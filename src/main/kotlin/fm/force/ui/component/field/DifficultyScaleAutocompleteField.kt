package fm.force.ui.component.field

import com.ccfraser.muirwik.components.mTextField
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDebounce
import kotlinext.js.Object
import mui.lab.labAutocompleteField
import react.dom.span
import react.functionalComponent
import react.useState
import redux.form.WrappedFieldProps

interface DifficultyScalesAutocompleteFieldProps : WrappedFieldProps {
    var label: String
}

val DifficultyScalesAutocompleteField = functionalComponent<DifficultyScalesAutocompleteFieldProps> { props ->
    val (searchText, setSearchText) = useState("")
    val debouncedSearchText = useDebounce(searchText, 500)

    val difficultyScales = useClient(listOf(debouncedSearchText)) {
        findDifficultyScales(
            DefaultSearchCriteria(
                query = debouncedSearchText,
                pageSize = 100
            )
        ).content.toList()
    } ?: listOf()

    labAutocompleteField(props.label, difficultyScales) {
        attrs {
            freeSolo = true
            value = if (props.input.value == "") null else props.input.value
            onInputChange = { _, text, _ -> setSearchText(text) }
            renderOption = { option, _ ->
                span { +option.name }
            }
            getOptionLabel = { it.name }
            onChange = { _, value, _ ->
                props.input.onChange(value)
            }
            getOptionSelected = { option, value ->
                when {
                    option == undefined || value == undefined -> false
                    option.name == value.name -> true
                    else -> false
                }
            }
            renderInput = { params ->
                mTextField(label = props.label) {
                    Object.assign(attrs, params)
                    attrs.error = props.meta.error != undefined
                }
            }
        }
    }
}
