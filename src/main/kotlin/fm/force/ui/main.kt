package fm.force.ui

import connected.react.router.connectedRouter
import fm.force.ui.container.app
import kotlin.browser.document
import react.dom.render
import react.redux.provider

val reduxStore = ReduxStore.DEFAULT

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
