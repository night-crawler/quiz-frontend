import fm.force.core.container.app
import fm.force.core.reducer.State
import fm.force.core.reducer.combinedReducers
import fm.force.core.util.composeWithDevTools
import kotlin.browser.document
import react.dom.render
import react.redux.provider
import redux.*

val store = createStore<State, RAction, dynamic>(
    combinedReducers(),
    State(),
    composeWithDevTools(
        applyMiddleware(),
        rEnhancer()
    )
)

fun main() {
    val rootElement = document.getElementById("root")!!
    render(rootElement) {
        provider(store) {
            app() {}
        }
    }
}
