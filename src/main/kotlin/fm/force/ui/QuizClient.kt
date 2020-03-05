package fm.force.ui

import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.jsApply
import fm.force.ui.util.toJson
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.w3c.files.FileReaderSync
import kotlin.browser.window


open class QuizClient(
    scheme: String = "http",
    host: String = "localhost",
    port: Int? = null
) {
    private val jsonX = Json(JsonConfiguration.Stable)
    private val baseUri = "$scheme://$host" + (port?.let { ":$port" } ?: "")
    private val defaultHeaders = mapOf(
        "Content-Type" to "application/json",
        "Accept" to "application/json"
    )
    private var authHeaders = mutableMapOf<String, String>()

    companion object {
        fun concatPaths(vararg paths: String) =
            paths.joinToString("/") { it.trim('/') }.trimEnd('/')
    }

    fun prepareUri(vararg paths: String, params: Map<String, Any> = mapOf()) =
        QueryBuilder.of(params).appendTo(concatPaths(baseUri, *paths))

    suspend inline fun fetch(
        method: String,
        uri: String,
        body: Any?,
        headers: Map<String, String>
    ): String {
        val response = window.fetch(uri, jsApply {
            this.method = method
            this.body = body
            this.headers = headers.toJson()
        }).await()

        val text = response.text().await()
        return text
    }
}
