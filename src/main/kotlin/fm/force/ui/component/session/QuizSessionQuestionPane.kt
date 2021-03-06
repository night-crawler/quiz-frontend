package fm.force.ui.component.session

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.*
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemSecondaryAction
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.styles.Breakpoint
import fm.force.quiz.common.dto.QuizSessionQuestionAnswerRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.component.question.markdownWithCode
import fm.force.ui.util.Icon
import kotlinx.css.*
import react.*
import react.dom.div
import styled.StyledElementBuilder
import styled.css
import kotlin.text.Typography.nbsp

interface QuizSessionQuestionPaneProps : RProps {
    var quizTitle: String
    var question: QuizSessionQuestionRestrictedDTO
    var checkedAnswers: Set<Long>
    var correctAnswers: Set<Long>
    var progress: Double
    var isSubmitted: Boolean
    var isLast: Boolean
    var isAnswerable: Boolean
    var remainingCount: Long
    var goFirstUnanswered: (Any) -> Unit
    var goLastUnanswered: (Any) -> Unit
    var onDoAnswerClick: (Any) -> Unit
    var onNextQuestion: (Any) -> Unit
    var onPrevQuestion: (Any) -> Unit
    var toggleAnswer: (question: QuizSessionQuestionRestrictedDTO, answer: QuizSessionQuestionAnswerRestrictedDTO) -> Unit
}

class QuizSessionQuestionPane(props: QuizSessionQuestionPaneProps) :
    RComponent<QuizSessionQuestionPaneProps, RState>(props) {
    private var showHelp = false

    private val breakpoints = MGridBreakpoints(MGridSize.cells6)
        .up(
            Breakpoint.lg,
            MGridSize.cells6
        )
        .down(
            Breakpoint.sm,
            MGridSize.cells12
        )

    override fun RBuilder.render() {
        mTypography(text = props.quizTitle, variant = MTypographyVariant.h6, align = MTypographyAlign.left)
        renderTopControls()
        markdownWithCode(props.question.text)
        renderHelpBlock()
        renderAnswerOptions()
        renderBottomButtons()

        mTypography(align = MTypographyAlign.center) {
            +"Remaining ${props.remainingCount}"
        }
    }

    private fun RBuilder.renderHelpBlock() {
        if (showHelp || props.isSubmitted) {
            markdownWithCode(props.question.help)
        } else {
            mButton("Show help", onClick = { setState { showHelp = true } }) {
                css { width = 100.pct }
            }
        }
    }

    private fun RBuilder.renderBottomButtons() {
        if (props.isAnswerable && !props.isSubmitted) {
            mButton(
                caption = "Answer",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                size = MButtonSize.large,
                onClick = props.onDoAnswerClick
            ) {
                css { width = 100.pct }
            }
        }

        if (!props.isLast && props.isSubmitted && props.isAnswerable) {
            mButton(
                caption = "Next",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                size = MButtonSize.large,
                onClick = props.goFirstUnanswered
            ) {
                css { width = 100.pct }
            }
        }
    }

    private fun RBuilder.renderTopControls() {
        mTypography(
            text = props.question.title,
            variant = MTypographyVariant.h6,
            align = MTypographyAlign.center
        ) {
            css { fontSize = 1.25.em }
        }
        mLinearProgress(
            value = props.progress,
            variant = MLinearProgressVariant.determinate
        )

        mButtonGroup(variant = MButtonGroupVariant.contained, size = MButtonSize.large, fullWidth = true) {
            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.onPrevQuestion
            ) {
                mIcon(
                    Icon.ARROW_LEFT.iconMame,
                    MIconColor.inherit
                )
            }

            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.goFirstUnanswered
            ) {
                mIcon(
                    Icon.FAST_REWIND.iconMame,
                    MIconColor.inherit
                )
            }

            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.goLastUnanswered
            ) {
                mIcon(
                    Icon.FAST_FORWARD.iconMame,
                    MIconColor.inherit
                )
            }

            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.onNextQuestion
            ) {
                mIcon(
                    Icon.ARROW_RIGHT.iconMame,
                    MIconColor.inherit
                )
            }

            css {
                marginTop = 10.px
                marginBottom = 10.px
            }
        }
    }

    private fun RBuilder.renderAnswerOptions() {
        mList {
            mGridContainer(MGridSpacing.spacing0) {
                props.question.answers.sortedBy { it.id }.forEachIndexed { index, answer ->
                    val isChecked = answer.id in props.checkedAnswers
                    val isCorrect = answer.id in props.correctAnswers
                    renderAnswerOption(answer, index, isChecked, isCorrect)
                }
            }
        }
    }

    private fun StyledElementBuilder<MGridProps>.renderAnswerOption(
        answer: QuizSessionQuestionAnswerRestrictedDTO,
        index: Int,
        isChecked: Boolean,
        isCorrect: Boolean
    ) {
        val numberPrefix = if (index <= 10) {
            "${((index + 1) % 10)}. "
        } else ""

        val cbColor = if (isCorrect) MOptionColor.primary else MOptionColor.secondary

        val handleToggle = {
            if (!props.isSubmitted)
                props.toggleAnswer(props.question, answer)
        }.asDynamic()

        mGridItem(breakpoints) {
            mListItem(button = true, selected = isChecked, onClick = handleToggle) {
                mListItemText {
                    attrs {
                        primary = div {
                            if (props.isSubmitted)
                                mCheckbox(isCorrect, color = cbColor) {
                                    attrs {
                                        css {
                                            color = Color.red
                                        }
                                    }
                                }
                            val prefixText = when {
                                answer.text.trim().startsWith("```") -> "**`$numberPrefix`** \n"
                                else -> "**`$numberPrefix`**$nbsp"
                            }
                            markdownWithCode("$prefixText${answer.text}")
                        }
                    }
                }
                mListItemSecondaryAction {
                    mCheckbox(isChecked, color = MOptionColor.primary, onChange = handleToggle)
                }
            }
        }
    }
}
