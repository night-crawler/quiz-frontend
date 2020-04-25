package fm.force.ui.component.difficultyscale.dto

import fm.force.quiz.common.dto.ErrorMessage
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.reducer.action.of
import fm.force.ui.util.isFalsy

fun DifficultyScaleEditDTO.validate() {
    if (name.isFalsy) {
        throw SubmissionError.of(
            "name",
            ErrorMessage("Name must not be empty")
        )
    }

    if (ranges.isFalsy) {
        throw SubmissionError.of(
            "ranges._error",
            ErrorMessage("Ranges must not be empty")
        )
    }
}
