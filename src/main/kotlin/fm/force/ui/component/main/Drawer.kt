package fm.force.ui.component.main

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.list.*
import com.ccfraser.muirwik.components.styles.Breakpoint
import com.ccfraser.muirwik.components.styles.down
import com.ccfraser.muirwik.components.styles.up
import fm.force.ui.util.Icon
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
    var isLoggedIn: Boolean

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

                if (props.isLoggedIn) {
                    renderQuestionRouteLinks()
                    renderQuizRouteLinks()
                    renderDifficultyScaleRouteLinks()
                    renderQuizSessionRouteLinks()
                    renderTagLinks()
                    renderTopicLinks()
                    renderMiscRouteLinks()
                } else {
                    renderAuthRouteLinks()
                }
            }
        }
    }

    private fun RBuilder.renderMiscRouteLinks() {
        renderLink("/import", "Import", Icon.IMPORT_CONTACTS)
    }

    private fun RBuilder.renderAuthRouteLinks() {
        renderLink("/login", "Login", Icon.FINGERPRINT_OUTLINE)
        renderLink("/register", "Register", Icon.PLUS_ONE)
    }

    private fun StyledElementBuilder<MListProps>.renderDifficultyScaleRouteLinks() {
        renderLink("/difficulty-scales", "Difficulty Scales", Icon.VIEW_LIST)
        renderLink("/difficulty-scales/create", "Create Difficulty Scale", Icon.ACCESSIBILITY_NEW)
    }

    private fun StyledElementBuilder<MListProps>.renderQuizRouteLinks() {
        renderLink("/quizzes", "Quizzes", Icon.LIST_ALT)
        renderLink("/quizzes/create", "Create Quiz", Icon.NEW_RELEASES)
    }

    private fun StyledElementBuilder<MListProps>.renderQuizSessionRouteLinks() {
        renderLink("/sessions", "Sessions", Icon.LINE_STYLE)
    }

    private fun StyledElementBuilder<MListProps>.renderQuestionRouteLinks() {
        renderLink("/questions", "Questions", Icon.FORMAT_LIST_NUMBERED)
        renderLink("/questions/create", "Create Question", Icon.PLAYLIST_ADD)
    }

    private fun RBuilder.renderTagLinks() {
        renderLink("/tags", "Tags", Icon.BOOKMARK)
    }

    private fun RBuilder.renderTopicLinks() {
        renderLink("/topics", "Topics", Icon.THEATERS)
    }

    private fun RBuilder.renderLink(path: String, name: String, icon: Icon) {
        routeLink(path) { pathInfo ->
            mListItem(
                button = true,
                selected = pathInfo.isActive,
                onClick = {
                    props.onResponsiveDrawerOpenToggle(false)
                    pathInfo.onClick(it)
                }
            ) {
                mListItemIcon(icon.iconMame)
                mListItemText { +name }
            }
        }
    }
}
