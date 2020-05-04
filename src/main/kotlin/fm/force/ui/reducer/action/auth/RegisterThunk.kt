package fm.force.ui.reducer.action.auth

import fm.force.ui.client.QuizClient
import fm.force.ui.client.dto.LoginRequestDTO
import fm.force.ui.client.dto.RegisterRequestDTO
import fm.force.ui.client.dto.RegisterResponseDTO
import fm.force.ui.component.main.defaultSubmitErrorHandler
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.*

import redux.RAction
import redux.WrapperAction
import react.router.connected.push


class RegisterStart : RAction

class RegisterSuccess(val registerResponseDTO: RegisterResponseDTO) : RAction

class RegisterError(val responseText: String) : RAction

class RegisterThunk(private val registerRequestDTO: RegisterRequestDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(RegisterStart()) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            val registerResponseDTO = client.register(registerRequestDTO)
            dispatch(RegisterSuccess(registerResponseDTO))

            client.activate(registerResponseDTO.id, "any-activation-is-ok-now")

            val loginRequestDTO = LoginRequestDTO(
                email = registerRequestDTO.email,
                password = registerRequestDTO.password
            )

            val jwtResponseTokensDTO = client.login(loginRequestDTO)
            dispatch(LoginSuccess(jwtResponseTokensDto = jwtResponseTokensDTO))

            Snack(
                title = "Registration has been successfully completed",
                type = SnackType.INFO,
                timeout = 2000
            )
                .let { ShowSnack(it) }
                .let { dispatch(it) }

            dispatch(BootstrapThunk())
            dispatch(push("/"))
        }
    }
}
