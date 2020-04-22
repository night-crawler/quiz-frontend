package fm.force.ui.component.field

import CodeMirror.EditorConfiguration
import com.ccfraser.muirwik.components.MTypographyColor
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.themeContext
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import fm.force.ui.extension.codeMirrorTheme
import fm.force.ui.util.jsApply
import kotlinx.css.Color
import kotlinx.css.color
import react.codemirror.Controlled
import react.codemirror.IUnControlledCodeMirror
import react.functionalComponent
import react.useEffect
import redux.form.WrappedFieldProps
import styled.css

interface WrappedCodeMirrorFieldProps : WrappedFieldProps, IUnControlledCodeMirror {
    var label: String
}

val CodeMirrorField = functionalComponent<WrappedCodeMirrorFieldProps> { props ->
    var state by UseState(props.input.value.toString())
    val debouncedText = useDebounce(state, 500)

    useEffect(listOf(debouncedText)) {
        props.input.onChange(debouncedText)
    }

    themeContext.Consumer { theme ->
        val cmOptions = jsApply<EditorConfiguration> {
            this.asDynamic().matchBrackets = true
            this.theme = theme.codeMirrorTheme
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
