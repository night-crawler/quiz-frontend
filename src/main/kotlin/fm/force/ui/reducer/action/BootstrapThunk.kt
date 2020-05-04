package fm.force.ui.reducer.action

import fm.force.ui.client.FetchClientNetworkError
import fm.force.ui.client.QuizClient
import fm.force.ui.client.UnauthorizedError
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.reducer.State
import fm.force.ui.util.IconName
import fm.force.ui.util.Thunk
import react.router.connected.push
import redux.RAction
import redux.WrapperAction

class BootstrapSuccess(val userProfile: UserFullDTO) : RAction

class BootstrapThunk : Thunk<State, RAction, WrapperAction, QuizClient> {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        client: QuizClient
    ): WrapperAction {
        return try {
            dispatch(BootstrapSuccess(client.currentProfile()))
        } catch (exc: UnauthorizedError) {
            dispatch(push("/login"))
        } catch (exc: FetchClientNetworkError) {
            dispatch(
                ShowSnack(
                    Snack(
                        title = "Request failed",
                        text = "No Internet connection or server is down",
                        type = SnackType.ERROR,
                        iconName = IconName.ERROR,
                        timeout = null
                    )
                )
            )
        }
    }
}
