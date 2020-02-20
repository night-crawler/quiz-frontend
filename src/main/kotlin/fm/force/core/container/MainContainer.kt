package fm.force.core.container

import fm.force.core.component.MainContainer
import fm.force.core.component.MainContainerProps
import fm.force.core.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface MainContainerConnectedProps : RProps

private interface MainContainerStateProps : RProps {
    var locationPathname: String
}

private interface MainContainerDispatchProps : RProps {
}

private val mapStateToProps: MainContainerStateProps.(State, MainContainerConnectedProps) -> Unit = { state, _ ->
    locationPathname = state.router.location.pathname
}

private val mapDispatchToProps: MainContainerDispatchProps.((RAction) -> WrapperAction, MainContainerConnectedProps) -> Unit =
    { _, _ ->
    }

val mainContainer: RClass<MainContainerConnectedProps> =
    rConnect<State, RAction, WrapperAction, MainContainerConnectedProps, MainContainerStateProps, MainContainerDispatchProps, MainContainerProps>(
        mapStateToProps,
        mapDispatchToProps
    )(MainContainer::class.rClass)
