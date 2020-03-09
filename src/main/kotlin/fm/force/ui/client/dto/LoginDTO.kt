package fm.force.ui.client.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDTO(
    val email: String,
    val password: String
)
