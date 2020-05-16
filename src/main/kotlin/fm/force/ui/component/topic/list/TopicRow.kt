package fm.force.ui.component.topic.list

import com.ccfraser.muirwik.components.dialog.mDialogContent
import com.ccfraser.muirwik.components.dialog.mDialogContentText
import com.ccfraser.muirwik.components.dialog.mDialogTitle
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemSecondaryAction
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import com.ccfraser.muirwik.components.targetInputValue
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.quiz.common.dto.TopicPatchDTO
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

interface TopicRowProps : RProps {
    var topic: TopicFullDTO
    var onDelete: (topic: TopicFullDTO) -> Unit
}

class TopicRow(props: TopicRowProps) : RComponent<TopicRowProps, RState>(props) {
    private var dialogRef: Node? = null
    private var text: String = props.topic.title

    private fun shouldCloseMenu(eventTarget: EventTarget?): Boolean {
        val anchor = dialogRef ?: return true
        val negativeMatches = setOf(anchor) + anchor.treeIterator().asSequence().toSet()
        if (eventTarget in negativeMatches) return false
        return false
    }

    override fun RBuilder.render() {
        val topic = props.topic

        mListItem {
            mFormControl(fullWidth = true) {
                mTextField("Title", defaultValue = text) {
                    attrs {
                        onKeyPress = {
                            if (it.key == "Enter") {
                                saveTopic(TopicPatchDTO(it.targetInputValue.trim()))
                            }
                        }
                        onBlur = {
                            saveTopic(TopicPatchDTO(it.targetInputValue.trim()))
                        }
                    }
                }
            }

            mListItemSecondaryAction {
                renderAction(topic)
            }
        }
    }

    private fun saveTopic(patch: TopicPatchDTO) {
        if (patch.title == props.topic.title) {
            return
        }
        callApi { patchTopic(props.topic.id, patch) }
    }

    private fun RBuilder.renderAction(topic: TopicFullDTO) =
        iconMenu(Icon.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
            confirmDeleteDialog(
                dialogRef = { dialogRef = findDOMNode(it) },
                title = RBuilder().mDialogTitle("Delete topic ${topic.title}?"),
                content = RBuilder().mDialogContent {
                    mDialogContentText("This action is permanent")
                },
                onConfirm = { handleConfirmDelete(topic) }
            ) { setIsOpen ->
                mMenuItemWithIcon(
                    Icon.REMOVE.iconMame, "Delete",
                    onClick = {
                        setIsOpen(true)
                    }
                )
            }
        }

    private fun handleConfirmDelete(topic: TopicFullDTO) {
        props.onDelete(topic)
    }
}
