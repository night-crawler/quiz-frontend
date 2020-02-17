package fm.force.core.component

import com.ccfraser.muirwik.components.Colors
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mThemeProvider
import com.ccfraser.muirwik.components.styles.ThemeOptions
import com.ccfraser.muirwik.components.styles.createMuiTheme
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

interface AppProps : RProps {
    var themeType: String
    var onSetThemeType: (themeType: String) -> Unit
}

class App(props: AppProps) : RComponent<AppProps, RState>(props) {
    override fun RBuilder.render() {
        mCssBaseline()

        @Suppress("UnsafeCastFromDynamic")
        val themeOptions: ThemeOptions = js("({palette: { type: 'placeholder', primary: {main: 'placeholder'}}})")
        themeOptions.palette?.type = props.themeType
        themeOptions.palette?.primary.main = Colors.Blue.shade500.toString()

        mThemeProvider(createMuiTheme(themeOptions)) {
            mainFrame("An Intro") { themeType -> props.onSetThemeType(themeType) }
        }
    }
}
