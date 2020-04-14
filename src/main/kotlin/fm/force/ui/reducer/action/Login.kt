package fm.force.ui.reducer.action

import fm.force.ui.client.QuizClient
import fm.force.ui.client.dto.JwtResponseTokensDTO
import fm.force.ui.client.dto.LoginRequestDTO
import fm.force.ui.reducer.State
import fm.force.ui.util.IconName
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
            error = { exc, _ ->
                dispatch(
                    ShowSnack(
                        Snack(
                            title = "Submission error",
                            iconName = IconName.ERROR,
                            timeout = null
                        )
                    )
                )
                dispatch(LoginError(exc.responseText))
            }
        ) {
            dispatch(LoginSuccess(jwtResponseTokensDto = client.login(loginRequestDTO)))
        }
    }
}
