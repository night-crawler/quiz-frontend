package fm.force.ui.container

import fm.force.ui.component.App
import fm.force.ui.component.AppProps
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.Bootstrap
import fm.force.ui.reducer.action.SetThemeType
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

private val mapStateToProps: AppStateProps.(State, ConnectedAppProps) -> Unit = { state, props ->
    themeType = state.appPreferences.themeType
}

private val mapDispatchToProps: AppDispatchProps.((RAction) -> WrapperAction, ConnectedAppProps) -> Unit =
    { dispatch, props ->
        onSetThemeType = { themeColor -> dispatch(SetThemeType(themeColor)) }
        onBootstrap = { dispatch(Bootstrap()) }
    }

val app: RClass<ConnectedAppProps> =
    rConnect<State, RAction, WrapperAction, ConnectedAppProps, AppStateProps, AppDispatchProps, AppProps>(
        mapStateToProps,
        mapDispatchToProps
    )(App::class.rClass)
