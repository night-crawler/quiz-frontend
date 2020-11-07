package fm.force.ui.component.question

import com.ccfraser.muirwik.components.themeContext
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
import styled.css
import styled.styledDiv

private val markdownRenderer = mkMarkdown(
    jsApply<MarkdownIt.Options> {
        highlight = { str, lang ->
            val highlightedCode = try {
                highlight(str, languages.asDynamic()[lang], lang)
            } catch (ex: Throwable) {
                escapeHtml(str)
            }
            "<pre class='language-$lang'>$highlightedCode</pre>"
        }
    }
)

private val sanitizeOptions = jsApply<IOptions> {
    allowedTags = arrayOf(
        "b", "i", "em", "strong", "a", "h1", "h2", "h3", "h4", "h5", "h6",
        "p", "blockquote", "pre", "code",
        "ol", "ul", "li",
        "img",
        "span"
    )
    allowedAttributes = jsApply {
        span = arrayOf("class")
        code = arrayOf("class")
        img = arrayOf("src", "alt")
        a = arrayOf("href", "title")
        pre = arrayOf("class")
    }
}

interface MarkdownWithCodeProps : RProps {
    var code: String
}

class MarkdownWithCode(props: MarkdownWithCodeProps) : RComponent<MarkdownWithCodeProps, RState>(props) {
    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            val highlightedCode = markdownRenderer.render(props.code)
            styledDiv {
                attrs["dangerouslySetInnerHTML"] = InnerHTML(
                    sanitize(highlightedCode, sanitizeOptions)
                )
                css { classes.add(theme.palette.type) }
            }
        }
    }
}

fun RBuilder.markdownWithCode(code: String) = child(MarkdownWithCode::class) {
    attrs {
        this.code = code
    }
}
