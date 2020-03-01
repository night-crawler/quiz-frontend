package fm.force.ui

import fm.force.ui.reducer.State
import fm.force.util.Thunk
import fm.force.util.jsApply
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.request.get
import kotlinx.coroutines.await
import redux.RAction
import redux.WrapperAction
import kotlin.browser.window
import kotlin.js.json

class SetThemeType(val themeType: String) : RAction

class DrawerOpenToggle(val isOpen: Boolean) : RAction

class SampleGo() : RAction
class SampleSuccess(val text: String) : RAction
class SampleFinish() : RAction

class SampleThunk : Thunk<State, RAction, WrapperAction, Any> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        extra: Any
    ) {
        val client = HttpClient(Js) {
        }
        dispatch(SampleGo())
        val response = client.get<String>("http://localhost:3001")
        dispatch(SampleSuccess(text = response))
    }
}
