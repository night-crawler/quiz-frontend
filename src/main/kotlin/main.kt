import fm.force.core.components.app
import react.dom.*
import react.dom.render
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        app()
    }
}
