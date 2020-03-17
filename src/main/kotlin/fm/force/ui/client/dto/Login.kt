package fm.force.ui.client.dto

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestDTO(
    val email: String,
    val password: String
)

@Serializable
data class JwtResponseTokensDTO(
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class JwtAccessTokenDTO(val accessToken: String)
