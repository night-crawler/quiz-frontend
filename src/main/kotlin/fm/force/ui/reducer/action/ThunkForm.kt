package fm.force.ui.reducer.action

import fm.force.ui.client.FetchError
import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.State
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

abstract class ThunkForm :
    Thunk<State, RAction, WrapperAction, QuizClient> {
    suspend fun checkedRun(
        start: suspend () -> WrapperAction,
        error: suspend (original: FetchError, transformed: SubmissionError) -> WrapperAction,
        success: suspend () -> WrapperAction
    ): WrapperAction {
        start()

        return try {
            success()
        } catch (exc: FetchError) {
            val transformed = SubmissionError.of(exc)
            error(exc, transformed)
            throw transformed
        }
    }
}
