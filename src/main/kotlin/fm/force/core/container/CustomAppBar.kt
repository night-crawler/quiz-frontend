package fm.force.core.container

import fm.force.core.action.DrawerOpenToggle
import fm.force.core.action.SetThemeType
import fm.force.core.component.CustomAppBar
import fm.force.core.component.CustomAppBarProps
import fm.force.core.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface CustomAppBarConnectedProps : RProps

private interface CustomAppBarStateProps : RProps {
    var appTitle: String
    var activeViewDisplayName: String
    var themeType: String
}

private interface CustomAppBarDispatchProps : RProps {
    var onThemeTypeChange: (themeType: String) -> Unit
    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

private val mapStateToProps: CustomAppBarStateProps.(State, CustomAppBarConnectedProps) -> Unit = { state, _ ->
    appTitle = state.appPreferences.appTitle
    activeViewDisplayName = state.appPreferences.activeViewDisplayName
    themeType = state.appPreferences.themeType
}

private val mapDispatchToProps: CustomAppBarDispatchProps.((RAction) -> WrapperAction, CustomAppBarConnectedProps) -> Unit =
    { dispatch, _ ->
        onThemeTypeChange = { themeColor -> dispatch(SetThemeType(themeColor)) }
        onResponsiveDrawerOpenToggle = { isOpen -> dispatch(DrawerOpenToggle(isOpen)) }
    }

val customAppBar: RClass<CustomAppBarConnectedProps> =
    rConnect<State, RAction, WrapperAction, CustomAppBarConnectedProps, CustomAppBarStateProps, CustomAppBarDispatchProps, CustomAppBarProps>(
        mapStateToProps,
        mapDispatchToProps
    )(CustomAppBar::class.rClass)
