package fm.force.ui.client

enum class HttpMethod {
    GET, POST, PATCH, PUT, DELETE, TRACE, OPTIONS
}

fun concatPaths(vararg paths: String) =
    paths.joinToString("/") { it.trim('/') }.trimEnd('/')
