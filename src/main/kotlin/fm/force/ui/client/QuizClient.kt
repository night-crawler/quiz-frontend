package fm.force.ui.client

import fm.force.ui.client.dto.JwtResponseTokensDTO
import fm.force.ui.client.dto.LoginRequestDTO
import fm.force.ui.util.QueryBuilder

open class QuizClient(
    scheme: String = "http",
    host: String = "localhost",
    port: Int? = null,
    private val fetchAdapter: WindowFetchAdapter = WindowFetchAdapter()
) {
    private val baseUri = "$scheme://$host" + (port?.let { ":$port" } ?: "")
    private val jsonHeaders = mapOf(
        "Content-Type" to "application/json",
        "Accept" to "application/json"
    )
    private var authHeaders = mutableMapOf<String, String>()


    fun prepareUri(vararg paths: String, params: Map<String, Any> = mapOf()) =
        QueryBuilder.of(params).appendTo(
            concatPaths(
                baseUri,
                *paths
            )
        )

    suspend fun login(jwtResponseTokensDTO: LoginRequestDTO) = fetchAdapter.fetch<JwtResponseTokensDTO>(
        HttpMethod.POST,
        prepareUri("auth/login"),
        jwtResponseTokensDTO,
        headers = jsonHeaders + authHeaders
    )
}
