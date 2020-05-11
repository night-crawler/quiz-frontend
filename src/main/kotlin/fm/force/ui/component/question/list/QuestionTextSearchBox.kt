package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.styles.Breakpoint
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.ui.client.QuestionSearchCriteria
import fm.force.ui.component.field.TagsAutocompleteField
import fm.force.ui.component.field.TopicsAutocompleteField
import fm.force.ui.hook.UseState
import fm.force.ui.hook.useDebounce
import fm.force.ui.util.jsApply
import react.RBuilder
import react.child
import react.functionalComponent
import react.useEffect

private val breakpoints = MGridBreakpoints(MGridSize.cells6)
    .up(Breakpoint.lg, MGridSize.cells6)
    .down(Breakpoint.sm, MGridSize.cells12)

interface QuestionTextSearchBoxProps : StyledPropsWithCommonAttributes {
    var initialCriteria: QuestionSearchCriteria
    var onSearchCriteriaChange: (newCriteria: QuestionSearchCriteria) -> Unit
    var onHeightChange: ((newHeight: Int) -> Unit)?
}

val QuestionTextSearchBox = functionalComponent<QuestionTextSearchBoxProps> { props ->
    var criteria by UseState(props.initialCriteria)
    val debouncedCriteria = useDebounce(criteria, 500)

    useEffect(listOf(debouncedCriteria.hashCode())) {
        if (debouncedCriteria != props.initialCriteria)
            props.onSearchCriteriaChange(debouncedCriteria)
    }

    mGridContainer {
        mGridItem(breakpoints) {
            child(TagsAutocompleteField) {
                attrs {
                    input = jsApply {
                        label = "Tags"
                        value = props.initialCriteria.tags.toTypedArray()
                        onChange = { tags: Array<TagFullDTO> ->
                            criteria = criteria.copy(tags = tags.toList())
                        }
                    }
                }
            }
        }

        mGridItem(breakpoints) {
            child(TopicsAutocompleteField) {
                attrs {
                    input = jsApply {
                        label = "Topics"
                        value = props.initialCriteria.topics.toTypedArray()
                        onChange = { topics: Array<TopicFullDTO> ->
                            criteria = criteria.copy(topics = topics.toList())
                        }
                        meta = jsApply {
                            error = undefined
                        }
                    }
                }
            }
        }
    }


    mTextField("Search", fullWidth = true, defaultValue = criteria.query, margin = MFormControlMargin.none) {
        ref { if (it != null) props.onHeightChange?.invoke(it.clientHeight) }
        attrs {
            onChange = { criteria = criteria.copy(query = it.targetInputValue) }
        }
    }
}

fun RBuilder.questionTextSearchBox(
    initialCriteria: QuestionSearchCriteria,
    onSearchCriteriaChange: (newCriteria: QuestionSearchCriteria) -> Unit,
    onHeightChange: ((newHeight: Int) -> Unit)? = null
) = child(QuestionTextSearchBox) {
    attrs {
        this.initialCriteria = initialCriteria
        this.onSearchCriteriaChange = onSearchCriteriaChange
        this.onHeightChange = onHeightChange
    }
}
