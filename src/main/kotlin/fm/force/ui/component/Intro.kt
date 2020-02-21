package fm.force.ui.component

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.spacingUnits
import fm.force.ui.component.Intro.ComponentStyles.typographyStyle
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
import react.dom.a
import react.dom.title
import styled.StyleSheet
import styled.css
import styled.styledDiv

class Intro : RComponent<RProps, RState>() {

    private object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val typographyStyle by css {
            fontSize = 1.em
            paddingBottom = 2.spacingUnits
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            helmet {
                title("SAMPLE")
            }
            css {
                padding(3.spacingUnits)
                textAlign = TextAlign.left
            }
            mTypography("Welcome to Muirwik", MTypographyVariant.h3) { css { paddingBottom = 3.spacingUnits } }
            mTypography {
                css(typographyStyle)
                +"Muirwik gets it name from being a "
                a("https://material-ui.com/", "_black") { +"Material UI" }
                +" "
                a("https://reactjs.org/", "_black") { +"React" }
                +" wrapper (written) in "
                a("https://kotlinlang.org/", "_black") { +"Kotlin" }
                +"."
            }

            mTypography(
                "This project is a test/playground area for the Muirwik components. It is modeled loosely " +
                    "off the Material UI demo pages and uses their source as a reference, however some pages have a bit " +
                    "more experimentation going on at the expense of a highly tuned visual experience."
            ) {
                css(typographyStyle)
            }

            mTypography {
                css(typographyStyle)
                +"For more information, see the above links (particularly the Material UI one) and this project's "
                a(href = "#") { +"Github page" }
                +"."
            }
        }
    }
}

fun RBuilder.intro() = child(Intro::class) {}
