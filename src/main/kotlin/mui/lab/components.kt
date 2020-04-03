package mui.lab

import com.ccfraser.muirwik.components.mTextField
import kotlinext.js.Object
import react.Component
import react.RBuilder
import react.RState
import react.dom.span
import kotlin.reflect.KClass


fun <T> RBuilder.renderSampleAutocomplete(options: List<T>) =
    child(Autocomplete::class as KClass<Component<AutocompleteProps<T>, RState>>) {
        attrs {
            this.asDynamic().debug = true
            this.options = options.toTypedArray()
            renderOption = { option, state ->
                span {
                    +option.toString()
                }
            }
            getOptionLabel = { it.toString() }
            renderInput = { params ->
                mTextField("Sample") {
                    Object.assign(attrs, params)
                }
            }
        }
    }
