package fm.force.util

import redux.MiddlewareApi
import redux.RAction

interface Thunk<S, A, R> : RAction {
    fun run(originalAction: RAction, dispatch: (A) -> R, getState: () -> S)
}

class ThunkAcknowledged(val originalAction: RAction) : RAction

class ThunkError(val originalAction: RAction, val exception: Throwable) : RAction

fun <S, A, WA> createThunkMiddleware(
    errorActionBuilder: (A, Throwable) -> A
): (MiddlewareApi<S, A, WA>) -> ((A) -> WA) -> (A) -> WA {

    fun thunkMiddleware(api: MiddlewareApi<S, A, WA>): ((A) -> WA) -> (A) -> WA {
        return { next ->
            { action ->
                if (action is Thunk<*, *, *>) {
                    try {
                        (action.unsafeCast<Thunk<S, A, WA>>()).run(action, api::dispatch, api::getState)
                    } catch (exc: Throwable) {
                        api.dispatch(errorActionBuilder(action, exc))
                    }
                }
                next(action)
            }
        }
    }
    return ::thunkMiddleware
}
