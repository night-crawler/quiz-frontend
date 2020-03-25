package fm.force.ui.reducer

import fm.force.ui.action.Snack
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.util.createLocation
import fm.force.ui.util.customCombineReducers
import history.History
import kotlinext.js.jsObject
import react.router.connected.RouterState
import react.router.connected.connectRouter
import redux.form.reducer
import kotlin.js.Date

data class CustomLocationState(
    var placeholder: Int = 1
)

data class AppPreferences(
    val themeType: String = "light",
    val appTitle: String = "Quiz",
    val activeViewDisplayName: String = "1",
    val responsiveDrawerOpen: Boolean = false,
    val currentUser: UserFullDTO = UserFullDTO(
        id = -1,
        firstName = "",
        lastName = "",
        email = "anonymous",
        isActive = false,
        roles = emptySet(),
        createdAt = Date(0),
        updatedAt = Date(0)
    )
)

data class State(
    val snacks: List<Snack> = listOf(),
    val appPreferences: AppPreferences = AppPreferences(),
    val router: RouterState<CustomLocationState> = RouterState(
        createLocation(state = CustomLocationState()), "POP"
    ),
    val form: dynamic = jsObject { }
)

fun combinedReducers(history: History<*>) = customCombineReducers(
    mapOf(
        State::snacks to ::snackReducer,
        State::appPreferences to ::appPreferencesReducer,
        State::router to connectRouter(history),
        State::form to reducer
    )
)
