package fm.force.ui.client

import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.util.jsApply
import fm.force.ui.util.toJson
import kotlin.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.w3c.fetch.INCLUDE
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.Response

interface AdapterConfiguration<T>

interface FetchAdapter {
    fun <T> configure(config: AdapterConfiguration<T>): Unit = throw NotImplementedError()
    suspend fun <ResponseType : Any> fetch(
        method: HttpMethod,
        uri: String,
        body: Any?,
        headers: Map<String, String>,
        buildResponse: suspend Json.(Request, Response) -> ResponseType
    ): ResponseType
}

@Suppress("OVERRIDE_BY_INLINE")
open class WindowFetchAdapter : FetchAdapter {
    @ImplicitReflectionSerializer
    final override suspend inline fun <ResponseType : Any> fetch(
        method: HttpMethod,
        uri: String,
        body: Any?,
        headers: Map<String, String>,
        buildResponse: suspend Json.(Request, Response) -> ResponseType
    ): ResponseType {
        @Suppress("UNCHECKED_CAST")
        val data = body?.let {
            try {
                val serializer = it::class.serializer() as KSerializer<Any>
                QuizJson.jsonX.stringify(serializer, body)
            } catch (exc: SerializationException) {
                // probably the body is just a simple JavaScript object
                JSON.stringify(body)
            } catch (exc: Throwable) {
                console.error(exc, body)
                throw exc
            }
        }

        val request = Request(method, uri, data, headers)
        val response = fetchInternal(request)

        return QuizJson.jsonX.buildResponse(request, response)
    }

    suspend fun fetchInternal(request: Request): Response {
        val responsePromise = window.fetch(
            request.uri,
            jsApply {
                this.method = request.method.name
                this.body = request.body
                this.headers = request.headers.toJson()
                this.credentials = RequestCredentials.INCLUDE
            }
        )
        val response = try {
            responsePromise.await()
        } catch (exc: Throwable) {
            throw FetchClientNetworkError(
                "Failed to retrieve a resource `${request.uri}`",
                request = request,
                response = null,
                cause = exc,
                status = HttpStatusCode.fromCode(-1)
            )
        }

        if (!response.ok) {
            // we assume that server will never respond with blob if an exception has occurred,
            // so it must be safe to get a text of the response here
            throw FetchError(
                response.statusText,
                cause = null,
                request = request,
                response = response,
                status = HttpStatusCode.fromCode(response.status),
                responseText = response.text().await()
            )
        }

        return response
    }
}
