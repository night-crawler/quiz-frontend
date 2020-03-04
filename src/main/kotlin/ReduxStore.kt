import fm.force.ui.reducer.CustomLocationState
import fm.force.ui.reducer.State
import fm.force.ui.reducer.combinedReducers
import fm.force.util.ThunkError
import fm.force.util.composeWithDevTools
import fm.force.util.createThunkMiddleware
import fm.force.util.customEnhancer
import history.History
import history.createBrowserHistory
import react.router.connected.routerMiddleware
import redux.RAction
import redux.Store
import redux.WrapperAction
import redux.applyMiddleware
import redux.createStore

class ReduxStore(
    val store: Store<State, RAction, WrapperAction>,
    val history: History<CustomLocationState>
) {
    companion object {
        fun default() = of(State(), createBrowserHistory())
        fun of(
            state: State,
            history: History<CustomLocationState>
        ): ReduxStore {
            val store = createStore<State, RAction, WrapperAction>(
                combinedReducers(history),
                state,
                composeWithDevTools(
                    applyMiddleware(
                        routerMiddleware(history),
                        createThunkMiddleware(1) { action, exc -> ThunkError(action, exc) }
                    ),
                    customEnhancer()
                )
            )

            return ReduxStore(store, history)
        }
    }
}
