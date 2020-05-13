package fm.force.ui.container

import fm.force.ui.component.main.MainContainer
import fm.force.ui.component.main.MainContainerProps
import fm.force.ui.reducer.state.QuizState
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
    var dispatch: (RAction) -> WrapperAction
}

private val mapStateToProps: MainContainerStateProps.(QuizState, MainContainerConnectedProps) -> Unit = { state, _ ->
    locationPathname = state.router.location.pathname
}

private val mapDispatchToProps: MainContainerDispatchProps.((RAction) -> WrapperAction, MainContainerConnectedProps) -> Unit =
    { dispatch, _ ->
        this.dispatch = dispatch
    }

val mainContainer: RClass<MainContainerConnectedProps> =
    rConnect<QuizState, RAction, WrapperAction, MainContainerConnectedProps, MainContainerStateProps, MainContainerDispatchProps, MainContainerProps>(
        mapStateToProps,
        mapDispatchToProps
    )(MainContainer::class.rClass)
