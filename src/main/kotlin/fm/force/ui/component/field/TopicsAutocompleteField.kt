package fm.force.ui.component.field

import com.ccfraser.muirwik.components.mTextField
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.quiz.common.dto.TopicPatchDTO
import fm.force.ui.hook.callApi
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDebounce
import kotlinext.js.Object
import mui.lab.labAutocompleteMultipleField
import react.dom.span
import react.functionalComponent
import react.useState
import redux.form.WrappedFieldProps
import redux.form.getArrayValue

interface TopicsAutocompleteFieldProps : WrappedFieldProps {
    var label: String
}

val TopicsAutocompleteField = functionalComponent<TopicsAutocompleteFieldProps> { props ->
    val (searchText, setSearchText) = useState("")
    val debouncedSearchText = useDebounce(searchText, 500)

    val topics = useClient(listOf(debouncedSearchText)) {
        findTopics(debouncedSearchText).content.toList()
    } ?: listOf()

    labAutocompleteMultipleField(props.label, topics) {
        attrs {
            freeSolo = true
            value = props.input.getArrayValue()
            onInputChange = { _, text, _ -> setSearchText(text) }
            renderOption = { option, _ ->
                span { +option.title }
            }
            getOptionLabel = { it.title }
            onChange = { _, value, reason, _ ->
                if (reason == "create-option") {
                    callApi {
                        val topic = getOrCreateTopic(TopicPatchDTO(searchText.trim()))
                        props.input.onChange(props.input.getArrayValue<TopicFullDTO>() + listOf(topic))
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
                    option.title == value.title -> true
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
