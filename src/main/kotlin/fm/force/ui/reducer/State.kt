package fm.force.ui.reducer

import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.reducer.action.Snack
import fm.force.ui.util.createLocation
import fm.force.ui.util.customCombineReducers
import history.History
import kotlinext.js.jsObject
import react.router.connected.RouterState
import react.router.connected.connectRouter
import redux.form.reducer as formReducer

data class CustomLocationState(
    var placeholder: Int = 1
)

data class State(
    val currentUser: UserFullDTO = UserFullDTO.of(),
    val snacks: List<Snack> = listOf(),
    val appPreferences: AppPreferences = AppPreferences.of(),
    val router: RouterState<CustomLocationState> = RouterState(
        createLocation(state = CustomLocationState()), "POP"
    ),
    val form: dynamic = jsObject { },

    val quizSessionState: QuizSessionState =  QuizSessionState.of()
)

fun combinedReducers(history: History<*>) = customCombineReducers(
    mapOf(
        State::currentUser to ::currentUserReducer,
        State::snacks to ::snackReducer,
        State::appPreferences to ::appPreferencesReducer,
        State::router to connectRouter(history),
        State::form to formReducer,
        State::quizSessionState to ::quizSessionStateReducer
    )
)
