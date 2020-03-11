package fm.force.ui.action

import fm.force.ui.client.QuizClient
import fm.force.ui.client.dto.LoginDTO
import fm.force.ui.client.dto.LoginResponseDTO
import fm.force.ui.reducer.State
import redux.RAction
import redux.WrapperAction

class LoginStart : RAction

class LoginSuccess(val loginResponseDTO: LoginResponseDTO) : RAction

class LoginError(val responseText: String) : RAction

class LoginThunk(private val loginDTO: LoginDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(LoginStart()) },
            error = { exc, _ -> dispatch(LoginError(exc.responseText)) }
        ) {
            dispatch(LoginSuccess(client.login(loginDTO)))
        }
    }
}
