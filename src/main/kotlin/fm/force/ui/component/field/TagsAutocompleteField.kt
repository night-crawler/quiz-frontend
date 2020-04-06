package fm.force.ui.component.field

import fm.force.ui.ReduxStore
import fm.force.ui.client.dto.TagFullDTO
import fm.force.ui.client.dto.TagPatchDTO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import mui.lab.labAutocompleteField
import org.w3c.dom.events.Event
import react.dom.span
import react.functionalComponent
import react.useEffect
import react.useState
import redux.form.WrappedFieldProps

interface TagsAutocompleteFieldProps : WrappedFieldProps {
    val label: String
}

/**
 * This function is used to convert weirdly-typed JS/Kotlin array to a new one.
 * 1. We have a ReduxField that renders an Autocomplete component wrapper.
 * 2. Whenever the autocomplete wrapper emits onChange, it passes it up to the ReduxField.
 * 3. ReduxField updates the Store.
 * 4. On the next render we receive in the Autocomplete's props our own writes.
 * 5. If we put them back, we get the error:
 *      kotlinx-coroutines-core.js?31b6:35773 Uncaught (in promise) TypeError:
 *      (intermediate value)(intermediate value)(intermediate value).filter is not a function
 *
 * Interesting fact is that it has nothing to do with coroutines.
 * So, officially, it's a bad ad-hoc solution
 *
 * @param T
 * @return
 */
fun <T>Any.toTypedList(): List<T> {
    val dyn = this.asDynamic()
    // it's a default value for inputs
    if (jsTypeOf(this) == "string") {
        if (dyn.length != 0) {
            throw IllegalArgumentException("It does not seem to be a default input value: $this")
        }
        return emptyList()
    }

    if (dyn.constructor.name == "Array") {
        val result = mutableListOf<T>()
        for (i in 0 until dyn.length) {
            result.add(dyn[i])
        }
        return result.toList()
    }

    return emptyList()
}

val TagsAutocompleteField = functionalComponent<TagsAutocompleteFieldProps> { props ->
    val (searchText, setSearchText) = useState("")
    val (tags, setTags) = useState(listOf<TagFullDTO>())

    useEffect(listOf(searchText)) {
        GlobalScope.promise {
            ReduxStore.DEFAULT.client.findTags().content.toList().apply(setTags)
        }
    }

    labAutocompleteField("Tags", tags.toList()) {
        attrs {
            value = props.input.value.toTypedList<TagFullDTO>().toTypedArray()
            onInputChange = { _, text, _ -> setSearchText(text) }
            onKeyPress = { event ->
                if (event.key == "Enter" && searchText.isNotEmpty()) {
                    GlobalScope.promise {
                        val tag = ReduxStore.DEFAULT.client.createTag(TagPatchDTO(searchText))
                        console.log(tag)
                    }
                }
            }
            renderOption = { option, _ ->
                span { +option.name }
            }
            getOptionLabel = { it.name }
            onChange = { event: Event, value: TagFullDTO, reason: String ->
                props.input.onChange(value)
            }
            getOptionSelected = { option, value ->
                option.name == value.name
            }
        }
    }
}
