package fm.force.ui.extension

import fm.force.quiz.common.dto.ErrorResponse
import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.client.FetchClientNetworkError
import fm.force.ui.client.FetchError
import fm.force.ui.client.isJson

fun FetchError.toPlainString(): String {
    if (this is FetchClientNetworkError) {
        return "[$status] Server is down or no internet connection"
    }

    if (this.response == null) {
        return "[$status] Response is empty"
    }

    if (!this.response.isJson()) {
        return "[$status] Server response type is not JSON"
    }

    return try {
        "[$status] " + QuizJson.jsonX.parse(ErrorResponse.serializer(), this.responseText).toPlainString()
    } catch (exc: Throwable) {
        return "[$status] Failed to parse a server response: $exc"
    }
}
