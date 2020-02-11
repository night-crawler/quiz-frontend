import fm.force.core.components.app
import kotlin.browser.document
import react.dom.render

fun main() {
    render(document.getElementById("root")) {
        app()
    }
}
