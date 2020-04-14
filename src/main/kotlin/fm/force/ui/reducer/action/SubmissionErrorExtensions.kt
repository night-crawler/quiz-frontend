package fm.force.ui.reducer.action

import fm.force.quiz.common.dto.ErrorMessage
import fm.force.quiz.common.dto.ErrorResponse
import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.client.FetchClientNetworkError
import fm.force.ui.client.FetchError
import fm.force.ui.client.isJson
import fm.force.ui.client.toReduxFormErrors
import fm.force.ui.util.deepSet
import fm.force.ui.util.toJson

fun SubmissionError.Companion.of(message: String) =
    SubmissionError(
        mapOf(
            "_error" to listOf(ErrorMessage(message))
        ).toJson()
    )

fun SubmissionError.Companion.of(errorResponse: ErrorResponse) =
    SubmissionError(errorResponse.toReduxFormErrors().toJson())

fun SubmissionError.Companion.ofNestedNonField(path: String, message: String) {
    var root = mutableMapOf<String, Any>()
    path.split(".").forEach { part ->
        val nested = mutableMapOf<String, Any>()
        root[part] = nested
        root = nested
    }
}

fun SubmissionError.Companion.of(error: FetchError): SubmissionError {
    if (error is FetchClientNetworkError) {
        return SubmissionError.of("Server is down or no internet connection")
    }

    if (error.response == null) {
        return SubmissionError.of("Response is empty")
    }

    if (!error.response.isJson()) {
        return SubmissionError.of("Server response type is not JSON")
    }

    val errorResponse = try {
        QuizJson.jsonX.parse(ErrorResponse.serializer(), error.responseText)
    } catch (exc: Throwable) {
        return SubmissionError.of("Failed to parse a server response: $exc")
    }

    return SubmissionError.of(errorResponse)
}

fun SubmissionError.Companion.of(path: String, vararg errors: dynamic, destination: dynamic = js("{}")) =
    SubmissionError(deepSet(path, errors.asList().toJson(), destination))
