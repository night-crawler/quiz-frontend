package fm.force.ui.component.tag.list

import com.ccfraser.muirwik.components.dialog.mDialogContent
import com.ccfraser.muirwik.components.dialog.mDialogContentText
import com.ccfraser.muirwik.components.dialog.mDialogTitle
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemSecondaryAction
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import com.ccfraser.muirwik.components.targetInputValue
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TagPatchDTO
import fm.force.ui.component.main.confirmDeleteDialog
import fm.force.ui.component.main.iconMenu
import fm.force.ui.hook.callApi
import fm.force.ui.util.Icon
import fm.force.ui.util.treeIterator
import org.w3c.dom.Node
import org.w3c.dom.events.EventTarget
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.findDOMNode

interface TagRowProps : RProps {
    var tag: TagFullDTO
    var onDelete: (tag: TagFullDTO) -> Unit
}

class TagRow(props: TagRowProps) : RComponent<TagRowProps, RState>(props) {
    private var dialogRef: Node? = null
    private var text: String = props.tag.name

    private fun shouldCloseMenu(eventTarget: EventTarget?): Boolean {
        val anchor = dialogRef ?: return true
        val negativeMatches = setOf(anchor) + anchor.treeIterator().asSequence().toSet()
        if (eventTarget in negativeMatches) return false
        return false
    }

    override fun RBuilder.render() {
        val tag = props.tag

        mListItem {
            mFormControl(fullWidth = true) {
                mTextField("Name", defaultValue = text) {
                    attrs {
                        onKeyPress = {
                            if (it.key == "Enter") {
                                saveTag(TagPatchDTO(it.targetInputValue.trim()))
                            }
                        }
                        onBlur = {
                            saveTag(TagPatchDTO(it.targetInputValue.trim()))
                        }
                    }
                }
            }

            mListItemSecondaryAction {
                renderAction(tag)
            }
        }
    }

    private fun saveTag(patch: TagPatchDTO) {
        if (patch.name == props.tag.name) {
            return
        }
        callApi { patchTag(props.tag.id, patch) }
    }

    private fun RBuilder.renderAction(tag: TagFullDTO) =
        iconMenu(Icon.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
            confirmDeleteDialog(
                dialogRef = { dialogRef = findDOMNode(it) },
                title = RBuilder().mDialogTitle("Delete tag ${tag.name}?"),
                content = RBuilder().mDialogContent {
                    mDialogContentText("This action is permanent")
                },
                onConfirm = { handleConfirmDelete(tag) }
            ) { setIsOpen ->
                mMenuItemWithIcon(
                    Icon.REMOVE.iconMame, "Delete",
                    onClick = {
                        setIsOpen(true)
                    }
                )
            }
        }

    private fun handleConfirmDelete(tag: TagFullDTO) {
        props.onDelete(tag)
    }
}
