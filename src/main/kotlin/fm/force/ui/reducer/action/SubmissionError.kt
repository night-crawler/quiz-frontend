package fm.force.ui.reducer.action

import fm.force.ui.util.ThunkCheckedException
import redux.form.SubmissionError

/**
 * This class must have SubmissionError name, otherwise it will not be recognised by redux-form
 * @param errors is a mapping of `{fieldName: [fm.force.quiz.common.dto.FieldError]]}`
 */
class SubmissionError(errors: dynamic) :
    SubmissionError(errors),
    ThunkCheckedException {
    companion object
}
