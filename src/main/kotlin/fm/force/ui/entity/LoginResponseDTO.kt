package fm.force.ui.entity

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val refreshToken: String,
    val accessToken: String
)
