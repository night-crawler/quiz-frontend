package fm.force.ui.component.field

import fm.force.quiz.common.dto.FieldError
import mui.lab.labAutocompleteField
import react.RBuilder
import react.RComponent
import react.RState
import redux.form.WrappedFieldProps

interface ReduxAutocompleteProps<T> : WrappedFieldProps {
    var options: List<T>
    val label: String
}

/**
 * MOAR GENERICS
 */
class ReduxAutocomplete<T>(props: ReduxAutocompleteProps<T>) : RComponent<ReduxAutocompleteProps<T>, RState>(props) {
    override fun RBuilder.render() {
        val hasError = props.meta.error != undefined

        labAutocompleteField(
            label = props.label,
            options = props.options
        )

        props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
    }
}
