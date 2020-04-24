package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.card.*
import com.ccfraser.muirwik.components.dialog.mDialogContent
import com.ccfraser.muirwik.components.dialog.mDialogContentText
import com.ccfraser.muirwik.components.dialog.mDialogTitle
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import date.fns.formatDistance
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.component.confirmDeleteDialog
import fm.force.ui.component.iconMenu
import fm.force.ui.component.routeLink
import fm.force.ui.util.IconName
import fm.force.ui.util.treeIterator
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
import kotlin.js.Date

interface QuestionRowProps : RProps {
    var question: QuestionFullDTO
    var onDelete: (question: QuestionFullDTO) -> Unit
}

class QuestionRow(props: QuestionRowProps) : RComponent<QuestionRowProps, RState>(props) {
    private var dialogRef: Node? = null

    private fun shouldCloseMenu(eventTarget: EventTarget?): Boolean {
        val anchor = dialogRef ?: return true
        val negativeMatches = setOf(anchor) + anchor.treeIterator().asSequence().toSet()
        if (eventTarget in negativeMatches) return false
        return false
    }

    override fun RBuilder.render() {
        val question = props.question
        mCard(raised = true) {
            css {
                marginBottom = 5.px
            }
            mCardHeader(
                title = question.title,
                subHeader = "Created " + formatDistance(question.createdAt, Date()) + " ago"
            ) {
                attrs {
                    avatar = mAvatar(addAsChild = false) { +question.title.slice(0..1) }
                    action = renderAction(question)
                }
            }
            mCardContent {
                child(ReadOnlyQuestionCode::class) {
                    attrs {
                        this.question = question
                    }
                }
                renderQuestionAnswers(question)
            }
            mCardActions {
                question.tags.forEach {
                    mChip(it.name, size = MChipSize.small, variant = MChipVariant.outlined, color = MChipColor.primary)
                }
                question.topics.forEach {
                    mChip(
                        it.title,
                        size = MChipSize.small,
                        variant = MChipVariant.outlined,
                        color = MChipColor.secondary
                    )
                }
            }
        }
    }

    private fun StyledElementBuilder<MCardHeaderProps>.renderAction(question: QuestionFullDTO) =
        iconMenu(IconName.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
            confirmDeleteDialog(
                dialogRef = { dialogRef = findDOMNode(it) },
                title = RBuilder().mDialogTitle("Delete question ${question.title}?"),
                content = RBuilder().mDialogContent {
                    mDialogContentText("This action is permanent")
                },
                onConfirm = { handleConfirmDelete(question) }
            ) { setIsOpen ->
                mMenuItemWithIcon(
                    IconName.REMOVE.iconMame, "Delete",
                    onClick = {
                        setIsOpen(true)
                    }
                )
            }
            routeLink("/questions/${question.id}/edit") {
                mMenuItemWithIcon(IconName.EDIT.iconMame, "Edit", onClick = it.onClick)
            }
        }

    private fun handleConfirmDelete(question: QuestionFullDTO) {
        props.onDelete(question)
    }
}
