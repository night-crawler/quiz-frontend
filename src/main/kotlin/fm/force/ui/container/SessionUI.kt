package fm.force.ui.container

import fm.force.quiz.common.dto.QuizRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionQuestionAnswerRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.component.session.SessionUI
import fm.force.ui.component.session.SessionUIProps
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.session.*
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface SessionUIConnectedProps : RProps

private interface SessionUIStateProps : RProps {
    var currentQuestion: QuizSessionQuestionRestrictedDTO?
    var totalQuestions: Int
    var seq: Int
    var checkedAnswers: Set<Long>
    var session: QuizSessionFullDTO?
    var quiz: QuizRestrictedDTO?
    var isSubmitted: Boolean
}

private interface SessionUIDispatchProps : RProps {
    var bootstrap: (sessionId: Long) -> Unit
    var setSeq: (seq: Int) -> Unit
    var toggleAnswer: (question: QuizSessionQuestionRestrictedDTO, answer: QuizSessionQuestionAnswerRestrictedDTO) -> Unit
    var doAnswer: (session: QuizSessionFullDTO, question: QuizSessionQuestionRestrictedDTO, answerIds: Set<Long>) -> Unit
    var goFirstUnanswered: (Any) -> Unit
    var goLastUnanswered: (Any) -> Unit
}

private val mapStateToProps: SessionUIStateProps.(State, SessionUIConnectedProps) -> Unit = { state, _ ->
    currentQuestion = state.quizSessionState.questions.getOrNull(state.quizSessionState.seq)
    totalQuestions = state.quizSessionState.questions.size
    seq = state.quizSessionState.seq
    session = state.quizSessionState.session
    quiz = state.quizSessionState.quiz

    isSubmitted = currentQuestion?.id in state.quizSessionState.submittedQuestions

    checkedAnswers = currentQuestion?.let {
        state.quizSessionState.answerMap[it.id]
    } ?: setOf()
}

private val mapDispatchToProps: SessionUIDispatchProps.((RAction) -> WrapperAction, SessionUIConnectedProps) -> Unit =
    { dispatch, _ ->
        bootstrap = { sessionId ->  dispatch(SessionBootstrapThunk(sessionId)) }
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

val sessionUI: RClass<SessionUIConnectedProps> =
    rConnect<State, RAction, WrapperAction, SessionUIConnectedProps, SessionUIStateProps, SessionUIDispatchProps, SessionUIProps>(
        mapStateToProps,
        mapDispatchToProps
    )(SessionUI.unsafeCast<RClass<SessionUIProps>>())
