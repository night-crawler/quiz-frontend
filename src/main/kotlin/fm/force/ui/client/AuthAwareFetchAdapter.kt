package fm.force.ui.client

import fm.force.ui.client.dto.JwtAccessTokenDTO
import kotlinx.coroutines.await
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import org.w3c.fetch.Response

data class AuthAwareAdapterConfiguration(
    val refreshUri: String,
    val ignoreUriPatterns: Collection<Regex>
) : AdapterConfiguration<AuthAwareAdapterConfiguration>

class AuthAwareFetchAdapter(private val adapter: FetchAdapter) : FetchAdapter by adapter {
    private val logger = KotlinLogging.logger("AuthAwareFetchAdapter")
    private val mutex = Mutex()

    private lateinit var accessToken: String
    lateinit var refreshUri: String
    lateinit var ignoreUriPatterns: Collection<Regex>

    override fun <T> configure(config: AdapterConfiguration<T>) {
        if (config !is AuthAwareAdapterConfiguration) {
            throw IllegalArgumentException("Parameter `config` must be an instance of `AuthAwareAdapterConfiguration`")
        }

        logger.debug { "Applied configuration: $config" }
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
        logger.debug { "Fetching $uri" }
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
        mutex.withLock {
            if (forceRefresh || !::accessToken.isInitialized) {
                accessToken = refresh().accessToken
                logger.debug { "No access token; refreshing (force: $forceRefresh)" }
            }

            if (!JsJwt.decode(accessToken).isValid()) {
                accessToken = refresh().accessToken
                logger.debug { "Access token is not valid; refreshing (force: $forceRefresh)" }
            }

            return mapOf("Authorization" to "Bearer $accessToken")
        }
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
