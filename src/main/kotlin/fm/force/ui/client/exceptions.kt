package fm.force.ui.client

import org.w3c.fetch.Response

abstract class JsFetchError(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class FetchNetworkError(
    message: String,
    cause: Throwable? = null,
    val request: Request,
    val response: Response?
) : JsFetchError(message, cause)

class JsonError(
    message: String,
    val request: Request,
    val response: Response,
    val responseText: String
) : JsFetchError(message)

class ReadException(
    message: String,
    cause: Throwable? = null
) : JsFetchError(message, cause)
