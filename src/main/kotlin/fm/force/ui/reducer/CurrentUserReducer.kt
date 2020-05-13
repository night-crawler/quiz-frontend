package fm.force.ui.reducer

import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.reducer.action.BootstrapSuccess
import fm.force.ui.reducer.action.auth.LogoutSuccess
import fm.force.ui.reducer.state.of
import redux.RAction

fun currentUserReducer(state: UserFullDTO = UserFullDTO.of(), action: RAction): UserFullDTO = when (action) {
    is BootstrapSuccess -> action.userProfile
    is LogoutSuccess -> UserFullDTO.of()
    else -> state
}
