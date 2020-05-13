package fm.force.ui.container

import fm.force.ui.component.main.Drawer
import fm.force.ui.component.main.DrawerProps
import fm.force.ui.reducer.action.DrawerOpenToggle
import fm.force.ui.reducer.state.QuizState
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
    var isLoggedIn: Boolean
}

private interface DrawerDispatchProps : RProps {
    var onResponsiveDrawerOpenToggle: (isOpen: Boolean) -> Unit
}

private val mapStateToProps: DrawerStateProps.(QuizState, DrawerConnectedProps) -> Unit = { state, _ ->
    responsiveDrawerOpen = state.appPreferences.responsiveDrawerOpen
    isLoggedIn = state.currentUser.id != -1L
}

private val mapDispatchToProps: DrawerDispatchProps.((RAction) -> WrapperAction, DrawerConnectedProps) -> Unit =
    { dispatch, _ ->
        onResponsiveDrawerOpenToggle = { isOpen ->
            dispatch(DrawerOpenToggle(isOpen))
        }
    }

val drawer: RClass<DrawerConnectedProps> =
    rConnect<QuizState, RAction, WrapperAction, DrawerConnectedProps, DrawerStateProps, DrawerDispatchProps, DrawerProps>(
        mapStateToProps,
        mapDispatchToProps
    )(Drawer::class.rClass)
