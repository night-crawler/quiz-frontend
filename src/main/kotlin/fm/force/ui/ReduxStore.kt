package fm.force.ui

import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.CustomLocationState
import fm.force.ui.reducer.State
import fm.force.ui.reducer.combinedReducers
import fm.force.ui.util.ThunkError
import fm.force.ui.util.composeWithDevTools
import fm.force.ui.util.createThunkMiddleware
import fm.force.ui.util.customEnhancer
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
    val history: History<CustomLocationState>,
    val client: QuizClient
) {
    companion object {
        val DEFAULT by lazy { of(State(), createBrowserHistory(), client = QuizClient(port = 8181)) }
        fun of(
            state: State,
            history: History<CustomLocationState>,
            client: QuizClient
        ): ReduxStore {
            val store = createStore<State, RAction, WrapperAction>(
                combinedReducers(history),
                state,
                composeWithDevTools(
                    applyMiddleware(
                        routerMiddleware(history),
                        createThunkMiddleware(client) { action, exc -> ThunkError(action, exc) }
                    ),
                    customEnhancer()
                )
            )
            return ReduxStore(store, history, client)
        }
    }
}
