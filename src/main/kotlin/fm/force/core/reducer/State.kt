package fm.force.core.reducer

import fm.force.core.util.combineReducers

data class AppPreferences(
    val themeType: String = "light"
)

data class State(
    val appPreferences: AppPreferences = AppPreferences()
)

fun combinedReducers() = combineReducers(
    mapOf(
        State::appPreferences to ::appPreferencesReducer
    )
)
