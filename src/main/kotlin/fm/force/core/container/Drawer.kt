package fm.force.core.container

import fm.force.core.action.DrawerOpenToggle
import fm.force.core.component.Drawer
import fm.force.core.component.DrawerProps
import fm.force.core.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface DrawerConnectedProps : RProps

private interface DrawerStateProps : RProps {
    var responsiveDrawerOpen: Boolean
}

private interface DrawerDispatchProps : RProps {
    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

private val mapStateToProps: DrawerStateProps.(State, DrawerConnectedProps) -> Unit = { state, _ ->
    responsiveDrawerOpen = state.appPreferences.responsiveDrawerOpen
}

private val mapDispatchToProps: DrawerDispatchProps.((RAction) -> WrapperAction, DrawerConnectedProps) -> Unit =
    { dispatch, _ ->
        onResponsiveDrawerOpenToggle = { isOpen -> dispatch(DrawerOpenToggle(isOpen)) }
    }

val drawer: RClass<DrawerConnectedProps> =
    rConnect<State, RAction, WrapperAction, DrawerConnectedProps, DrawerStateProps, DrawerDispatchProps, DrawerProps>(
        mapStateToProps,
        mapDispatchToProps
    )(Drawer::class.rClass)