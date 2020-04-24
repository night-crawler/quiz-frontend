package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.targetInputValue
import fm.force.ui.client.QuestionSearchCriteria
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import kotlinx.css.margin
import kotlinx.css.padding
import react.RBuilder
import react.child
import react.functionalComponent
import react.useEffect
import styled.css

interface QuestionSearchBoxProps : StyledPropsWithCommonAttributes {
    var initialCriteria: QuestionSearchCriteria
    var onSearchCriteriaChange: (newCriteria: QuestionSearchCriteria) -> Unit
    var onHeightChange: ((newHeight: Int) -> Unit)?
}

val QuestionSearchBox = functionalComponent<QuestionSearchBoxProps> { props ->
    var criteria by UseState(props.initialCriteria)
    val debouncedCriteria = useDebounce(criteria, 500)

    useEffect(listOf(debouncedCriteria.hashCode())) {
        if (debouncedCriteria != props.initialCriteria)
            props.onSearchCriteriaChange(debouncedCriteria)
    }

    mTextField("Search", fullWidth = true, defaultValue = criteria.query, margin = MFormControlMargin.none) {
        ref { if (it != null) props.onHeightChange?.invoke(it.clientHeight) }
        attrs {
            onChange = { criteria = criteria.copy(query = it.targetInputValue) }
        }
    }
}

fun RBuilder.questionSearchBox(
    initialCriteria: QuestionSearchCriteria,
    onSearchCriteriaChange: (newCriteria: QuestionSearchCriteria) -> Unit,
    onHeightChange: ((newHeight: Int) -> Unit)? = null
) = child(QuestionSearchBox) {
    attrs {
        this.initialCriteria = initialCriteria
        this.onSearchCriteriaChange = onSearchCriteriaChange
        this.onHeightChange = onHeightChange
    }
}
