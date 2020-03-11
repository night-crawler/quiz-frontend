package fm.force.ui.client

import org.w3c.fetch.Response

open class FetchError(
    message: String,
    cause: Throwable? = null,
    val request: Request,
    val response: Response?,
    val status: HttpStatusCode,
    val responseText: String
) : RuntimeException(message, cause)

/**
 * When server is not responding, preflight request is failed, or network connection is lost
 */
class FetchClientNetworkError(
    message: String,
    cause: Throwable? = null,
    status: HttpStatusCode,
    request: Request,
    response: Response?
) : FetchError(
    message = message,
    cause = cause,
    status = status,
    request = request,
    response = response,
    responseText = ""
)

class ReadException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)
