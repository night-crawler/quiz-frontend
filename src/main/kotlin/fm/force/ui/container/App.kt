package fm.force.ui.container

import fm.force.ui.component.main.App
import fm.force.ui.component.main.AppProps
import fm.force.ui.reducer.action.BootstrapThunk
import fm.force.ui.reducer.action.SetThemeType
import fm.force.ui.reducer.state.QuizState
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface ConnectedAppProps : RProps

private interface AppStateProps : RProps {
    var themeType: String
}

private interface AppDispatchProps : RProps {
    var onSetThemeType: (themeType: String) -> Unit
    var onBootstrap: () -> Unit
}

private val mapStateToProps: AppStateProps.(QuizState, ConnectedAppProps) -> Unit = { state, props ->
    themeType = state.appPreferences.themeType
}

private val mapDispatchToProps: AppDispatchProps.((RAction) -> WrapperAction, ConnectedAppProps) -> Unit =
    { dispatch, props ->
        onSetThemeType = { themeColor -> dispatch(SetThemeType(themeColor)) }
        onBootstrap = { dispatch(BootstrapThunk()) }
    }

val app: RClass<ConnectedAppProps> =
    rConnect<QuizState, RAction, WrapperAction, ConnectedAppProps, AppStateProps, AppDispatchProps, AppProps>(
        mapStateToProps,
        mapDispatchToProps
    )(App::class.rClass)
