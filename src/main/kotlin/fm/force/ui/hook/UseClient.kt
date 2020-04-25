package fm.force.ui.hook

import fm.force.ui.ReduxStore
import fm.force.ui.client.QuizClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.RDependenciesList
import react.useEffect
import redux.RAction
import redux.WrapperAction

val SENTINEL = listOf(null)

suspend fun defaultNetworkErrorHandler(dispatch: (RAction) -> WrapperAction, error: Throwable) {
    console.log(error)
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
