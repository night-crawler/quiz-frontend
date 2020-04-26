package fm.force.ui.component.session

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.MButtonSize
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemSecondaryAction
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.styles.Breakpoint
import fm.force.quiz.common.dto.QuizRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionQuestionAnswerRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.component.main.loadingCard
import fm.force.ui.component.question.list.readOnlyQuestionCode
import fm.force.ui.extension.CodeLanguage
import fm.force.ui.util.IconName
import fm.force.ui.util.RouterContext
import kotlinext.js.Object
import kotlinx.css.em
import kotlinx.css.fontSize
import kotlinx.css.pct
import kotlinx.css.width
import react.*
import styled.css

interface SessionUIProps : RProps {
    var currentQuestion: QuizSessionQuestionRestrictedDTO?
    var totalQuestions: Int
    var bootstrap: (sessionId: Long) -> Unit
    var setSeq: (seq: Int) -> Unit
    var seq: Int
    var toggleAnswer: (question: QuizSessionQuestionRestrictedDTO, answer: QuizSessionQuestionAnswerRestrictedDTO) -> Unit
    var checkedAnswers: Set<Long>
    var doAnswer: (session: QuizSessionFullDTO, question: QuizSessionQuestionRestrictedDTO, answerIds: Set<Long>) -> Unit
    var session: QuizSessionFullDTO?
    var quiz: QuizRestrictedDTO?
}

interface MatchProps : RProps {
    val id: String
}

val SENTINEL = listOf<String>()

val SessionUI = functionalComponent<SessionUIProps> { props ->
    val routerContext = useContext(RouterContext)
    val sessionId = routerContext.match.params.unsafeCast<MatchProps>().id.toLong()

    useEffect(SENTINEL) {
        props.bootstrap(sessionId)
    }

    val currentQuestion = props.currentQuestion

    if (currentQuestion == null) {
        loadingCard()
        return@functionalComponent
    }

    child(QuestionDisplay::class) {
        Object.assign(attrs, props)
        key = "quiz-session-question:${currentQuestion.id}"
    }

}

class QuestionDisplay(props: SessionUIProps) : RComponent<SessionUIProps, RState>(props) {
    private var showHelp = false

    private val breakpoints = MGridBreakpoints(MGridSize.cells6)
        .up(Breakpoint.lg, MGridSize.cells6)
        .down(Breakpoint.sm, MGridSize.cells12)

    override fun RBuilder.render() {
        props.quiz?.title?.let {
            mTypography(text = it, variant = MTypographyVariant.h6, align = MTypographyAlign.left)
        }

        mGridContainer(MGridSpacing.spacing0) {
            mGridItem(xs = MGridSize.cells1) {
                mIconButton(
                    iconName = IconName.ARROW_LEFT.iconMame,
                    disabled = props.seq == 0,
                    onClick = { props.setSeq(props.seq - 1) }
                )
            }
            mGridItem(xs = MGridSize.cells10) {
                mTypography(
                    text = props.currentQuestion!!.title,
                    variant = MTypographyVariant.h6,
                    align = MTypographyAlign.center
                ) {
                    css { fontSize = 1.25.em }
                }
                mLinearProgress(
                    value = props.seq.toDouble() / (props.totalQuestions - 1) * 100,
                    variant = MLinearProgressVariant.determinate
                )
            }
            mGridItem(xs = MGridSize.cells1) {
                mIconButton(
                    iconName = IconName.ARROW_RIGHT.iconMame,
                    color = MColor.primary,
                    disabled = props.seq == props.totalQuestions - 1,
                    onClick = { props.setSeq(props.seq + 1) }
                )
            }
        }

        readOnlyQuestionCode(props.currentQuestion!!.text, CodeLanguage.GENERAL)

        if (showHelp) {
            readOnlyQuestionCode(props.currentQuestion!!.help, CodeLanguage.GENERAL, lineWrapping = true)
        } else {
            mButton("Show help", onClick = { setState { showHelp = true } }) {
                css { width = 100.pct }
            }
        }

        mList {
            mGridContainer(MGridSpacing.spacing0) {
                props.currentQuestion!!.answers.forEach { answer ->
                    val isChecked = answer.id in props.checkedAnswers

                    mGridItem(breakpoints) {
                        mListItem(button = true, selected = isChecked, onClick = {
                            props.toggleAnswer(props.currentQuestion!!, answer)
                        }) {
                            mListItemText(answer.text)
                            mListItemSecondaryAction {
                                mCheckbox(isChecked, onChange = { _, _ ->
                                    props.toggleAnswer(props.currentQuestion!!, answer)
                                })
                            }
                        }
                    }
                }
            }
        }

        mButton(
            caption = "Answer",
            color = MColor.primary,
            variant = MButtonVariant.outlined,
            size = MButtonSize.large
        ) {
            css { width = 100.pct }
            attrs {
                onClick = {
                    val currentQuestion = props.currentQuestion
                    val session = props.session
                    if (session != null && currentQuestion != null && props.checkedAnswers.isNotEmpty()) {
                        props.doAnswer(session, currentQuestion, props.checkedAnswers)
                    }
                }
            }
        }
    }
}
