package fm.force.ui.hook

import com.ccfraser.muirwik.components.MSnackbarVertAnchor
import fm.force.quiz.common.dto.ErrorMessage
import fm.force.quiz.common.dto.ErrorResponse
import fm.force.quiz.common.dto.FieldError
import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.ReduxStore
import fm.force.ui.client.FetchClientNetworkError
import fm.force.ui.client.FetchError
import fm.force.ui.client.QuizClient
import fm.force.ui.client.isJson
import fm.force.ui.reducer.action.Snack
import fm.force.ui.reducer.action.SnackType
import fm.force.ui.reducer.action.dispatch
import fm.force.ui.util.Icon
import kotlin.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.RDependenciesList
import react.useEffect
import redux.RAction
import redux.WrapperAction

val SENTINEL = listOf(null)

fun ErrorResponse.toPlainString(): String {
    val fieldErrors = this.errors.filterIsInstance<FieldError>()
    val nonFieldErrors = errors.filterIsInstance<ErrorMessage>()
    val parts = fieldErrors.map { "${it.fieldName}: ${it.message}" } + nonFieldErrors.map { it.message }
    return parts.joinToString("\n")
}

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
        "[$status]" + QuizJson.jsonX.parse(ErrorResponse.serializer(), this.responseText).toPlainString()
    } catch (exc: Throwable) {
        return "[$status] Failed to parse a server response: $exc"
    }
}

suspend fun defaultNetworkErrorHandler(dispatch: (RAction) -> WrapperAction, error: Throwable) {
    val errorText = if (error is FetchError) error.toPlainString() else error.toString()
    window.setTimeout(
        {
            Snack(
                title = "Error ", text = errorText, icon = Icon.ERROR_OUTLINE, type = SnackType.ERROR,
                vertAnchor = MSnackbarVertAnchor.top,
                timeout = null
            ).dispatch(dispatch)
        },
        100
    )

    console.error(error)
}

fun <T> useClient(
    dependencies: RDependenciesList = SENTINEL,
    errorHandler: suspend (dispatch: (RAction) -> WrapperAction, ex: Throwable) -> Unit = ::defaultNetworkErrorHandler,
    handler: suspend QuizClient.() -> T
): T? {
    val dispatch = useDispatch()
    var data by UseState<T?>(null)

    useEffect(dependencies) {
        GlobalScope.promise {
            try {
                data = handler(ReduxStore.DEFAULT.client)
            } catch (e: Throwable) {
                errorHandler(dispatch, e)
            }
        }
    }
    return data
}

fun <T> callApi(
    errorHandler: suspend (dispatch: (RAction) -> WrapperAction, ex: Throwable) -> Unit = ::defaultNetworkErrorHandler,
    handler: suspend QuizClient.() -> T
) {
    val dispatch = ReduxStore.DEFAULT.store::dispatch

    GlobalScope.promise {
        try {
            handler(ReduxStore.DEFAULT.client)
        } catch (e: Throwable) {
            errorHandler(dispatch, e)
        }
    }
}
