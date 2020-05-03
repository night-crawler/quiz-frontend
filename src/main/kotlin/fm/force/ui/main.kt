package fm.force.ui

import connected.react.router.connectedRouter
import fm.force.ui.container.app
import kotlin.browser.document
import kotlinext.js.require
import mu.KotlinLoggingConfiguration
import mu.KotlinLoggingLevel
import react.dom.render
import react.redux.provider

val reduxStore = ReduxStore.DEFAULT

fun main() {
    require("prismjs/components/prism-kotlin.js")
    require("prismjs/components/prism-java.js")
    require("prismjs/components/prism-javascript.js")
    require("prismjs/components/prism-json.js")
//    require("prismjs/components/prism-cpp.js")
    require("prismjs/components/prism-dart.js")
    require("prismjs/components/prism-go.js")
    require("prismjs/components/prism-python.js")
    require("prismjs/components/prism-bash.js")
    require("prismjs/components/prism-yaml.js")

    require("prism-themes/themes/prism-darcula.css")
//    require("prismjs/themes/prism.css")

    require("codemirror/lib/codemirror.css")
    require("codemirror/theme/darcula.css")
    require("codemirror/theme/idea.css")

    require("codemirror/addon/hint/show-hint.css")
    require("codemirror/addon/hint/show-hint.js")

    require("codemirror/addon/edit/matchbrackets.js")

    require("codemirror/mode/markdown/markdown.js")

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
