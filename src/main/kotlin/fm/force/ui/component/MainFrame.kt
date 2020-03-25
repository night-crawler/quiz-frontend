package fm.force.ui.component

import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.spacingUnits
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import com.ccfraser.muirwik.components.themeContext
import fm.force.ui.container.appBar
import fm.force.ui.container.drawer
import fm.force.ui.container.mainContainer
import fm.force.ui.container.snackList
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledDiv

interface MainFrameProps : RProps


class MainFrame(props: MainFrameProps) : RComponent<MainFrameProps, RState>(props) {

    override fun RBuilder.render() {
        mCssBaseline()
        themeContext.Consumer { theme ->
            snackList {}
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

fun RBuilder.mainFrame() = child(MainFrame::class) {}
