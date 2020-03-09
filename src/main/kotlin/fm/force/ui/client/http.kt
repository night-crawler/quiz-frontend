package fm.force.ui.client

import org.w3c.fetch.Response

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

enum class HttpMethod {
    GET, POST, PATCH, PUT, DELETE, TRACE, OPTIONS
}
