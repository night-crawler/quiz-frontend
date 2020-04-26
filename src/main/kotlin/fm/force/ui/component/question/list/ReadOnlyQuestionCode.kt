package fm.force.ui.component.question.list

import CodeMirror.EditorConfiguration
import com.ccfraser.muirwik.components.themeContext
import fm.force.ui.extension.CodeLanguage
import fm.force.ui.extension.codeMirrorTheme
import fm.force.ui.util.jsApply
import kotlinx.css.LinearDimension
import kotlinx.css.height
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.codemirror.IUnControlledCodeMirror
import react.codemirror.UnControlled
import styled.StyledElementBuilder
import styled.css

interface ReadOnlyQuestionCodeProps : RProps {
    var text: String
    var codeLanguage: CodeLanguage
    var lineWrapping: Boolean
}

class ReadOnlyQuestionCode(props: ReadOnlyQuestionCodeProps) : RComponent<ReadOnlyQuestionCodeProps, RState>(props) {
    override fun RBuilder.render() {
        themeContext.Consumer { theme ->
            val builder =
                StyledElementBuilder<IUnControlledCodeMirror>(
                    UnControlled::class.js
                )

            val cmOptions =
                jsApply<EditorConfiguration> {
                    readOnly = true
                    lineNumbers = true
                    // we need this to make codemirror component fit the size of the text
                    viewportMargin = Double.POSITIVE_INFINITY
                    mode = props.codeLanguage.codeMirrorModeName
                    this.theme = theme.codeMirrorTheme
                    lineWrapping = props.lineWrapping
                }
            builder.css {
                // we need this to make codemirror component fit the size of the text
                descendants(".CodeMirror") {
                    height = LinearDimension.auto
                }
            }
            builder.attrs {
                value = props.text
                options = cmOptions
            }
            child(builder.create())
        }
    }
}

fun RBuilder.readOnlyQuestionCode(
    text: String,
    codeLanguage: CodeLanguage,
    lineWrapping: Boolean = false
) = child(ReadOnlyQuestionCode::class) {
    attrs {
        this.text = text
        this.codeLanguage = codeLanguage
        this.lineWrapping = lineWrapping
    }
}
