package fm.force.ui

import connected.react.router.connectedRouter
import fm.force.ui.container.app
import kotlin.browser.document
import mu.KotlinLoggingConfiguration
import mu.KotlinLoggingLevel
import react.dom.render
import react.redux.provider

val reduxStore = ReduxStore.DEFAULT

fun main() {
    KotlinLoggingConfiguration.LOG_LEVEL = KotlinLoggingLevel.DEBUG

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
