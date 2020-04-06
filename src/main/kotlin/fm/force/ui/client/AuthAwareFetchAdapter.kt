package fm.force.ui.client

import fm.force.ui.client.dto.JwtAccessTokenDTO
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

class AuthAwareAdapterConfiguration(
    val refreshUri: String,
    val ignoreUriPatterns: Collection<Regex>
) : AdapterConfiguration<AuthAwareAdapterConfiguration>

class AuthAwareFetchAdapter(private val adapter: FetchAdapter) : FetchAdapter by adapter {
    private lateinit var accessToken: String
    lateinit var refreshUri: String
    lateinit var ignoreUriPatterns: Collection<Regex>

    override fun <T> configure(config: AdapterConfiguration<T>) {
        if (config !is AuthAwareAdapterConfiguration) {
            throw IllegalArgumentException("Parameter `config` must be an instance of `AuthAwareAdapterConfiguration`")
        }

        refreshUri = config.refreshUri
        ignoreUriPatterns = config.ignoreUriPatterns
    }

    override suspend fun <ResponseType : Any> fetch(
        method: HttpMethod,
        uri: String,
        body: Any?,
        headers: Map<String, String>,
        buildResponse: suspend Json.(Request, Response) -> ResponseType
    ): ResponseType {
        console.log("Fetching $uri")
        if (shouldIgnore(uri)) {
            return adapter.fetch(method, uri, body, headers, buildResponse)
        }

        var authHeaders = prepareAuthHeaders()
        return try {
            adapter.fetch(method, uri, body, headers + authHeaders, buildResponse)
        } catch (exc: UnauthorizedError) {
            authHeaders = prepareAuthHeaders(forceRefresh = true)
            adapter.fetch(method, uri, body, headers + authHeaders, buildResponse)
        }
    }

    private fun shouldIgnore(uri: String): Boolean {
        ignoreUriPatterns.forEach {
            if (it.matches(uri)) {
                return true
            }
        }
        return false
    }

    private suspend fun prepareAuthHeaders(forceRefresh: Boolean = false): Map<String, String> {
        if (forceRefresh || !::accessToken.isInitialized) {
            accessToken = refresh().accessToken
            console.log("No access token; refreshing (force: $forceRefresh)")
        }

        if (!JsJwt.decode(accessToken).isValid()) {
            accessToken = refresh().accessToken
            console.log("Access token is not valid; refreshing (force: $forceRefresh)")
        }

        return mapOf("Authorization" to "Bearer $accessToken")
    }

    private suspend fun refresh() = try {
        adapter.fetch(
            method = HttpMethod.POST,
            uri = refreshUri,
            body = null,
            headers = mapOf(
                "Content-Type" to "application/json",
                "Accept" to "application/json"
            ),
            buildResponse = { _, response ->
                val serializer = JwtAccessTokenDTO.serializer()
                parse(serializer, response.text().await())
            }
        )
    } catch (exc: FetchError) {
        if (exc.status == HttpStatusCode.Unauthorized) {
            throw UnauthorizedError(exc)
        }
        throw exc
    }
}
