package fm.force.core.util

import kotlin.browser.window
import redux.compose

@Suppress("UnsafeCastFromDynamic")
fun <A, T1, R> composeWithDevTools(function1: (T1) -> R, function2: (A) -> T1): (A) -> R {
    return if (window.asDynamic().__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ != undefined) {
        window.asDynamic().__REDUX_DEVTOOLS_EXTENSION_COMPOSE__(function1, function2)
    } else {
        compose(function1, function2)
    }
}
