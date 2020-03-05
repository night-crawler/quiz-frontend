package fm.force.ui

import connected.react.router.connectedRouter
import fm.force.ui.container.app
import react.dom.render
import react.redux.provider
import kotlin.browser.document

val reduxStore = ReduxStore.default()

fun main() {
    val rootElement = document.getElementById("root")
    if (rootElement == null) {
        console.error("HTML does not contain the element with id `root`")
        return
    }

    render(rootElement) {
        provider(reduxStore.store) {
            connectedRouter(reduxStore.history) {
                app {}
            }
        }
    }
}
