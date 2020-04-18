package fm.force.ui.component

import com.ccfraser.muirwik.components.*
import fm.force.ui.util.IconName
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.title
import styled.StyleSheet
import styled.css
import styled.styledDiv

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
