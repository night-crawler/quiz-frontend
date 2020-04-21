package fm.force.ui

import connected.react.router.connectedRouter
import fm.force.ui.container.app
import kotlinext.js.require
import mu.KotlinLoggingConfiguration
import mu.KotlinLoggingLevel
import react.dom.render
import react.redux.provider
import kotlin.browser.document

val reduxStore = ReduxStore.DEFAULT

fun main() {
    require("codemirror/lib/codemirror.css")
    require("codemirror/theme/darcula.css")
    require("codemirror/theme/idea.css")

    require("codemirror/addon/hint/show-hint.css")
    require("codemirror/addon/hint/show-hint.js")

    require("codemirror/addon/edit/matchbrackets.js")

    require("codemirror/mode/yaml/yaml.js")
    require("codemirror/mode/javascript/javascript.js")
    require("codemirror/mode/clike/clike.js")
    require("codemirror/mode/xml/xml.js")

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
