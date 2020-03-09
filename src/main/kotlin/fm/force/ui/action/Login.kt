package fm.force.ui.action

import fm.force.ui.client.JsonError
import fm.force.ui.client.QuizClient
import fm.force.ui.client.dto.ErrorResponse
import fm.force.ui.client.dto.LoginDTO
import fm.force.ui.client.toReduxFormErrors
import fm.force.ui.reducer.State
import fm.force.ui.util.Thunk
import fm.force.ui.util.toJson
import redux.RAction
import redux.WrapperAction

class LoginStart : RAction

class LoginSuccess(val text: String) : RAction

class LoginError(val errorResponse: ErrorResponse) : RAction

class LoginThunk(private val loginDTO: LoginDTO) :
    Thunk<State, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        dispatch(LoginStart())
        return try {
            val response = client.login(loginDTO)
            dispatch(LoginSuccess(""))
        } catch (exc: JsonError) {
            val response = client.jsonX.parse(ErrorResponse.serializer(), exc.responseText)
            dispatch(LoginError(response))
            throw SubmissionError(response.toReduxFormErrors().toJson())
        }
    }
}
