package fm.force.ui.component

import com.ccfraser.muirwik.components.MIconFontSize
import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import fm.force.util.IconName
import kotlinx.css.TextAlign
import kotlinx.css.em
import kotlinx.css.fontSize
import kotlinx.css.padding
import kotlinx.css.paddingBottom
import kotlinx.css.textAlign
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.title
import styled.StyleSheet
import styled.css
import styled.styledDiv
import styled.styledH3

class NotFound(props: RProps) : RComponent<RProps, RState>(props) {
    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val typographyStyle by ComponentStyles.css {
            fontSize = 1.em
            paddingBottom = 2.spacingUnits
        }
    }
    private val message = "Requested page was not found on this server"

    override fun RBuilder.render() {
        styledDiv {
            helmet {
                title(message)
            }

            css {
                padding(3.spacingUnits)
                textAlign = TextAlign.left
            }
            mTypography(variant = MTypographyVariant.h4) {
                css { paddingBottom = 3.spacingUnits }
                mIcon("sync_problem", fontSize = MIconFontSize.large)
                +message
            }
            mTypography {
                css(ComponentStyles.typographyStyle)
                +"It's likely this route is quite outdated. But still can you enjoy Material Icons!"
            }

            IconName.values().map {
                mIcon(iconName = it.iconMame, fontSize = MIconFontSize.small)
            }
        }
    }
}

fun RBuilder.notFound() = child(NotFound::class) {}
