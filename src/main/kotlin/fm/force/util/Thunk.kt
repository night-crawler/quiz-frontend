package fm.force.util

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import redux.MiddlewareApi
import redux.RAction

interface Thunk<S, A, R, X> : RAction {
    suspend fun run(originalAction: RAction, dispatch: (A) -> R, getState: () -> S, extra: X)
}

class ThunkAcknowledged(val originalAction: RAction) : RAction

class ThunkError(val originalAction: RAction, val exception: Throwable) : RAction

fun <S, A, WA, X> createThunkMiddleware(
    extra: X,
    errorActionBuilder: (A, Throwable) -> A
): (MiddlewareApi<S, A, WA>) -> ((A) -> WA) -> (A) -> WA {

    fun thunkMiddleware(api: MiddlewareApi<S, A, WA>): ((A) -> WA) -> (A) -> WA {
        return { next ->
            { action ->
                GlobalScope.launch {
                    if (action is Thunk<*, *, *, *>) {
                        try {
                            (action.unsafeCast<Thunk<S, A, WA, X>>()).run(action, api::dispatch, api::getState, extra)
                        } catch (exc: Throwable) {
                            api.dispatch(errorActionBuilder(action, exc))
                        }
                    }
                }
                next(action)
            }
        }
    }
    return ::thunkMiddleware
}
