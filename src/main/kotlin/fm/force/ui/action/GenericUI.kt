package fm.force.ui.action

import redux.RAction

class SetThemeType(val themeType: String) : RAction
class DrawerOpenToggle(val isOpen: Boolean) : RAction

class Noop() : RAction
