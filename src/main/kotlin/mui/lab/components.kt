package mui.lab

import com.ccfraser.muirwik.components.mTextField
import react.Component
import react.RBuilder
import react.RState
import react.dom.input
import react.dom.span
import kotlin.reflect.KClass

/*
params

id: "mui-autocomplete-59959"
disabled: false
fullWidth: true
size: undefined
InputLabelProps: {id: "mui-autocomplete-59959-label", htmlFor: "mui-autocomplete-59959"}
InputProps: {className: "MuiAutocomplete-inputRoot", startAdornment: undefined, endAdornment: {…}, ref: ƒ}

 */

fun <T> RBuilder.renderSampleAutocomplete(options: List<T>) =
    child(Autocomplete::class as KClass<Component<AutocompleteProps<T>, RState>>) {
        attrs {
            this.asDynamic().debug = true
//            id = "sample-111"
//            freeSolo = true
            autoHighlight = true
//            this.asDynamic().multiple = true
            this.options = options.toTypedArray()
            this.asDynamic().onChange = { console.log("CHANGE") }
            filterOptions = { o, s ->
                console.log("!333", o, s)
                o
            }
//            renderOption = { option, state ->
//                console.log("callme?", option)
//                span("killme") {
//                    +option.toString()
//                }
//            }
            getOptionLabel = { it.toString() }
            renderInput = { params ->
                console.log(params)
                mTextField("Sample") {
                    attrs {
                        id = params.id
                        disabled = params.disabled
                        fullWidth = params.asDynamic().fullWidth
                        size = params.asDynamic().size
                        this.asDynamic().InputLabelProps = params.asDynamic().InputLabelProps
                        this.asDynamic().InputProps = params.asDynamic().InputProps
                        inputProps = params.inputProps
                    }
                }
            }

        }
    }
