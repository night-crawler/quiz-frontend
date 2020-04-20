package fm.force.ui.reducer.action

import redux.RAction

class SetThemeType(val themeType: String) : RAction
class DrawerOpenToggle(val isOpen: Boolean) : RAction
class ChangeAppViewName(val title: String) : RAction

class Noop : RAction
