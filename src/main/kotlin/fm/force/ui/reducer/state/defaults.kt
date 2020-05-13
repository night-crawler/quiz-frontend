package fm.force.ui.reducer.state

import fm.force.ui.client.dto.UserFullDTO
import kotlin.js.Date

fun UserFullDTO.Companion.of() = UserFullDTO(
    id = -1,
    firstName = "",
    lastName = "",
    email = "anonymous",
    isActive = false,
    roles = emptySet(),
    createdAt = Date(0),
    updatedAt = Date(0)
)
