package fm.force.ui.component.field

import com.ccfraser.muirwik.components.MCheckboxProps
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mCheckboxWithLabel
import fm.force.quiz.common.dto.FieldError
import kotlinx.html.InputType
import react.functionalComponent
import redux.form.WrappedFieldProps

interface WrappedCheckboxFieldProps : WrappedFieldProps, MCheckboxProps {
    var fieldType: InputType
    var variant: MFormControlVariant
    var label: String
}

@Suppress("UnsafeCastFromDynamic")
val WrappedCheckboxField = functionalComponent<WrappedCheckboxFieldProps> { props ->
    mCheckboxWithLabel(
        checked = props.input.value.unsafeCast<Boolean>(),
        label = props.label
    ) {
        attrs {
            onBlur = props.input.onBlur
            onFocus = props.input.onFocus.asDynamic()
            onChange = props.input.onChange
        }
    }

    props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
}
