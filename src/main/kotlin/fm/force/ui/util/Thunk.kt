package fm.force.ui.util

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import redux.MiddlewareApi
import redux.RAction

interface Thunk<S, A, R, X> : RAction {
    suspend fun run(originalAction: RAction, dispatch: (A) -> R, getState: () -> S, extra: X): R
}

interface ThunkCheckedException

class ThunkError(val originalAction: RAction, val exception: Throwable) : RAction

fun <S, A, WA, X> createThunkMiddleware(
    extra: X,
    errorActionBuilder: (A, Throwable) -> A
): (MiddlewareApi<S, A, WA>) -> ((A) -> WA) -> (A) -> WA {
    fun thunkMiddleware(api: MiddlewareApi<S, A, WA>): ((A) -> WA) -> (A) -> WA {
        return { next ->
            { action ->
                if (action is Thunk<*, *, *, *>) {
                    val promise = GlobalScope.promise {
                        try {
                            val thunk = (action.unsafeCast<Thunk<S, A, WA, X>>())
                            thunk.run(action, api::dispatch, api::getState, extra)
                        } catch (exc: Throwable) {
                            // in some cases we need to propagate this exception, because some JavaScript code
                            // relies on rejected() promise values, e.g., redux-form
                            if (exc is ThunkCheckedException) {
                                throw exc
                            }

                            console.error("Thunk processing failed", action, exc)
                            api.dispatch(errorActionBuilder(action, exc))
                        }
                    }
                    promise.unsafeCast<WA>()
                } else {
                    next(action)
                }
            }
        }
    }
    return ::thunkMiddleware
}
