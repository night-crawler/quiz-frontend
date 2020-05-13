package fm.force.ui

import fm.force.ui.client.QuizClient
import fm.force.ui.constant.API_HOST
import fm.force.ui.reducer.combinedReducers
import fm.force.ui.reducer.state.CustomLocationState
import fm.force.ui.reducer.state.QuizState
import fm.force.ui.util.ThunkError
import fm.force.ui.util.composeWithDevTools
import fm.force.ui.util.createThunkMiddleware
import fm.force.ui.util.customEnhancer
import history.History
import history.createBrowserHistory
import react.router.connected.routerMiddleware
import redux.*

class ReduxStore(
    val store: Store<QuizState, RAction, WrapperAction>,
    val history: History<CustomLocationState>,
    val client: QuizClient
) {
    companion object {
        val DEFAULT by lazy { of(QuizState(), createBrowserHistory(), client = QuizClient(API_HOST)) }
        fun of(
            state: QuizState,
            history: History<CustomLocationState>,
            client: QuizClient
        ): ReduxStore {
            val store = createStore<QuizState, RAction, WrapperAction>(
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
