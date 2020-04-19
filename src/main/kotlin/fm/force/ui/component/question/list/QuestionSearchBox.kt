package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.targetInputValue
import kotlinx.css.margin
import kotlinx.css.padding
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler
import styled.css

interface QuestionSearchBoxProps : StyledPropsWithCommonAttributes {
    var onSearchTextChange: (newText: String) -> Unit
    var onHeightChange: (newHeight: Int) -> Unit
}

fun RBuilder.searchBox(handler: StyledHandler<QuestionSearchBoxProps>) = createStyled(QuestionSearchBox::class) {
    handler(this)
}

class QuestionSearchBox(props: QuestionSearchBoxProps) : RComponent<QuestionSearchBoxProps, RState>(props) {
    override fun RBuilder.render() {
        mTextField("Search", fullWidth = true, margin = MFormControlMargin.none) {
            ref { if (it != null) props.onHeightChange(it.clientHeight) }
            css {
                padding = "0px"
                margin = "0px"
            }
            attrs {
                onChange = { props.onSearchTextChange(it.targetInputValue)  }
            }
        }
    }
}
