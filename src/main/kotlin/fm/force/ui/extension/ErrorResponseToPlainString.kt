package fm.force.ui.extension

import fm.force.quiz.common.dto.ErrorMessage
import fm.force.quiz.common.dto.ErrorResponse
import fm.force.quiz.common.dto.FieldError

fun ErrorResponse.toPlainString(): String {
    val fieldErrors = this.errors.filterIsInstance<FieldError>()
    val nonFieldErrors = errors.filterIsInstance<ErrorMessage>()
    val parts = fieldErrors.map { "${it.fieldName}: ${it.message}" } + nonFieldErrors.map { it.message }
    return parts.joinToString("\n")
}
