package fm.force.ui.reducer.action.quizcomposer

import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.QuizComposerBootstrapSuccess
import fm.force.ui.reducer.State
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class QuizComposerBootstrap(private val quizId: Long) :
    Thunk<State, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        extra: QuizClient
    ): WrapperAction {
        val quiz = extra.getQuiz(quizId)
        return dispatch(QuizComposerBootstrapSuccess(quiz))
    }
}
