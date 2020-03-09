package fm.force.ui.action

import fm.force.ui.util.ThunkCheckedException
import redux.form.SubmissionError

/**
 * This class must have SubmissionError name, otherwise it will not be recognised by redux-form
 * @param errors is a mapping of `{fieldName: [FieldError]]}`
 */
class SubmissionError(errors: dynamic) :
    SubmissionError(errors),
    ThunkCheckedException
