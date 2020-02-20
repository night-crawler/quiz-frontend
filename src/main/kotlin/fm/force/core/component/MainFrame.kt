package fm.force.core.component

import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import com.ccfraser.muirwik.components.themeContext
import fm.force.core.container.appBar
import fm.force.core.container.drawer
import fm.force.core.container.mainContainer
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

interface MainFrameProps : RProps {
    var onThemeTypeChange: (themeType: String) -> Unit
    var initialView: String
}

class MainFrame(props: MainFrameProps) : RComponent<MainFrameProps, RState>(props) {

    override fun RBuilder.render() {
        mCssBaseline()

        themeContext.Consumer { theme ->
            styledDiv {
                css {
                    flexGrow = 1.0
                    width = 100.pct
                    zIndex = 1
                    overflow = Overflow.hidden
                    position = Position.relative
                    display = Display.flex
                }

                styledDiv {
                    // App Frame
                    css {
                        overflow = Overflow.hidden
                        position = Position.relative
                        display = Display.flex
                        width = 100.pct
                    }

                    appBar() {}
                    drawer() {}

                    // Main content area
                    styledDiv {
                        css {
                            height = 100.pct
                            flexGrow = 1.0; minWidth = 0.px
                            backgroundColor = Color(theme.palette.background.default)
                        }

                        spacer()
                        styledDiv {
                            css {
                                media(theme.breakpoints.down(Breakpoint.sm)) {
                                    height = 100.vh - 57.px
                                }
                                media(theme.breakpoints.up(Breakpoint.sm)) {
                                    height = 100.vh - 65.px
                                }

                                overflowY = Overflow.auto
                                padding(2.spacingUnits)
                                backgroundColor = Color(theme.palette.background.default)
                            }
                            mainContainer() {}
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.mainFrame(initialView: String, onThemeTypeChange: (themeType: String) -> Unit) = child(MainFrame::class) {
    attrs.onThemeTypeChange = onThemeTypeChange
    attrs.initialView = initialView
}
