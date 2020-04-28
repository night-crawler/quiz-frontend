package fm.force.ui.component.session

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.*
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
import fm.force.ui.util.jsApply
import kotlinext.js.Object
import kotlinx.css.*
import react.*
import react.use.useKey
import styled.css

interface SessionUIProps : RProps {
    var currentQuestion: QuizSessionQuestionRestrictedDTO?
    var totalQuestions: Int
    var seq: Int
    var checkedAnswers: Set<Long>
    var session: QuizSessionFullDTO?
    var quiz: QuizRestrictedDTO?
    var isSubmitted: Boolean

    var toggleAnswer: (question: QuizSessionQuestionRestrictedDTO, answer: QuizSessionQuestionAnswerRestrictedDTO) -> Unit
    var doAnswer: (session: QuizSessionFullDTO, question: QuizSessionQuestionRestrictedDTO, answerIds: Set<Long>) -> Unit
    var bootstrap: (sessionId: Long) -> Unit
    var setSeq: (seq: Int) -> Unit
    var goFirstUnanswered: (Any) -> Unit
    var goLastUnanswered: (Any) -> Unit
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

    val handlePrevQuestion = { _: Any ->
        if (props.seq > 0) props.setSeq(props.seq - 1)
    }

    val handleNextQuestion = { _: Any ->
        if (props.seq < props.totalQuestions - 1) props.setSeq(props.seq + 1)
    }

    val handleDoAnswer = { _: Any ->
        val session = props.session
        val currentQuestion = props.currentQuestion
        if (session != null && currentQuestion != null && !props.isSubmitted && props.checkedAnswers.isNotEmpty())
            props.doAnswer(session, currentQuestion, props.checkedAnswers)
    }

    useKey("ArrowLeft", handlePrevQuestion, jsApply {}, arrayOf(props))
    useKey("ArrowRight", handleNextQuestion, jsApply {}, arrayOf(props))
    useKey("Enter", handleDoAnswer, jsApply {}, arrayOf(props))

    (1..10).forEach { num ->
        val bindKey = "${num % 10}"
        useKey(bindKey, { _ ->
            if (!props.isSubmitted)
                props.currentQuestion?.answers?.getOrNull(num - 1)?.let {
                    props.toggleAnswer(props.currentQuestion!!, it)
                }
        }, jsApply {}, arrayOf(props))
    }

    val currentQuestion = props.currentQuestion

    if (currentQuestion == null) {
        loadingCard()
        return@functionalComponent
    }

    child(QuestionDisplay::class) {
        Object.assign(attrs, props)
        key = "quiz-session-question:${currentQuestion.id}"
        attrs {
            quizTitle = props.quiz?.title ?: ""
            question = currentQuestion
            onNextQuestion = handleNextQuestion
            onPrevQuestion = handlePrevQuestion
            progress = props.seq.toDouble() / (props.totalQuestions - 1) * 100
            checkedAnswers = props.checkedAnswers
            onDoAnswerClick = handleDoAnswer
            isSubmitted = props.isSubmitted
            goFirstUnanswered = props.goFirstUnanswered
            goLastUnanswered = props.goLastUnanswered
        }
    }

}

interface QuestionDisplayProps : RProps {
    var quizTitle: String
    var question: QuizSessionQuestionRestrictedDTO
    var checkedAnswers: Set<Long>
    var progress: Double
    var isSubmitted: Boolean

    var goFirstUnanswered: (Any) -> Unit
    var goLastUnanswered: (Any) -> Unit
    var onDoAnswerClick: (Any) -> Unit
    var onNextQuestion: (Any) -> Unit
    var onPrevQuestion: (Any) -> Unit
    var toggleAnswer: (question: QuizSessionQuestionRestrictedDTO, answer: QuizSessionQuestionAnswerRestrictedDTO) -> Unit
}

class QuestionDisplay(props: QuestionDisplayProps) : RComponent<QuestionDisplayProps, RState>(props) {
    private var showHelp = false

    private val breakpoints = MGridBreakpoints(MGridSize.cells6)
        .up(Breakpoint.lg, MGridSize.cells6)
        .down(Breakpoint.sm, MGridSize.cells12)

    override fun RBuilder.render() {
        mTypography(text = props.quizTitle, variant = MTypographyVariant.h6, align = MTypographyAlign.left)

        renderTopControls()

        readOnlyQuestionCode(props.question.text, CodeLanguage.GENERAL)

        if (showHelp) {
            readOnlyQuestionCode(props.question.help, CodeLanguage.GENERAL, lineWrapping = true)
        } else {
            mButton("Show help", onClick = { setState { showHelp = true } }) {
                css { width = 100.pct }
            }
        }

        renderAnswerOptions()

        if (!props.isSubmitted)
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
                mIcon(IconName.ARROW_LEFT.iconMame, MIconColor.inherit)
            }

            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.goFirstUnanswered
            ) {
                mIcon(IconName.FAST_REWIND.iconMame, MIconColor.inherit)
            }

            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.goLastUnanswered
            ) {
                mIcon(IconName.FAST_FORWARD.iconMame, MIconColor.inherit)
            }

            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.onNextQuestion
            ) {
                mIcon(IconName.ARROW_RIGHT.iconMame, MIconColor.inherit)
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

                    val numberPrefix = if (index <= 10) {
                        "${((index + 1) % 10)}. "
                    } else ""

                    mGridItem(breakpoints) {
                        mListItem(button = true, selected = isChecked, onClick = {
                            props.toggleAnswer(props.question, answer)
                        }) {
                            mListItemText("$numberPrefix${answer.text}")
                            mListItemSecondaryAction {
                                mCheckbox(isChecked, onChange = { _, _ ->
                                    props.toggleAnswer(props.question, answer)
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
