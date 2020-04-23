package fm.force.ui.component.field

import com.ccfraser.muirwik.components.mTextField
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import kotlinext.js.Object
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import mu.KotlinLogging
import mui.lab.labAutocompleteField
import react.dom.span
import react.functionalComponent
import react.useEffect
import redux.form.WrappedFieldProps

private val logger = KotlinLogging.logger("QuestionsAutocompleteField")

interface QuestionsAutocompleteFieldProps : WrappedFieldProps {
    var label: String
}

val QuestionAutocompleteField = functionalComponent<QuestionsAutocompleteFieldProps> { props ->
    var searchText by UseState("")
    var questions by UseState(listOf<QuestionFullDTO>())
    val debouncedSearchText = useDebounce(searchText, 500)

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            questions = ReduxStore.DEFAULT.client.findQuestions(
                page = 1,
                sort = "title",
                query = debouncedSearchText
            ).content.toList()
        }
    }

    labAutocompleteField(props.label, questions) {
        attrs {
            freeSolo = false
            value = if (props.input.value == "") null else props.input.value
            onInputChange = { _, text, _ -> searchText = text }
            renderOption = { option, _ ->
                span { +option.title }
            }
            getOptionLabel = { if (it.asDynamic() == "") "" else it.title }
            onChange = { _, value, _ ->
                props.input.onChange(value)
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
