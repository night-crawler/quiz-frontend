package fm.force.ui.component.main

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.list.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import fm.force.ui.util.IconName
import kotlinext.js.js
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
import styled.StyledElementBuilder
import styled.css

interface DrawerProps : RProps {
    var responsiveDrawerOpen: Boolean

    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

class Drawer(props: DrawerProps) : RComponent<DrawerProps, RState>(props) {
    private val drawerWidth = 180.px
    private val paperProps = js {
        style = js {
            position = "relative"
            width = drawerWidth.value
            display = "block"
            height = "100%"
            minHeight = "100vh"
        }
    }.unsafeCast<MPaperProps>()

    override fun RBuilder.render() {
        mCssBaseline()

        mHidden(mdUp = true) {
            mDrawer(
                props.responsiveDrawerOpen,
                MDrawerAnchor.left,
                MDrawerVariant.temporary,
                paperProps = paperProps,
                onClose = { props.onResponsiveDrawerOpenToggle(!props.responsiveDrawerOpen) }
            ) {
                spacer()
                renderRouteLinks()
            }
        }
        mHidden(smDown = true, implementation = MHiddenImplementation.css) {
            mDrawer(true, MDrawerAnchor.left, MDrawerVariant.permanent, paperProps = paperProps) {
                spacer()
                renderRouteLinks()
            }
        }
    }

    private fun RBuilder.renderRouteLinks() {
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

                renderQuestionRouteLinks()
                renderQuizRouteLinks()
                renderDifficultyScaleRouteLinks()
                renderQuizSessionRouteLinks()
            }
        }
    }

    private fun StyledElementBuilder<MListProps>.renderDifficultyScaleRouteLinks() {
        renderLink("/difficulty-scales", "Difficulty Scales", IconName.VIEW_LIST)
        renderLink("/difficulty-scales/create", "Create Difficulty Scale", IconName.ACCESSIBILITY_NEW)
    }

    private fun StyledElementBuilder<MListProps>.renderQuizRouteLinks() {
        renderLink("/quizzes", "Quizzes", IconName.LIST_ALT)
        renderLink("/quizzes/create", "Create Quiz", IconName.NEW_RELEASES)
    }

    private fun StyledElementBuilder<MListProps>.renderQuizSessionRouteLinks() {
        renderLink("/sessions", "Sessions", IconName.LINE_STYLE)
    }

    private fun StyledElementBuilder<MListProps>.renderQuestionRouteLinks() {
        renderLink("/questions", "Questions", IconName.FORMAT_LIST_NUMBERED)
        renderLink("/questions/create", "Create Question", IconName.PLAYLIST_ADD)
    }

    private fun StyledElementBuilder<MListProps>.renderLink(path: String, name: String, iconName: IconName) {
        routeLink(path) { pathInfo ->
            mListItem(
                button = true,
                selected = pathInfo.isActive,
                onClick = {
                    props.onResponsiveDrawerOpenToggle(false)
                    pathInfo.onClick(it)
                }
            ) {
                mListItemIcon(iconName.iconMame)
                mListItemText { +name }
            }
        }
    }
}
