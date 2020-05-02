package fm.force.ui.container

import fm.force.quiz.common.dto.QuizRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionQuestionAnswerRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.component.session.QuizSessionComponent
import fm.force.ui.component.session.QuizSessionComponentProps
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.session.*
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface QuizSessionContainerConnectedProps : RProps

private interface QuizSessionContainerStateProps : RProps {
    var currentQuestion: QuizSessionQuestionRestrictedDTO?
    var totalQuestions: Int
    var seq: Int
    var checkedAnswers: Set<Long>
    var correctAnswers: Set<Long>
    var session: QuizSessionFullDTO?
    var quiz: QuizRestrictedDTO?
    var isSubmitted: Boolean
    var remainingCount: Long
}

private interface QuizSessionContainerDispatchProps : RProps {
    var bootstrap: (sessionId: Long) -> Unit
    var setSeq: (seq: Int) -> Unit
    var toggleAnswer: (question: QuizSessionQuestionRestrictedDTO, answer: QuizSessionQuestionAnswerRestrictedDTO) -> Unit
    var doAnswer: (session: QuizSessionFullDTO, question: QuizSessionQuestionRestrictedDTO, answerIds: Set<Long>) -> Unit
    var goFirstUnanswered: (Any) -> Unit
    var goLastUnanswered: (Any) -> Unit
}

private val mapStateToProps: QuizSessionContainerStateProps.(State, QuizSessionContainerConnectedProps) -> Unit = { state, _ ->
    currentQuestion = state.quizSessionState.questions.getOrNull(state.quizSessionState.seq)
    totalQuestions = state.quizSessionState.questions.size
    seq = state.quizSessionState.seq
    session = state.quizSessionState.session
    quiz = state.quizSessionState.quiz
    remainingCount = state.quizSessionState.remainingCount

    isSubmitted = currentQuestion?.id in state.quizSessionState.submittedQuestions

    checkedAnswers = currentQuestion?.let {
        state.quizSessionState.answerMap[it.id]
    } ?: setOf()
    correctAnswers = currentQuestion?.let {
        state.quizSessionState.correctAnswerMap[it.id]
    } ?: setOf()
}

private val mapDispatchToProps: QuizSessionContainerDispatchProps.((RAction) -> WrapperAction, QuizSessionContainerConnectedProps) -> Unit =
    { dispatch, _ ->
        bootstrap = { sessionId -> dispatch(SessionBootstrapThunk(sessionId)) }
        setSeq = { seq ->
            dispatch(SessionSequenceSet(seq))
        }
        toggleAnswer = { question, answer ->
            dispatch(SessionAnswerToggled(question, answer))
        }
        doAnswer = { session, question, answerIds ->
            dispatch(SessionDoAnswerThunk(session, question, answerIds))
        }
        goFirstUnanswered = { dispatch(GoFirstUnanswered()) }
        goLastUnanswered = { dispatch(GoLastUnanswered()) }
    }

val sessionUI: RClass<QuizSessionContainerConnectedProps> =
    rConnect<State, RAction, WrapperAction, QuizSessionContainerConnectedProps, QuizSessionContainerStateProps, QuizSessionContainerDispatchProps, QuizSessionComponentProps>(
        mapStateToProps,
        mapDispatchToProps
    )(QuizSessionComponent.unsafeCast<RClass<QuizSessionComponentProps>>())
