package fm.force.ui.client

import fm.force.quiz.common.dto.ErrorMessage
import fm.force.quiz.common.dto.ErrorResponse
import fm.force.quiz.common.dto.FieldError
import fm.force.quiz.common.dto.GenericError
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import org.w3c.files.Blob
import org.w3c.files.FileReader

fun concatPaths(vararg paths: String) =
    paths.joinToString("/") { it.trim('/') }.trimEnd('/')

suspend fun FileReader.toText(blob: Blob): String {
    return suspendCoroutine { cont ->
        this.readAsText(blob)
        this.onload = { _ -> cont.resume(this.result) }
        this.onerror =
            { _ -> cont.resumeWithException(ReadException("Failed to read a blob")) }
    }
}

fun ErrorResponse.toReduxFormErrors(): Map<String, List<GenericError>> {
    val fieldErrors = errors.filterIsInstance<FieldError>().groupBy { it.fieldName }
    val nonFieldErrors = errors.filterIsInstance<ErrorMessage>().groupBy { "_error" }
    return fieldErrors + nonFieldErrors
}
