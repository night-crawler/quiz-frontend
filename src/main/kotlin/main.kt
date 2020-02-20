import connected.react.router.connectedRouter
import fm.force.core.container.app
import fm.force.core.reducer.CustomLocationState
import fm.force.core.reducer.State
import fm.force.core.reducer.combinedReducers
import fm.force.util.composeWithDevTools
import fm.force.util.customEnhancer
import history.createBrowserHistory
import history.locationsAreEqual
import kotlin.browser.document
import react.dom.render
import react.redux.provider
import react.router.connected.routerMiddleware
import redux.RAction
import redux.applyMiddleware
import redux.createStore

val browserHistory = createBrowserHistory<CustomLocationState>()

val store = createStore<State, RAction, dynamic>(
    combinedReducers(browserHistory),
    State(),
    composeWithDevTools(
        customEnhancer(),
        applyMiddleware(routerMiddleware(browserHistory))
    )
)

fun main() {
    val rootElement = document.getElementById("root")!!
    render(rootElement) {
        provider(store) {
            connectedRouter(browserHistory) {
                app() {}
            }
        }
    }
}
