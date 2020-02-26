package fm.force.ui.component

import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.styles.PaletteOptions
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.TypographyOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme
import fm.force.util.jsApply
import kotlinext.js.js
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

interface AppProps : RProps {
    var themeType: String
}

class App(props: AppProps) : RComponent<AppProps, RState>(props) {
    companion object {
        val baseThemeOptions = jsApply<ThemeOptions> {
            palette = jsApply<PaletteOptions> {
                primary = js {
                    light = "#63ccff"
                    main = "#009be5"
                    dark = "#006db3"
                }
                typography = jsApply<TypographyOptions> {
                    h5 = jsApply {
                        fontWeight = 500
                        fontSize = 26
                        letterSpacing = 0.5.toString()
                    }
                }
                shape = jsApply {
                    borderRadius = 8
                }
            }
        }
    }

    override fun RBuilder.render() {
        mCssBaseline()
        val themeOptions = baseThemeOptions
        themeOptions.palette?.type = props.themeType
        mThemeProvider(createMuiTheme(themeOptions)) {
            mainFrame()
        }
    }
}
