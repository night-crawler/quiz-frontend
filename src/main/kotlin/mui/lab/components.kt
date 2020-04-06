package mui.lab

import com.ccfraser.muirwik.components.mTextField
import kotlinext.js.Object
import react.Component
import react.RBuilder
import react.RElementBuilder
import react.RState
import react.dom.span
import kotlin.reflect.KClass


fun <T> RBuilder.labAutocompleteField(
    label: String,
    options: List<T>,
    rb: (RElementBuilder<AutocompleteProps<T>>.() -> Unit)? = null
) =
    child(Autocomplete::class as KClass<Component<AutocompleteProps<T>, RState>>) {
        attrs {
            this.asDynamic().multiple = true
            this.options = options.toTypedArray()
            renderOption = { option, state ->
                span {
                    +option.toString()
                }
            }
            getOptionLabel = { it.toString() }
            renderInput = { params ->
                mTextField(label = label) {
                    Object.assign(attrs, params)
                }
            }
        }
        rb?.invoke(this)
    }
