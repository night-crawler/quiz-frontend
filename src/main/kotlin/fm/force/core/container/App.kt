package fm.force.core.container

import fm.force.core.action.SetThemeType
import fm.force.core.component.App
import fm.force.core.component.AppProps
import fm.force.core.reducer.State
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
}

private val mapStateToProps: AppStateProps.(State, ConnectedAppProps) -> Unit = { state, props ->
    themeType = state.appPreferences.themeType
}

private val mapDispatchToProps: AppDispatchProps.((RAction) -> WrapperAction, ConnectedAppProps) -> Unit =
    { dispatch, props ->
        onSetThemeType = { themeColor -> dispatch(SetThemeType(themeColor)) }
    }

val app: RClass<ConnectedAppProps> =
    rConnect<State, RAction, WrapperAction, ConnectedAppProps, AppStateProps, AppDispatchProps, AppProps>(
        mapStateToProps,
        mapDispatchToProps
    )(App::class.rClass)
