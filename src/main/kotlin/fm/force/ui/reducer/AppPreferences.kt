package fm.force.ui.reducer

import fm.force.ui.action.DrawerOpenToggle
import fm.force.ui.action.SetThemeType
import redux.RAction

fun appPreferencesReducer(state: AppPreferences = AppPreferences(), action: RAction): AppPreferences = when (action) {
    is SetThemeType -> state.copy(themeType = action.themeType)
    is DrawerOpenToggle -> state.copy(responsiveDrawerOpen = action.isOpen)
    else -> state
}
