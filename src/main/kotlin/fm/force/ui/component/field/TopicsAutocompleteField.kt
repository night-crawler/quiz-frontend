package fm.force.ui.component.field

import com.ccfraser.muirwik.components.mTextField
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.quiz.common.dto.TopicPatchDTO
import fm.force.ui.ReduxStore
import fm.force.ui.effect.useDebounce
import kotlinext.js.Object
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import mu.KotlinLogging
import mui.lab.labAutocompleteMultipleField
import react.dom.span
import react.functionalComponent
import react.useEffect
import react.useState
import redux.form.WrappedFieldProps
import redux.form.getArrayValue

private val logger = KotlinLogging.logger("TopicsAutocompleteField")

interface TopicsAutocompleteFieldProps : WrappedFieldProps {
    var label: String
}

val TopicsAutocompleteField = functionalComponent<TopicsAutocompleteFieldProps> { props ->
    val (searchText, setSearchText) = useState("")
    val (Topics, setTopics) = useState(listOf<TopicFullDTO>())
    val debouncedSearchText = useDebounce(searchText, 500)

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            ReduxStore.DEFAULT.client.findTopics(debouncedSearchText).content.toList().apply(setTopics)
        }
    }

    labAutocompleteMultipleField(props.label, Topics) {
        attrs {
            freeSolo = true
            value = props.input.getArrayValue()
            onInputChange = { _, text, _ -> setSearchText(text) }
            renderOption = { option, _ ->
                span { +option.title }
            }
            getOptionLabel = { it.title }
            onChange = { _, value, reason, _ ->
                logger.debug { "Triggered onChange: $value ($reason)" }
                if (reason == "create-option") {
                    GlobalScope.promise {
                        val topic = ReduxStore.DEFAULT.client
                            .getOrCreateTopic(TopicPatchDTO(searchText.trim()))
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
