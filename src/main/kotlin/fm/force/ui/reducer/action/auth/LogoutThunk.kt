package fm.force.ui.reducer.action.auth

import fm.force.ui.client.QuizClient
import fm.force.ui.component.main.defaultSubmitErrorHandler
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ThunkForm
import react.router.connected.push
import redux.RAction
import redux.WrapperAction

class LogoutStart : RAction

class LogoutSuccess : RAction

class LogoutThunk : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(LogoutStart()) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            client.logout()
            dispatch(LogoutSuccess())
            dispatch(push("/login"))
        }
    }
}
