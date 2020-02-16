package fm.force.core.components

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemText
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import kotlin.properties.Delegates.observable
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import react.*
import styled.StyleSheet
import styled.css
import styled.styledDiv

interface MainFrameProps : RProps {
    var onThemeTypeChange: (themeType: String) -> Unit
    var initialView: String
}

class MainFrame(props: MainFrameProps) : RComponent<MainFrameProps, RState>(props) {
    private var themeColor = "light"
    private var responsiveDrawerOpen = false

    private val viewChangeObservers = emptyList<(String) -> Unit>()

    private var currentView: String by observable(props.initialView) { _, _, newValue ->
        viewChangeObservers.forEach { it(newValue) }
    }

    private val nameToTestMap = hashMapOf(
        "An Intro" to RBuilder::intro
    )

    override fun RBuilder.render() {
        mCssBaseline()

        val drawerWidth = 180.px

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

                    mAppBar(position = MAppBarPosition.absolute) {
                        css {
                            zIndex = theme.zIndex.drawer + 1
                        }
                        mToolbar {
                            mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                                mIconButton(
                                    "menu",
                                    color = MColor.inherit,
                                    onClick = { setState { responsiveDrawerOpen = true } }
                                )
                            }
                            mToolbarTitle("App title - $currentView")
                            mIconButton(
                                "lightbulb_outline",
                                onClick = {
                                    themeColor = if (themeColor == "light") "dark" else "light"
                                    props.onThemeTypeChange(themeColor)
                                }
                            )
                        }
                    }

                    val p: MPaperProps = jsObject { }
                    p.asDynamic().style = js {
                        position = "relative"; width = drawerWidth.value; display = "block"; height =
                            "100%"; minHeight = "100vh"
                    }
                    mHidden(mdUp = true) {
                        mDrawer(
                            responsiveDrawerOpen,
                            MDrawerAnchor.left,
                            MDrawerVariant.temporary,
                            paperProps = p,
                            onClose = { setState { responsiveDrawerOpen = !responsiveDrawerOpen } }
                        ) {
                            spacer()
                            demoItems()
                        }
                    }
                    mHidden(smDown = true, implementation = MHiddenImplementation.css) {
                        mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = p) {
                            spacer()
                            demoItems()
                        }
                    }

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
                            nameToTestMap[currentView]?.invoke(this)
                        }
                    }
                }
            }
        }
    }

    private fun RBuilder.demoItems() {
        fun RBuilder.addListItem(caption: String) {
//            mListItem(caption, onClick = {setState {currentView = caption}})
            // We want to get rid of the extra right padding, so must use the longer version as below
            mListItem(true, onClick = { setState { currentView = caption; responsiveDrawerOpen = false } }) {
                mListItemText(caption) {
                    css {
                        paddingRight = 0.px
                        if (caption == currentView) {
                            descendants {
                                color = Colors.Blue.shade500
                            }
                        }
                    }
                }
            }
        }

        themeContext.Consumer { theme ->
            mList {
                css {
                    media(theme.breakpoints.down(Breakpoint.sm)) {
                        height = 100.vh - 57.px
                    }
                    media(theme.breakpoints.up(Breakpoint.sm)) {
                        height = 100.vh - 65.px
                    }
                    overflowY = Overflow.auto
                    overflowX = Overflow.hidden
                    wordBreak = WordBreak.keepAll
                }
                nameToTestMap.keys.sorted().forEach { addListItem(it) }
            }
        }
    }
}

fun RBuilder.spacer() {
    themeContext.Consumer { theme ->
        val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
            val toolbar by css {
                toolbarJsCssToPartialCss(theme.mixins.toolbar)
            }
        }

        // This puts in a spacer to get below the AppBar.
        styledDiv {
            css(themeStyles.toolbar)
        }
        mDivider { }
    }
}

fun RBuilder.mainFrame(initialView: String, onThemeTypeChange: (themeType: String) -> Unit) = child(MainFrame::class) {
    attrs.onThemeTypeChange = onThemeTypeChange
    attrs.initialView = initialView
}
