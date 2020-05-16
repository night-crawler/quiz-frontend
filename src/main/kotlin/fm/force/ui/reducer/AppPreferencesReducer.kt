package fm.force.ui.reducer

import fm.force.ui.reducer.action.ChangeAppViewName
import fm.force.ui.reducer.action.DrawerOpenToggle
import fm.force.ui.reducer.action.SetThemeType
import fm.force.ui.reducer.action.auth.LogoutSuccess
import fm.force.ui.reducer.state.AppPreferences
import fm.force.ui.reducer.state.of
import redux.RAction

fun appPreferencesReducer(state: AppPreferences = AppPreferences.of(), action: RAction): AppPreferences =
    when (action) {
        is SetThemeType -> state.copy(themeType = action.themeType)
        is DrawerOpenToggle -> state.copy(responsiveDrawerOpen = action.isOpen)
        is ChangeAppViewName -> state.copy(activeViewDisplayName = action.title)
        is LogoutSuccess -> AppPreferences.of()
        else -> state
    }
