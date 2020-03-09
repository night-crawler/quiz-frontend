package fm.force.ui.component.field

import com.ccfraser.muirwik.components.MTextFieldProps
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mTextField
import fm.force.ui.client.dto.FieldError
import kotlinx.css.Color
import kotlinx.css.LinearDimension
import kotlinx.css.color
import kotlinx.css.fontSize
import kotlinx.css.margin
import kotlinx.css.padding
import kotlinx.html.InputType
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.li
import react.dom.ul
import redux.form.WrappedFieldProps
import styled.css
import styled.styledDiv

interface WrappedMTextFieldProps : WrappedFieldProps, MTextFieldProps {
    var fieldType: InputType
    var variant: MFormControlVariant
}

class WrappedMTextField(props: WrappedMTextFieldProps) : RComponent<WrappedMTextFieldProps, RState>(props) {
    override fun RBuilder.render() {
        val hasError = props.meta.error != undefined
        @Suppress("UnsafeCastFromDynamic")
        mTextField(
            defaultValue = props.input.value.asDynamic(),
            helperText = props.helperText,
            error = hasError,
            label = props.label,
            name = props.input.name,
            type = props.fieldType,
            variant = props.variant
        ) {
            attrs {
                onBlur = props.input.onBlur
                onFocus = props.input.onFocus.asDynamic()
            }
        }

        props.meta.error?.let {
            styledDiv {
                css {
                    margin = "0"
                    padding = "0"
                    color = Color.red
                    fontSize = LinearDimension.inherit
                }
                ul {
                    it.unsafeCast<List<FieldError>>().map { fieldError ->
                        li {
                            +fieldError.message
                        }
                    }
                }
            }
        }
    }
}
