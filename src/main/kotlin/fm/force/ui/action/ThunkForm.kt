package fm.force.ui.action

import fm.force.ui.client.FetchClientNetworkError
import fm.force.ui.client.FetchError
import fm.force.ui.client.QuizClient
import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.client.dto.ErrorResponse
import fm.force.ui.client.isJson
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
            val transformed = transformException(exc)
            error(exc, transformed)
            throw transformed
        }
    }

    fun transformException(exc: FetchError): SubmissionError {
        if (exc is FetchClientNetworkError) {
            return SubmissionError.of("Server is down or no internet connection")
        }

        if (exc.response == null) {
            return SubmissionError.of("Response is empty")
        }

        if (!exc.response.isJson()) {
            return SubmissionError.of("Server response type is not JSON")
        }

        val errorResponse = try {
            QuizJson.jsonX.parse(ErrorResponse.serializer(), exc.responseText)
        } catch (exc: Throwable) {
            return SubmissionError.of("Failed to parse a server response: $exc")
        }

        return SubmissionError.of(errorResponse)
    }
}
