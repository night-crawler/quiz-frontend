package fm.force.ui.reducer

import fm.force.ui.reducer.action.CloseSnack
import fm.force.ui.reducer.action.ShowSnack
import fm.force.ui.reducer.action.Snack
import fm.force.ui.reducer.action.auth.LogoutSuccess
import redux.RAction

fun snackReducer(state: List<Snack> = listOf(), action: RAction): List<Snack> = when (action) {
    is ShowSnack -> state + listOf(action.snack)
    is CloseSnack -> state.filterNot { it.key == action.key }
    is LogoutSuccess -> listOf()
    else -> state
}
