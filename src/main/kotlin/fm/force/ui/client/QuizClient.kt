package fm.force.ui.client

import fm.force.ui.client.dto.LoginDTO
import fm.force.ui.client.dto.LoginResponseDTO
import fm.force.ui.util.QueryBuilder

open class QuizClient(
    scheme: String = "http",
    host: String = "localhost",
    port: Int? = null
) {
    private val baseUri = "$scheme://$host" + (port?.let { ":$port" } ?: "")
    private val jsonHeaders = mapOf(
        "Content-Type" to "application/json",
        "Accept" to "application/json"
    )
    private var authHeaders = mutableMapOf<String, String>()
    private val fetchAdapter = WindowFetchAdapter()
    val jsonX get() = fetchAdapter.jsonX

    fun prepareUri(vararg paths: String, params: Map<String, Any> = mapOf()) =
        QueryBuilder.of(params).appendTo(
            concatPaths(
                baseUri,
                *paths
            )
        )

    suspend fun login(loginDTO: LoginDTO) = fetchAdapter.fetch<LoginResponseDTO>(
        HttpMethod.POST,
        prepareUri("auth/login"),
        loginDTO,
        headers = jsonHeaders + authHeaders
    )
}
