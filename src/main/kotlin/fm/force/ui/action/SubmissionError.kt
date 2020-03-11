package fm.force.ui.action

import fm.force.ui.client.dto.ErrorMessage
import fm.force.ui.client.dto.ErrorResponse
import fm.force.ui.client.toReduxFormErrors
import fm.force.ui.util.ThunkCheckedException
import fm.force.ui.util.toJson
import redux.form.SubmissionError

/**
 * This class must have SubmissionError name, otherwise it will not be recognised by redux-form
 * @param errors is a mapping of `{fieldName: [FieldError]]}`
 */
class SubmissionError(errors: dynamic) :
    SubmissionError(errors),
    ThunkCheckedException {
    companion object {
    }
}

fun fm.force.ui.action.SubmissionError.Companion.of(message: String) = fm.force.ui.action.SubmissionError(
    mapOf(
        "_error" to listOf(ErrorMessage(message))
    ).toJson()
)

fun fm.force.ui.action.SubmissionError.Companion.of(errorResponse: ErrorResponse) =
    fm.force.ui.action.SubmissionError(errorResponse.toReduxFormErrors().toJson())
