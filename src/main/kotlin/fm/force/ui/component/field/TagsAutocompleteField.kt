package fm.force.ui.component.field

import fm.force.ui.ReduxStore
import fm.force.ui.client.dto.TagFullDTO
import fm.force.ui.client.dto.TagPatchDTO
import fm.force.ui.component.form.TagDTO
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


val TagsAutocompleteField = functionalComponent<TagsAutocompleteFieldProps> { props ->
    val (searchText, setSearchText) = useState("")
    val (tags, setTags) = useState(mutableListOf<TagFullDTO>())

//    val createdTag = useCreateTag()

    useEffect(listOf(searchText)) {
        GlobalScope.promise {
            ReduxStore.DEFAULT.client.findTags().content.toMutableList().apply(setTags)
        }
    }

    labAutocompleteField("Tags", tags.toList()) {
        attrs {
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
                console.log("event $event")
                console.log("value $value")
                console.log("reason $reason")
            }
            getOptionSelected = { option, value ->
                console.log(option, value)
                true
            }
        }
    }
}
