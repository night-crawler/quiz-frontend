package fm.force.ui.component

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.targetInputValue
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import react.RBuilder
import react.child
import react.functionalComponent
import react.useEffect

interface TextSearchBoxProps : StyledPropsWithCommonAttributes {
    var initialCriteria: DefaultSearchCriteria
    var onSearchCriteriaChange: (newCriteria: DefaultSearchCriteria) -> Unit
    var onHeightChange: ((newHeight: Int) -> Unit)?
}

val TextSearchBox = functionalComponent<TextSearchBoxProps> { props ->
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

fun RBuilder.textSearchBox(
    initialCriteria: DefaultSearchCriteria,
    onSearchCriteriaChange: (newCriteria: DefaultSearchCriteria) -> Unit,
    onHeightChange: ((newHeight: Int) -> Unit)? = null
) = child(TextSearchBox) {
    attrs {
        this.initialCriteria = initialCriteria
        this.onSearchCriteriaChange = onSearchCriteriaChange
        this.onHeightChange = onHeightChange
    }
}
