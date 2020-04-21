package fm.force.ui.component.field

import CodeMirror.EditorConfiguration
import com.ccfraser.muirwik.components.MTypographyColor
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.themeContext
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import fm.force.ui.util.jsApply
import kotlinx.css.Color
import kotlinx.css.color
import react.codemirror.IUnControlledCodeMirror
import react.codemirror.UnControlled
import react.functionalComponent
import react.useEffect
import redux.form.WrappedFieldProps
import styled.css

interface WrappedCodeMirrorFieldProps : WrappedFieldProps, IUnControlledCodeMirror {
    var label: String
}

val CodeMirrorField = functionalComponent<WrappedCodeMirrorFieldProps> { props ->
    var rawValue by UseState(props.input.value.toString())
    val debouncedText = useDebounce(rawValue, 500)

    useEffect(listOf(debouncedText)) {
        props.input.onChange(debouncedText)
    }

    themeContext.Consumer { theme ->
        val cmTheme = when (theme.palette.type) {
            "light" -> "idea"
            "dark" -> "darcula"
            else -> "idea"
        }
        val cmOptions = jsApply<EditorConfiguration> {
            this.asDynamic().matchBrackets = true
            mode = "text/x-kotlin"
            this.theme = cmTheme
            lineNumbers = true
        }
        mFormControl {
            mTypography(color = MTypographyColor.textPrimary) {
                css {
                    color = Color(theme.palette.text.secondary)
                }
                +props.label
            }
            child(UnControlled::class) {
                attrs {
                    this.asDynamic().id = "trash"
                    value = props.input.value.unsafeCast<String>()
                    onChange = { _, _, value -> rawValue = value }
                    options = cmOptions
                }
            }
        }
    }
}
