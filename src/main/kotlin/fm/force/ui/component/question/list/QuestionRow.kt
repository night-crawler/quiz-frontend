package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.card.*
import com.ccfraser.muirwik.components.dialog.mDialogContent
import com.ccfraser.muirwik.components.dialog.mDialogContentText
import com.ccfraser.muirwik.components.dialog.mDialogTitle
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.component.main.confirmDeleteDialog
import fm.force.ui.component.main.iconMenu
import fm.force.ui.component.main.routeLink
import fm.force.ui.component.question.markdownWithCode
import fm.force.ui.util.Icon
import fm.force.ui.util.ago
import fm.force.ui.util.treeIterator
import kotlinx.css.*
import org.w3c.dom.Node
import org.w3c.dom.events.EventTarget
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.findDOMNode
import styled.StyledElementBuilder
import styled.css
import styled.styledDiv

interface QuestionRowProps : RProps {
    var isSelected: Boolean
    var question: QuestionFullDTO
    var onDelete: (question: QuestionFullDTO) -> Unit
    var onSelectToggle: (question: QuestionFullDTO) -> Unit
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
            mCardHeader(title = question.title, subHeader = ago(question.createdAt)) {
                attrs {
                    avatar = mAvatar(addAsChild = false) { +question.title.slice(0..1) }
                    action = renderAction(question)
                }
            }
            mCardContent {
                mTypography("Text", color = MTypographyColor.textSecondary)
                markdownWithCode(question.text)
                mTypography("Help", color = MTypographyColor.textSecondary)
                markdownWithCode(question.help)
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
        styledDiv {
            css {
                width = 100.px
            }
            mCheckbox(props.isSelected, onChange = { _, value -> props.onSelectToggle(question) }) {
                css {
                    float = Float.left
                    marginTop = 3.px
                }
            }
            iconMenu(Icon.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
                css { float = Float.left }
                routeLink("/questions/${question.id}/edit") {
                    mMenuItemWithIcon(Icon.EDIT.iconMame, "Edit", onClick = it.onClick)
                }
                confirmDeleteDialog(
                    dialogRef = { dialogRef = findDOMNode(it) },
                    title = RBuilder().mDialogTitle("Delete question ${question.title}?"),
                    content = RBuilder().mDialogContent {
                        mDialogContentText("This action is permanent")
                    },
                    onConfirm = { handleConfirmDelete(question) }
                ) { setIsOpen ->
                    mMenuItemWithIcon(
                        Icon.REMOVE.iconMame, "Delete",
                        onClick = {
                            setIsOpen(true)
                        }
                    )
                }
            }
        }

    private fun handleConfirmDelete(question: QuestionFullDTO) {
        props.onDelete(question)
    }
}
