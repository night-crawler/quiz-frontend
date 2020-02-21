package fm.force.ui

import fm.force.ui.reducer.State
import fm.force.util.Thunk
import redux.RAction
import redux.WrapperAction

class SetThemeType(val themeType: String) : RAction

class DrawerOpenToggle(val isOpen: Boolean) : RAction

class SampleThunk : Thunk<State, RAction, WrapperAction> {
    override fun run(dispatch: (RAction) -> WrapperAction, getState: () -> State): WrapperAction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
