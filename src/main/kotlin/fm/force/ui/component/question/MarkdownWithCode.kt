package fm.force.ui.component.question

import fm.force.ui.util.escapeHtml
import fm.force.ui.util.jsApply
import markdown.it.MarkdownIt
import markdown.it.mkMarkdown
import prismjs.highlight
import prismjs.languages
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.InnerHTML
import sanitize.html.IOptions
import sanitize.html.sanitize
import styled.styledDiv


private val markdownRenderer = mkMarkdown(jsApply<MarkdownIt.Options> {
    highlight = { str, lang ->
        val highlightedCode = try {
            highlight(str, languages.asDynamic()[lang], lang)
        } catch (ex: Throwable) {
            escapeHtml(str)
        }
        "<pre class='language-$lang'>$highlightedCode</pre>"
    }
})


private val sanitizeOptions = jsApply<IOptions> {
    allowedTags = arrayOf(
        "b", "i", "em", "strong", "a", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "code",
        "span"
    )
    allowedAttributes = jsApply {
        span = arrayOf("class")
        code = arrayOf("class")
        pre = arrayOf("class")
    }
}

interface MarkdownWithCodeProps : RProps {
    var code: String
}

class MarkdownWithCode(props: MarkdownWithCodeProps) : RComponent<MarkdownWithCodeProps, RState>(props) {
    override fun RBuilder.render() {
        val highlightedCode = markdownRenderer.render(props.code)
        styledDiv {
            attrs["dangerouslySetInnerHTML"] = InnerHTML(
                sanitize(highlightedCode, sanitizeOptions)
            )
        }
    }
}

fun RBuilder.markdownWithCode(code: String) = child(MarkdownWithCode::class) {
    attrs {
        this.code = code
    }
}