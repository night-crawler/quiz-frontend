package fm.force.ui.reducer

import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.reducer.action.BootstrapSuccess
import redux.RAction

fun currentUserReducer(state: UserFullDTO = UserFullDTO.of(), action: RAction): UserFullDTO = when (action) {
    is BootstrapSuccess -> action.userProfile
    else -> state
}
