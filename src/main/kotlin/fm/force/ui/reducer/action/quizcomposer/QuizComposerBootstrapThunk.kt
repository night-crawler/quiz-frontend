package fm.force.ui.reducer.action.quizcomposer

import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.state.QuizState
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class QuizComposerBootstrapThunk(private val quizId: Long) : Thunk<QuizState, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> QuizState,
        extra: QuizClient
    ): WrapperAction {
        val quiz = extra.getQuiz(quizId)
        return dispatch(QuizComposerBootstrapSuccess(quiz))
    }
}
