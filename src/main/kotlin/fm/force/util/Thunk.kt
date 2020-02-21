package fm.force.util

import redux.MiddlewareApi
import redux.RAction

interface Thunk<S, A, R> : RAction {
    fun run(dispatch: (A) -> R, getState: () -> S): R
}

class ThunkError(val originalAction: RAction, val exception: Exception) : RAction

fun <S, A, WA> createThunkMiddleware(errorActionBuilder: (A, Exception) -> A): (MiddlewareApi<S, A, WA>) -> ((A) -> WA) -> (A) -> WA {
    fun thunkMiddleware(api: MiddlewareApi<S, A, WA>): ((A) -> WA) -> (A) -> WA {
        return { next ->
            { action ->
                if (action is Thunk<*, *, *>) {
                    try {
                        (action.unsafeCast<Thunk<S, A, WA>>()).run(api::dispatch, api::getState)
                    } catch (exc: Exception) {
                        api.dispatch(errorActionBuilder(action, exc))
                    }
                } else {
                    next(action)
                }
            }
        }
    }
    return ::thunkMiddleware
}
