package fm.force.ui.client.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequestDTO(
    val email: String,
    val password: String
)

@Serializable
data class RegisterResponseDTO(
    val id: Long,
    val username: String
)

@Serializable
data class JwtResponseTokensDTO(
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class JwtAccessTokenDTO(val accessToken: String)
