package fm.force.ui.util

import kotlin.browser.document

fun escapeHtml(html: String): String {
    val text = document.createTextNode(html)
    val p = document.createElement("p");
    p.appendChild(text)
    return p.innerHTML
}
