package fm.force.ui.component.difficultyscale.list

import com.ccfraser.muirwik.components.card.MCardHeaderProps
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.dialog.mDialogContent
import com.ccfraser.muirwik.components.dialog.mDialogContentText
import com.ccfraser.muirwik.components.dialog.mDialogTitle
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import com.ccfraser.muirwik.components.table.*
import date.fns.formatDistance
import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.ui.component.main.confirmDeleteDialog
import fm.force.ui.component.main.iconMenu
import fm.force.ui.component.main.routeLink
import fm.force.ui.util.IconName
import fm.force.ui.util.treeIterator
import kotlin.js.Date
import kotlinx.css.marginBottom
import kotlinx.css.px
import org.w3c.dom.Node
import org.w3c.dom.events.EventTarget
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.findDOMNode
import styled.StyledElementBuilder
import styled.css

interface DifficultyScaleRowProps : RProps {
    var difficultyScale: DifficultyScaleFullDTO
    var onDelete: (difficultyScale: DifficultyScaleFullDTO) -> Unit
}

class DifficultyScaleRow(props: DifficultyScaleRowProps) : RComponent<DifficultyScaleRowProps, RState>(props) {
    private var dialogRef: Node? = null

    private fun shouldCloseMenu(eventTarget: EventTarget?): Boolean {
        val anchor = dialogRef ?: return true
        val negativeMatches = setOf(anchor) + anchor.treeIterator().asSequence().toSet()
        if (eventTarget in negativeMatches) return false
        return false
    }

    override fun RBuilder.render() {
        val difficultyScale = props.difficultyScale
        mCard(raised = true) {
            css {
                marginBottom = 5.px
            }
            mCardHeader(
                title = difficultyScale.name,
                subHeader = "Created " + formatDistance(difficultyScale.createdAt, Date()) + " ago"
            ) {
                attrs {
                    avatar = mAvatar(addAsChild = false) { +difficultyScale.name.slice(0..1) }
                    action = renderAction(difficultyScale)
                }
            }
            mCardContent {
                mTable {
                    mTableHead {
                        mTableRow {
                            mTableCell { +"Title" }
                            mTableCell { +"Min" }
                            mTableCell { +"Max" }
                        }
                    }
                    mTableBody {
                        difficultyScale.difficultyScaleRanges.forEach { range ->
                            mTableRow {
                                mTableCell { +range.title }
                                mTableCell { +"${range.min}" }
                                mTableCell { +"${range.max}" }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun StyledElementBuilder<MCardHeaderProps>.renderAction(difficultyScale: DifficultyScaleFullDTO) =
        iconMenu(IconName.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
            confirmDeleteDialog(
                dialogRef = { dialogRef = findDOMNode(it) },
                title = RBuilder().mDialogTitle("Delete difficultyScale ${difficultyScale.name}?"),
                content = RBuilder().mDialogContent {
                    mDialogContentText("This action is permanent")
                },
                onConfirm = { handleConfirmDelete(difficultyScale) }
            ) { setIsOpen ->
                mMenuItemWithIcon(
                    IconName.REMOVE.iconMame, "Delete",
                    onClick = {
                        setIsOpen(true)
                    }
                )
            }
            routeLink("/difficulty-scales/${difficultyScale.id}/edit") {
                mMenuItemWithIcon(IconName.EDIT.iconMame, "Edit", onClick = it.onClick)
            }
        }

    private fun handleConfirmDelete(difficultyScale: DifficultyScaleFullDTO) {
        props.onDelete(difficultyScale)
    }
}
