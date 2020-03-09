package fm.force.ui.client

import fm.force.ui.client.dto.ErrorMessage
import fm.force.ui.client.dto.FieldError
import fm.force.ui.client.dto.GenericError
import fm.force.ui.util.jsApply
import fm.force.ui.util.toJson
import kotlin.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerialModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.w3c.fetch.INCLUDE
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.Response

open class WindowFetchAdapter(
    jsonConfiguration: JsonConfiguration = defaultJsonConfiguration,
    serialModule: SerialModule = defaultSerialModule
) {
    val jsonX = Json(jsonConfiguration, serialModule)

    @ImplicitReflectionSerializer
    suspend inline fun <reified ResponseType : Any> fetch(
        method: HttpMethod,
        uri: String,
        body: Any? = null,
        headers: Map<String, String> = mapOf(),
        noinline buildException: suspend Json.(Request, Response) -> Throwable = { request, response ->
            if (response.isJson()) {
                JsonError(
                    "Failed to fetch a resource $uri",
                    request,
                    response,
                    response.text().await()
                )
            } else {
                FetchNetworkError(
                    "Failed to fetch a resource $uri",
                    null,
                    request,
                    response
                )
            }
        },
        noinline buildResponse: suspend Json.(Request, Response) -> ResponseType = { request, response ->
            val serializer = ResponseType::class.serializer()
            parse(serializer, response.text().await())
        }
    ): ResponseType {
        @Suppress("UNCHECKED_CAST")
        val data = body?.let {
            try {
                val serializer = it::class.serializer() as KSerializer<Any>
                jsonX.stringify(serializer, body)
            } catch (exc: SerializationException) {
                JSON.stringify(body)
            }
        }

        val request = Request(method, uri, data, headers)
        val response = fetchInternal(request)
        if (!response.ok) {
            throw jsonX.buildException(request, response)
        }

        return jsonX.buildResponse(request, response)
    }

    companion object {
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
            try {
                return responsePromise.await()
            } catch (exc: Throwable) {
                throw FetchNetworkError(
                    "Failed to retrieve a resource `${request.uri}`",
                    request = request,
                    response = null,
                    cause = exc
                )
            }
        }

        private val defaultSerialModule = SerializersModule {
            polymorphic(GenericError::class) {
                ErrorMessage::class with ErrorMessage.serializer()
                FieldError::class with FieldError.serializer()
            }
        }
        private val defaultJsonConfiguration =
            JsonConfiguration(
                encodeDefaults = true,
                ignoreUnknownKeys = true,
                isLenient = false,
                serializeSpecialFloatingPointValues = false,
                allowStructuredMapKeys = true,
                prettyPrint = false,
                unquotedPrint = false,
                indent = "    ",
                useArrayPolymorphism = false,
                classDiscriminator = "@type"
            )
    }
}
