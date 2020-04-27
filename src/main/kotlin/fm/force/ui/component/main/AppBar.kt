package fm.force.ui.component.main

import com.ccfraser.muirwik.components.MAppBarPosition
import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.MHiddenImplementation
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.mAppBar
import com.ccfraser.muirwik.components.mCssBaseline
import com.ccfraser.muirwik.components.mHidden
import com.ccfraser.muirwik.components.mToolbar
import com.ccfraser.muirwik.components.mToolbarTitle
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import com.ccfraser.muirwik.components.themeContext
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.util.IconName
import kotlinx.css.zIndex
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css

interface AppBarProps : RProps {
    var currentUser: UserFullDTO
    var appTitle: String
    var activeViewDisplayName: String
    var themeType: String

    var onThemeTypeChange: (themeType: String) -> Unit
    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

enum class ThemeType(val value: String) {
    DARK("dark"),
    LIGHT("light");

    companion object {
        private val map = values().associateBy(ThemeType::value)
        fun of(value: String) = map[value] ?: error("No value `$value` in ThemeType")
    }
}

class AppBar(props: AppBarProps) : RComponent<AppBarProps, RState>(props) {
    private val nextThemeType
        get() =
            (ThemeType.values().toSet() - setOf(
                ThemeType.of(
                    props.themeType
                )
            )).first()

    override fun RBuilder.render() {
        mCssBaseline()

        themeContext.Consumer { theme ->
            mAppBar(position = MAppBarPosition.absolute) {
                css {
                    zIndex = theme.zIndex.drawer + 1
                }
                mToolbar {
                    mHidden(mdUp = true, implementation = MHiddenImplementation.css) {
                        mIconButton(
                            iconName = IconName.MENU.iconMame,
                            color = MColor.inherit,
                            onClick = { props.onResponsiveDrawerOpenToggle(true) }
                        )
                    }
                    mToolbarTitle("${props.appTitle} - ${props.activeViewDisplayName}")
                    mIconButton(
                        iconName = IconName.BRIGHTNESS_3.iconMame,
                        onClick = { props.onThemeTypeChange(nextThemeType.value) }
                    )

                    iconMenu(IconName.ACCOUNT_CIRCLE_OUTLINE.iconMame) {
                        routeLink("/profile") {
                            mMenuItemWithIcon(
                                iconName = IconName.PERSON_OUTLINE.iconMame,
                                primaryText = "Profile",
                                secondaryText = props.currentUser.email,
                                selected = it.isActive,
                                onClick = it.onClick
                            )
                        }
                        if (props.currentUser.isActive) {
                            routeLink("/logout") {
                                mMenuItemWithIcon(
                                    iconName = IconName.ROWING_OUTLINE.iconMame,
                                    primaryText = "Log out"
                                )
                            }
                        } else {
                            routeLink("/login") {
                                mMenuItemWithIcon(
                                    iconName = IconName.FINGERPRINT_OUTLINE.iconMame,
                                    primaryText = "Log in",
                                    selected = it.isActive,
                                    onClick = it.onClick
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
