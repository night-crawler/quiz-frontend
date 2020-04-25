package fm.force.ui.component.quiz.dto

import fm.force.quiz.common.dto.ErrorMessage
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.reducer.action.of
import fm.force.ui.util.isFalsy

fun QuizEditDTO.validate() {
    if (title.isFalsy) {
        throw SubmissionError.of(
            QuizEditDTO::title.name,
            ErrorMessage("Title must not be empty")
        )
    }

    if (questionWrappers.isFalsy || questionWrappers.mapNotNull { it.question }.isEmpty()) {
        throw SubmissionError.of(
            "${QuizEditDTO::questionWrappers.name}._error",
            ErrorMessage("Questions must not be empty")
        )
    }
}
