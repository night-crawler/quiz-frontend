package fm.force.ui.util

fun parseQueryString(rawString: String): dynamic {
    val parts = rawString
        .split("?").last()
        .split("&")
        .filter { it.isNotBlank() }
        .map { decodeURIComponent(it) }

    val destination = js("{}")
    if (parts.isEmpty()) {
        return destination
    }

    parts.forEach { part ->
        val (path, value) = part.split("=", limit = 2)
        deepSet(path, value, destination)
    }
    return destination
}
