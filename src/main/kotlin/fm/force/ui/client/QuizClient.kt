package fm.force.ui.client

import fm.force.ui.client.dto.ErrorMessage
import fm.force.ui.client.dto.FieldError
import fm.force.ui.client.dto.GenericError
import fm.force.ui.component.LoginDTO
import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.jsApply
import fm.force.ui.util.toJson
import kotlinx.coroutines.await
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.SerialModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.w3c.fetch.INCLUDE
import org.w3c.fetch.RequestCredentials
import org.w3c.fetch.Response
import org.w3c.files.Blob
import org.w3c.files.FileReader
import kotlin.browser.window
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun FileReader.toText(blob: Blob): String {
    return suspendCoroutine { cont ->
        this.readAsText(blob)
        this.onload = { _ -> cont.resume(this.result) }
        this.onerror = { _ -> cont.resumeWithException(ReadException("Failed to read a blob")) }
    }
}

class Request(
    val method: HttpMethod,
    val uri: String,
    val body: Any? = null,
    val headers: Map<String, String> = mapOf()
)

fun Response.isJson(): Boolean {
    val parts = (headers.get("content-type") ?: "").split("/")
    if (parts.size < 2) {
        return false
    }
    return "json" in parts.last().toLowerCase()
}

@UnstableDefault
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
                JsonError("Failed to fetch a resource $uri", request, response, response.text().await())
            } else {
                FetchError("Failed to fetch a resource $uri", null, request, response)
            }
        },
        noinline buildResponse: suspend Json.(Request, Response) -> ResponseType = { request, response ->
            val serializer = ResponseType::class.serializer()
            parse(serializer, response.text().await())
        }
    ): ResponseType {
        @Suppress("UNCHECKED_CAST")
        val data = body?.let {
            val serializer = it::class.serializer() as KSerializer<Any>
            jsonX.stringify(serializer, body)
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
            val responsePromise = window.fetch(request.uri, jsApply {
                this.method = request.method.name
                this.body = request.body
                this.headers = request.headers.toJson()
                this.credentials = RequestCredentials.INCLUDE
            })
            try {
                return responsePromise.await()
            } catch (exc: Throwable) {
                throw FetchError(
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

@Serializable
data class LoginResponseDTO(
    val email: String
)

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

    fun prepareUri(vararg paths: String, params: Map<String, Any> = mapOf()) =
        QueryBuilder.of(params).appendTo(
            concatPaths(
                baseUri,
                *paths
            )
        )

    @ImplicitReflectionSerializer
    suspend fun login(loginDTO: LoginDTO) = fetchAdapter.fetch<LoginResponseDTO>(
        HttpMethod.POST,
        prepareUri("auth/login"),
        loginDTO,
        headers = jsonHeaders + authHeaders
    )
}
