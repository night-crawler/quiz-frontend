package fm.force.core.reducers

import fm.force.core.util.combineReducers

data class AppPreferences(
    val themeColor: String = "light"
)

data class State(
    val appPreferences: AppPreferences = AppPreferences()
)

fun combinedReducers() = combineReducers(
    mapOf(
        State::appPreferences to ::appPreferencesReducer
    )
)
