package fm.force.core.reducers

import redux.RAction

fun appPreferencesReducer(state: AppPreferences?, action: RAction): AppPreferences = state ?: AppPreferences()
