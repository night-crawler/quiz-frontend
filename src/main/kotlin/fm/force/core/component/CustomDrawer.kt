package fm.force.core.component

import com.ccfraser.muirwik.components.MDrawerAnchor
import com.ccfraser.muirwik.components.MDrawerVariant
import com.ccfraser.muirwik.components.MHiddenImplementation
import com.ccfraser.muirwik.components.MPaperProps
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mDrawer
import com.ccfraser.muirwik.components.mHidden
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import com.ccfraser.muirwik.components.themeContext
import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.Overflow
import kotlinx.css.WordBreak
import kotlinx.css.height
import kotlinx.css.overflowX
import kotlinx.css.overflowY
import kotlinx.css.px
import kotlinx.css.vh
import kotlinx.css.wordBreak
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css

interface CustomDrawerProps : RProps {
    var responsiveDrawerOpen: Boolean

    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

class CustomDrawer(props: CustomDrawerProps) : RComponent<CustomDrawerProps, RState>(props) {
    private fun RBuilder.demoItems() {
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

                routeLink("/sample/haha/222") {
                    mListItem("I AM HERE", selected = it.isActive, onClick = it.onClick)
                }

                routeLink("/sample") {
                    mListItem("HAHA", selected = it.isActive, onClick = it.onClick)
                }
            }
        }
    }

    override fun RBuilder.render() {
        mCssBaseline()

        val drawerWidth = 180.px

        val paperProps: MPaperProps = jsObject { }
        paperProps.asDynamic().style = js {
            position = "relative"
            width = drawerWidth.value
            display = "block"
            height = "100%"
            minHeight = "100vh"
        }
        console.log(paperProps)
        mHidden(mdUp = true) {
            mDrawer(
                props.responsiveDrawerOpen,
                MDrawerAnchor.left,
                MDrawerVariant.temporary,
                paperProps = paperProps,
                onClose = { props.onResponsiveDrawerOpenToggle(!props.responsiveDrawerOpen) }
            ) {
                spacer()
                demoItems()
            }
        }
        mHidden(smDown = true, implementation = MHiddenImplementation.css) {
            mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = paperProps) {
                spacer()
                demoItems()
            }
        }
    }
}
