package fm.force.ui.reducer.action

import fm.force.ui.util.ThunkCheckedException
import redux.form.SubmissionError

class SubmissionError(override var errors: dynamic) :
    SubmissionError(errors),
    ThunkCheckedException {

    init {
        this.asDynamic().__FLAG__ = "@@redux-form/submission-error-flag"
        this.asDynamic().constructor.__FLAG__ = "@@redux-form/submission-error-flag"
    }

    companion object
}
