package fm.force.ui.component.field

import com.ccfraser.muirwik.components.MTextFieldProps
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mTextFieldMultiLine
import fm.force.quiz.common.dto.FieldError
import react.functionalComponent
import redux.form.WrappedFieldProps

interface WrappedMultilineFieldProps : WrappedFieldProps, MTextFieldProps {
    var variant: MFormControlVariant
}

@Suppress("UnsafeCastFromDynamic")
val WrappedMultilineField = functionalComponent<WrappedMultilineFieldProps> { props ->
    val hasError = props.meta.error != undefined

    mTextFieldMultiLine(
        rows = props.rows,
        rowsMax = props.rowsMax,
        defaultValue = props.input.value.asDynamic(),
        helperText = props.helperText,
        error = hasError,
        label = props.label,
        name = props.input.name,
        variant = props.variant
    ) {
        attrs {
            onBlur = props.input.onBlur
            onFocus = props.input.onFocus.asDynamic()
        }
    }

    props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
}
