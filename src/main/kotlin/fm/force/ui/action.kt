package fm.force.ui

import fm.force.ui.reducer.State
import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class SetThemeType(val themeType: String) : RAction

class DrawerOpenToggle(val isOpen: Boolean) : RAction

class LoginStart() : RAction
class LoginSuccess(val text: String) : RAction
class LoginError() : RAction


class SampleThunk : Thunk<State, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE") client: QuizClient
    ) {
        dispatch(LoginStart())

        val result = client.fetch(
            "post",
            "http://localhost:8181/auth/register",
            """{"lol": "q"}""",
            mapOf("Accept" to "application/json")
        )
        dispatch(LoginSuccess(result))
    }
}
