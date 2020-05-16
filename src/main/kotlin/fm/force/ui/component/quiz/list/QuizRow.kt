package fm.force.ui.component.quiz.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.*
import com.ccfraser.muirwik.components.dialog.mDialogContent
import com.ccfraser.muirwik.components.dialog.mDialogContentText
import com.ccfraser.muirwik.components.dialog.mDialogTitle
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import date.fns.formatDistance
import fm.force.quiz.common.dto.QuizFullDTO
import fm.force.ui.component.main.confirmDeleteDialog
import fm.force.ui.component.main.iconMenu
import fm.force.ui.component.main.routeLink
import fm.force.ui.util.Icon
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

interface QuizRowProps : RProps {
    var quiz: QuizFullDTO
    var onDelete: (quiz: QuizFullDTO) -> Unit
}

class QuizRow(props: QuizRowProps) : RComponent<QuizRowProps, RState>(props) {
    private var dialogRef: Node? = null

    private fun shouldCloseMenu(eventTarget: EventTarget?): Boolean {
        val anchor = dialogRef ?: return true
        val negativeMatches = setOf(anchor) + anchor.treeIterator().asSequence().toSet()
        if (eventTarget in negativeMatches) return false
        return false
    }

    override fun RBuilder.render() {
        val quiz = props.quiz
        mCard(raised = true) {
            css {
                marginBottom = 5.px
            }
            mCardHeader(
                title = quiz.title,
                subHeader = "Created " + formatDistance(quiz.createdAt, Date()) + " ago"
            ) {
                attrs {
                    avatar = mAvatar(addAsChild = false) { +quiz.title.slice(0..1) }
                    action = renderAction(quiz)
                }
            }
            mCardContent {
                mList(dense = true) {
                    quiz.quizQuestions.forEach { quizQuestion ->
                        mListItem {
                            +"#${quizQuestion.seq + 1} ${quizQuestion.question.title}"
                        }
                    }
                }
                mTypography {
                    +"Difficulty Scale: ${quiz.difficultyScale?.name ?: "-"}"
                }
            }

            routeLink("/quizzes/${quiz.id}/preview") {
                mIconButton(
                    color = MColor.primary,
                    onClick = it.onClick
                ) {
                    mIcon(Icon.DIRECTIONS_RUN.iconMame)
                    +"Start Quiz Session"
                }
            }

            mCardActions {
                quiz.tags.forEach {
                    mChip(it.name, size = MChipSize.small, variant = MChipVariant.outlined, color = MChipColor.primary)
                }
                quiz.topics.forEach {
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

    private fun StyledElementBuilder<MCardHeaderProps>.renderAction(quiz: QuizFullDTO) =
        iconMenu(Icon.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
            routeLink("/quizzes/${quiz.id}/edit") {
                mMenuItemWithIcon(Icon.EDIT.iconMame, "Edit", onClick = it.onClick)
            }
            routeLink("/quizzes/${quiz.id}/compose") {
                mMenuItemWithIcon(Icon.COMPARE.iconMame, "Compose", onClick = it.onClick)
            }
            confirmDeleteDialog(
                dialogRef = { dialogRef = findDOMNode(it) },
                title = RBuilder().mDialogTitle("Delete quiz ${quiz.title}?"),
                content = RBuilder().mDialogContent {
                    mDialogContentText("This action is permanent")
                },
                onConfirm = { handleConfirmDelete(quiz) }
            ) { setIsOpen ->
                mMenuItemWithIcon(
                    Icon.REMOVE.iconMame, "Delete",
                    onClick = {
                        setIsOpen(true)
                    }
                )
            }
        }

    private fun handleConfirmDelete(quiz: QuizFullDTO) {
        props.onDelete(quiz)
    }
}
