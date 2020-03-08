package fm.force.ui.client

import org.w3c.fetch.Response

abstract class JsFetchError(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class FetchError(
    message: String,
    cause: Throwable? = null,
    request: Request,
    response: Response?
) : JsFetchError(message, cause)

class JsonError(
    message: String,
    request: Request,
    response: Response,
    responseText: String
) : JsFetchError(message)

class ReadException(
    message: String,
    cause: Throwable? = null
) : JsFetchError(message, cause)
