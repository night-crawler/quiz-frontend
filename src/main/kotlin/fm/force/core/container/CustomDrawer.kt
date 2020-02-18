package fm.force.core.container

import fm.force.core.action.DrawerOpenToggle
import fm.force.core.component.CustomDrawer
import fm.force.core.component.CustomDrawerProps
import fm.force.core.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface CustomDrawerConnectedProps : RProps

private interface CustomDrawerStateProps : RProps {
    var responsiveDrawerOpen: Boolean
}

private interface CustomDrawerDispatchProps : RProps {
    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

private val mapStateToProps: CustomDrawerStateProps.(State, CustomDrawerConnectedProps) -> Unit = { state, _ ->
    responsiveDrawerOpen = state.appPreferences.responsiveDrawerOpen
}

private val mapDispatchToProps: CustomDrawerDispatchProps.((RAction) -> WrapperAction, CustomDrawerConnectedProps) -> Unit =
    { dispatch, _ ->
        onResponsiveDrawerOpenToggle = { isOpen -> dispatch(DrawerOpenToggle(isOpen)) }
    }

val customDrawer: RClass<CustomDrawerConnectedProps> =
    rConnect<State, RAction, WrapperAction, CustomDrawerConnectedProps, CustomDrawerStateProps, CustomDrawerDispatchProps, CustomDrawerProps>(
        mapStateToProps,
        mapDispatchToProps
    )(CustomDrawer::class.rClass)
