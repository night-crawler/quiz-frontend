package fm.force.ui.component.field

import com.ccfraser.muirwik.components.MTextFieldProps
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mTextField
import kotlinx.html.InputType
import react.RBuilder
import react.RComponent
import react.RState
import redux.form.WrappedFieldProps

interface WrappedMTextFieldProps : WrappedFieldProps, MTextFieldProps {
    var fieldType: InputType
    var variant: MFormControlVariant
}

class WrappedMTextField(props: WrappedMTextFieldProps) : RComponent<WrappedMTextFieldProps, RState>(props) {
    override fun RBuilder.render() {
        @Suppress("UnsafeCastFromDynamic")
        mTextField(
            defaultValue = props.input.value.asDynamic(),
            helperText = props.helperText,
            error = props.meta.error.asDynamic(),
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
    }
}
