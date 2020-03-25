package fm.force.ui.container

import fm.force.ui.action.Bootstrap
import fm.force.ui.action.CloseSnack
import fm.force.ui.action.SetThemeType
import fm.force.ui.action.Snack
import fm.force.ui.component.SnackList
import fm.force.ui.component.SnackListProps
import fm.force.ui.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface ConnectedSnackListProps : RProps

private interface SnackListStateProps : RProps {
    var snacks: List<Snack>
}

private interface SnackListDispatchProps : RProps {
    var onCloseSnack: (key: String) -> Unit
}

private val mapStateToProps: SnackListStateProps.(State, ConnectedSnackListProps) -> Unit = { state, props ->
    snacks = state.snacks
}

private val mapDispatchToProps: SnackListDispatchProps.((RAction) -> WrapperAction, ConnectedSnackListProps) -> Unit =
    { dispatch, props ->
        onCloseSnack = { key -> dispatch(CloseSnack(key)) }
    }

val snackList: RClass<ConnectedSnackListProps> =
    rConnect<State, RAction, WrapperAction, ConnectedSnackListProps, SnackListStateProps, SnackListDispatchProps, SnackListProps>(
        mapStateToProps,
        mapDispatchToProps
    )(SnackList::class.rClass)
