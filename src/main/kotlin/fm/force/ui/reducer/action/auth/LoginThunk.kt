package fm.force.ui.reducer.action.auth

import fm.force.ui.client.QuizClient
import fm.force.ui.client.dto.JwtResponseTokensDTO
import fm.force.ui.client.dto.LoginRequestDTO
import fm.force.ui.component.main.defaultSubmitErrorHandler
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.BootstrapThunk
import fm.force.ui.reducer.action.ThunkForm
import react.router.connected.push
import redux.RAction
import redux.WrapperAction

class LoginStart : RAction

class LoginSuccess(val jwtResponseTokensDto: JwtResponseTokensDTO) : RAction

class LoginError(val responseText: String) : RAction

class LoginThunk(private val loginRequestDTO: LoginRequestDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(LoginStart()) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            val jwtResponseTokensDTO = client.login(loginRequestDTO)
            dispatch(LoginSuccess(jwtResponseTokensDto = jwtResponseTokensDTO))
            dispatch(BootstrapThunk())
            dispatch(push("/"))
        }
    }
}
