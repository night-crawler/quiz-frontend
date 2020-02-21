package fm.force.ui.container

import fm.force.ui.DrawerOpenToggle
import fm.force.ui.SetThemeType
import fm.force.ui.component.AppBar
import fm.force.ui.component.AppBarProps
import fm.force.ui.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface AppBarConnectedProps : RProps

private interface AppBarStateProps : RProps {
    var appTitle: String
    var activeViewDisplayName: String
    var themeType: String
}

private interface AppBarDispatchProps : RProps {
    var onThemeTypeChange: (themeType: String) -> Unit
    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

private val mapStateToProps: AppBarStateProps.(State, AppBarConnectedProps) -> Unit = { state, _ ->
    appTitle = state.appPreferences.appTitle
    activeViewDisplayName = state.appPreferences.activeViewDisplayName
    themeType = state.appPreferences.themeType
}

private val mapDispatchToProps: AppBarDispatchProps.((RAction) -> WrapperAction, AppBarConnectedProps) -> Unit =
    { dispatch, _ ->
        onThemeTypeChange = { themeColor -> dispatch(SetThemeType(themeColor)) }
        onResponsiveDrawerOpenToggle = { isOpen -> dispatch(DrawerOpenToggle(isOpen)) }
    }

val appBar: RClass<AppBarConnectedProps> =
    rConnect<State, RAction, WrapperAction, AppBarConnectedProps, AppBarStateProps, AppBarDispatchProps, AppBarProps>(
        mapStateToProps,
        mapDispatchToProps
    )(AppBar::class.rClass)