import connected.react.router.connectedRouter
import fm.force.ui.container.app
import fm.force.ui.reducer.CustomLocationState
import fm.force.ui.reducer.State
import fm.force.ui.reducer.combinedReducers
import fm.force.util.ThunkError
import fm.force.util.composeWithDevTools
import fm.force.util.createThunkMiddleware
import fm.force.util.customEnhancer
import history.createBrowserHistory
import kotlin.browser.document
import react.dom.render
import react.redux.provider
import react.router.connected.routerMiddleware
import redux.RAction
import redux.WrapperAction
import redux.applyMiddleware
import redux.createStore

val browserHistory = createBrowserHistory<CustomLocationState>()

val store = createStore<State, RAction, WrapperAction>(
    combinedReducers(browserHistory),
    State(),
    composeWithDevTools(
        applyMiddleware(
            routerMiddleware(browserHistory),
            createThunkMiddleware(1) { action, exc -> ThunkError(action, exc) }
        ),
        customEnhancer()
    )
)

fun main() {
    val rootElement = document.getElementById("root")!!
    render(rootElement) {
        provider(store) {
            connectedRouter(browserHistory) {
                app {}
            }
        }
    }
}
