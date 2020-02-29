package fm.force.ui

import fm.force.ui.reducer.State
import fm.force.util.Thunk
import fm.force.util.ThunkAcknowledged
import fm.force.util.jsApply
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import redux.RAction
import redux.WrapperAction
import kotlin.browser.window
import kotlin.js.json

class SetThemeType(val themeType: String) : RAction

class DrawerOpenToggle(val isOpen: Boolean) : RAction

class SampleGo() : RAction
class SampleSuccess(val text: String) : RAction
class SampleFinish() : RAction

class SampleThunk : Thunk<State, RAction, WrapperAction> {
    override fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State
    ) {
        GlobalScope.launch {
            dispatch(SampleGo())
            val response = window.fetch("http://localhost:3001", jsApply {
                method = "GET"
                headers = json("Accept" to "application/json")
            }).await()

            val text = response.text().await()
            dispatch(SampleSuccess(text = text))
        }
    }
}
