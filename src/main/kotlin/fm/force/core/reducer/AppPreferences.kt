package fm.force.core.reducer

import fm.force.core.action.DrawerOpenToggle
import fm.force.core.action.SetThemeType
import redux.RAction

fun appPreferencesReducer(state: AppPreferences = AppPreferences(), action: RAction): AppPreferences = when (action) {
    is SetThemeType -> state.copy(themeType = action.themeType)
    is DrawerOpenToggle -> state.copy(responsiveDrawerOpen = action.isOpen)
    else -> state
}
