package fm.force.util

import kotlin.browser.window
import kotlin.reflect.KProperty1
import kotlinext.js.Object
import kotlinext.js.assign
import kotlinext.js.js
import redux.Action
import redux.Enhancer
import redux.RAction
import redux.Reducer
import redux.Store
import redux.StoreCreator
import redux.WrapperAction
import redux.combineReducers
import redux.compose

/**
 * Helper function that combines reducers using [combineReducers] where the keys in the map are
 * properties of the state object instead of strings with the name of the state's properties
 * this helper function has 2 advantages over the original:
 *
 * 1. It is less error-prone, when you change the name of the property of the state you must change the
 * corresponding key or you will get a compile error.
 * 2. The compiler is now able to infer the [S] type parameter which means it is no longer needed to provide the 2 type parameters explicitly.
 *
 * @param S state
 * @param A action
 * @param R state property type
 *
 * @param reducers map where the key is the state property and the value is the reducer for said property.
 *
 * @return the combined reducer.
 *
 */
fun <S, A, R> customCombineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> {
    return combineReducers(reducers.mapKeys { it.key.name })
}

fun <S> customEnhancer(): Enhancer<S, Action, Action, RAction, WrapperAction> = { next ->
    { reducer, initialState ->
        fun wrapperReducer(reducer: Reducer<S, RAction>): Reducer<S, WrapperAction> = { state, action ->
            if (!action.asDynamic().isKotlin as Boolean) {
                reducer(state, action.asDynamic().unsafeCast<RAction>())
            } else {
                reducer(state, action.action)
            }
        }

        val nextStoreCreator = next.unsafeCast<StoreCreator<S, WrapperAction, WrapperAction>>()
        val store = nextStoreCreator(
            wrapperReducer(reducer),
            // we need this cast to get rid of annoying type check performed by redux itself:
            // The previous state received by the reducer has unexpected type of "Object".
            // Expected argument to be an object with the following keys: "appPreferences", "router"
            Object.assign(js {}, initialState) as S
        )

        assign(Object.assign(js {}, store)) {
            dispatch = { action: dynamic ->
                // original redux actions use `type` keyword, so we don't reshape them
                if (action.type != undefined && action.action == undefined) {
                    store.dispatch(action.unsafeCast<WrapperAction>())
                } else {
                    // it's a Kotlin action, so we'll reshape it and provide a marker for the wrapper
                    store.dispatch(
                        js {
                            type = action::class.simpleName
                            isKotlin = true
                            this.action = action
                        }.unsafeCast<WrapperAction>()
                    )
                }
            }
            replaceReducer = { nextReducer: Reducer<S, RAction> ->
                store.replaceReducer(wrapperReducer(nextReducer))
            }
        }.unsafeCast<Store<S, RAction, WrapperAction>>()
    }
}

@Suppress("UnsafeCastFromDynamic")
fun <A, T1, R> composeWithDevTools(function1: (T1) -> R, function2: (A) -> T1): (A) -> R {
    return if (window.asDynamic().__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ != undefined) {
        window.asDynamic().__REDUX_DEVTOOLS_EXTENSION_COMPOSE__(function1, function2)
    } else {
        compose(function1, function2)
    }
}
