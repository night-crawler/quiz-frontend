package fm.force.ui.reducer

import fm.force.util.createLocation
import fm.force.util.customCombineReducers
import history.History
import kotlinext.js.jsObject
import react.router.connected.RouterState
import react.router.connected.connectRouter
import redux.form.reducer

data class CustomLocationState(
    var placeholder: Int = 1
)

data class AppPreferences(
    val themeType: String = "light",
    val appTitle: String = "Quiz",
    val activeViewDisplayName: String = "1",
    val responsiveDrawerOpen: Boolean = false
)

data class State(
    val appPreferences: AppPreferences = AppPreferences(),
    val router: RouterState<CustomLocationState> = RouterState(
        createLocation(state = CustomLocationState()), "POP"
    ),
    val form: dynamic = jsObject { }
)

fun combinedReducers(history: History<*>) = customCombineReducers(
    mapOf(
        State::appPreferences to ::appPreferencesReducer,
        State::router to connectRouter(history),
        State::form to reducer
    )
)
