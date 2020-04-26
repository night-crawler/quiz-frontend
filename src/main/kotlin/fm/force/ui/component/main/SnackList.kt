package fm.force.ui.component.main

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.MIconFontSize
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mSnackbar
import fm.force.ui.reducer.action.Snack
import kotlinx.css.VerticalAlign
import kotlinx.css.verticalAlign
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.span
import styled.css
import styled.styledSpan

val altBuilder = RBuilder()

interface SnackListProps : RProps {
    var snacks: List<Snack>
    var onCloseSnack: (key: String) -> Unit
}

class SnackList(props: SnackListProps) : RComponent<SnackListProps, RState>(props) {

    override fun RBuilder.render() {
        if (props.snacks.isEmpty())
            return

        val snack = props.snacks.first()

        mSnackbar(
            altBuilder.div {
                mIcon(snack.iconName.iconMame, fontSize = MIconFontSize.small)
                styledSpan {
                    css {
                        verticalAlign = VerticalAlign.top
                    }
                    +snack.title
                }
            },
            open = true,
            key = snack.key,
            horizAnchor = snack.horizAnchor,
            vertAnchor = snack.vertAnchor,
            autoHideDuration = snack.timeout,
            onClose = { _, _ -> props.onCloseSnack(snack.key) }
        ) {
            attrs.action = altBuilder.div {
                span { +snack.text }
                mIconButton("close", onClick = { props.onCloseSnack(snack.key) }, color = MColor.inherit)
            }
        }
    }
}
