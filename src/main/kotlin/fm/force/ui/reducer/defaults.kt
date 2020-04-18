package fm.force.ui.reducer

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

fun AppPreferences.Companion.of() = AppPreferences(
    themeType = "light",
    appTitle = "Quiz",
    activeViewDisplayName = "",
    responsiveDrawerOpen = false
)
