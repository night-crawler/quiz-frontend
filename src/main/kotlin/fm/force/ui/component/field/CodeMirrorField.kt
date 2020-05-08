package fm.force.ui.component.field

import CodeMirror.EditorConfiguration
import com.ccfraser.muirwik.components.MTypographyColor
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.themeContext
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.extension.CodeLanguage
import fm.force.ui.extension.codeMirrorTheme
import fm.force.ui.hook.UseState
import fm.force.ui.hook.useDebounce
import fm.force.ui.util.jsApply
import kotlinx.css.Color
import kotlinx.css.color
import react.*
import react.codemirror.Controlled
import react.codemirror.IUnControlledCodeMirror
import redux.form.WrappedFieldProps
import styled.css

interface WrappedCodeMirrorFieldProps : WrappedFieldProps, IUnControlledCodeMirror {
    var label: String
    var mode: CodeLanguage
}

val CodeMirrorField = functionalComponent<WrappedCodeMirrorFieldProps> { props ->
    codeMirrorFieldComponent(
        props.label,
        props.mode,
        props.input.value.toString()
    ) { value ->
        props.input.onChange(value)
    }

    props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }

}

interface CodeMirrorFieldComponentProps : RProps {
    var label: String
    var value: String
    var codeLanguage: CodeLanguage
    var onChange: (String) -> Unit
}

val qwe = functionalComponent<CodeMirrorFieldComponentProps> {props ->
    var state by UseState(props.value)
    val debouncedText = useDebounce(state, 500)

    useEffect(listOf(debouncedText)) {
        props.onChange(debouncedText)
    }

    themeContext.Consumer { theme ->
        val cmOptions = jsApply<EditorConfiguration> {
            this.asDynamic().matchBrackets = true
            this.theme = theme.codeMirrorTheme
            mode = props.codeLanguage.codeMirrorModeName
            if (props.codeLanguage == CodeLanguage.MARKDOWN) {
                extraKeys = js("""{"Enter": "newlineAndIndentContinueMarkdownList"}""")
            }
            lineNumbers = true
        }
        mFormControl {
            mTypography(color = MTypographyColor.textPrimary) {
                css {
                    color = Color(theme.palette.text.secondary)
                }
                +props.label
            }
            child(Controlled::class) {
                attrs {
                    value = state
                    onBeforeChange = { _, _, value -> state = value }
                    options = cmOptions
                }
            }
        }
    }
}

fun RBuilder.codeMirrorFieldComponent(
    label: String, mode: CodeLanguage,
    value: String,
    onChange: (String) -> Unit
) = child(qwe) {
    attrs {
        this.label = label
        this.codeLanguage = mode
        this.value = value
        this.onChange = onChange
    }
}
