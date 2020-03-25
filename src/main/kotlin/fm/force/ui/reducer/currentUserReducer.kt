package fm.force.ui.reducer

import fm.force.ui.action.BootstrapSuccess
import fm.force.ui.client.dto.UserFullDTO
import redux.RAction

fun currentUserReducer(state: UserFullDTO = UserFullDTO.of(), action: RAction): UserFullDTO = when (action) {
    is BootstrapSuccess -> action.userProfile
    else -> state
}
