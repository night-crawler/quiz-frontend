package fm.force.ui.component.session

import fm.force.quiz.common.dto.QuizRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionQuestionAnswerRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.component.main.loadingCard
import fm.force.ui.util.RouterContext
import fm.force.ui.util.jsApply
import kotlinext.js.Object
import react.RProps
import react.functionalComponent
import react.use.useKey
import react.useContext
import react.useEffect

val SENTINEL = listOf<String>()

interface SessionUIProps : RProps {
    var currentQuestion: QuizSessionQuestionRestrictedDTO?
    var totalQuestions: Int
    var seq: Int
    var checkedAnswers: Set<Long>
    var correctAnswers: Set<Long>
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
    useKey("ArrowDown", props.goFirstUnanswered, jsApply {}, arrayOf(props))
    useKey("ArrowUp", props.goLastUnanswered, jsApply {}, arrayOf(props))
    useKey("ArrowRight", handleNextQuestion, jsApply {}, arrayOf(props))
    useKey("Enter", handleDoAnswer, jsApply {}, arrayOf(props))

    // must create all hooks here
    (1..10).forEach { num ->
        val bindKey = "${num % 10}"
        useKey(
            bindKey,
            { _ ->
                if (!props.isSubmitted)
                    props.currentQuestion?.answers?.getOrNull(num - 1)?.let {
                        props.toggleAnswer(props.currentQuestion!!, it)
                    }
            },
            jsApply {}, arrayOf(props)
        )
    }

    val currentQuestion = props.currentQuestion
    val session = props.session
    if (currentQuestion == null || session == null) {
        loadingCard()
        return@functionalComponent
    }

    child(SessionQuestionPane::class) {
        Object.assign(attrs, props)
        key = "quiz-session-question:${currentQuestion.id}"
        attrs {
            quizTitle = listOf(props.quiz?.title ?: "-", "(${props.totalQuestions})").joinToString(" ")
            question = currentQuestion
            onNextQuestion = handleNextQuestion
            onPrevQuestion = handlePrevQuestion
            progress = props.seq.toDouble() / (props.totalQuestions - 1) * 100
            checkedAnswers = props.checkedAnswers
            correctAnswers = props.correctAnswers
            onDoAnswerClick = handleDoAnswer
            isSubmitted = props.isSubmitted
            goFirstUnanswered = props.goFirstUnanswered
            goLastUnanswered = props.goLastUnanswered
            isLast = props.seq == props.totalQuestions - 1
            shouldRenderAnswerButton = session.isAnswerable && !session.isExpired && !isSubmitted
        }
    }
}
