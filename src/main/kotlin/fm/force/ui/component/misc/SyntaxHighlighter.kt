package fm.force.ui.component.misc

import com.ccfraser.muirwik.components.themeContext
import fm.force.ui.extension.syntaxHighlightStyle
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.syntax.highlighter.PrismSyntaxHighlighter

interface SyntaxHighlighterProps : RProps {
    var code: String
    var language: String
}

class SyntaxHighlighter(props: SyntaxHighlighterProps) : RComponent<SyntaxHighlighterProps, RState>(props) {
    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            child(PrismSyntaxHighlighter::class) {
                attrs {
                    this.asDynamic().children = props.code
                    language = props.language
                    style = theme.syntaxHighlightStyle
                }
            }
        }
    }
}

fun RBuilder.syntaxHighlighter(code: String, language: String) {
    child(SyntaxHighlighter::class) {
        attrs {
            this.code = code
            this.language = language
        }
    }
}
