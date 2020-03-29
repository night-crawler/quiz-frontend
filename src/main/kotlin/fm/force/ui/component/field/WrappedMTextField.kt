package fm.force.ui.component.field

import com.ccfraser.muirwik.components.MTextFieldProps
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mTextField
import fm.force.ui.client.dto.FieldError
import kotlinx.html.InputType
import react.functionalComponent
import redux.form.WrappedFieldProps

interface WrappedTextFieldProps : WrappedFieldProps, MTextFieldProps {
    var fieldType: InputType
    var variant: MFormControlVariant
}

@Suppress("UnsafeCastFromDynamic")
val WrappedTextField = functionalComponent<WrappedTextFieldProps> { props ->
    val hasError = props.meta.error != undefined
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

    props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
}
