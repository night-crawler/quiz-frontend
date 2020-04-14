package fm.force.ui.reducer

import fm.force.ui.reducer.action.DrawerOpenToggle
import fm.force.ui.reducer.action.SetThemeType
import redux.RAction

fun appPreferencesReducer(state: AppPreferences = AppPreferences.of(), action: RAction): AppPreferences =
    when (action) {
        is SetThemeType -> state.copy(themeType = action.themeType)
        is DrawerOpenToggle -> state.copy(responsiveDrawerOpen = action.isOpen)
        else -> state
    }
