package fm.force.ui.component.field

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TagPatchDTO
import fm.force.ui.ReduxStore
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDebounce
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import mu.KotlinLogging
import mui.lab.labAutocompleteMultipleField
import react.dom.span
import react.functionalComponent
import react.useState
import redux.form.WrappedFieldProps
import redux.form.getArrayValue

private val logger = KotlinLogging.logger("TagsAutocompleteField")

interface TagsAutocompleteFieldProps : WrappedFieldProps {
    var label: String
}

val TagsAutocompleteField = functionalComponent<TagsAutocompleteFieldProps> { props ->
    val (searchText, setSearchText) = useState("")
    val debouncedSearchText = useDebounce(searchText, 500)

    val tags = useClient(listOf(debouncedSearchText)) {
        findTags(debouncedSearchText).content.toList()
    } ?: listOf()

    labAutocompleteMultipleField(props.label, tags) {
        attrs {
            freeSolo = true
            value = props.input.getArrayValue()
            onInputChange = { _, text, _ -> setSearchText(text) }
            renderOption = { option, _ ->
                span { +option.name }
            }
            getOptionLabel = { it.name }
            onChange = { _, value, reason, _ ->
                logger.debug { "Triggered onChange: $value ($reason)" }
                if (reason == "create-option") {
                    GlobalScope.promise {
                        val tag = ReduxStore.DEFAULT.client.getOrCreateTag(TagPatchDTO(searchText.trim()))
                        props.input.onChange(props.input.getArrayValue<TagFullDTO>() + listOf(tag))
                        Unit
                    }
                } else {
                    props.input.onChange(value)
                }
                Unit
            }
            getOptionSelected = { option, value ->
                when {
                    option == undefined || value == undefined -> false
                    option.name == value.name -> true
                    else -> false
                }
            }
        }
    }
}
