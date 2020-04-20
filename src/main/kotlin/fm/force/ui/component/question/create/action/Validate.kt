package fm.force.ui.component.question.create.action

import fm.force.quiz.common.dto.AnswerFullDTO
import fm.force.quiz.common.dto.ErrorMessage
import fm.force.ui.component.question.create.AnswerEditDTO
import fm.force.ui.component.question.create.QuestionEditDTO
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.reducer.action.of
import fm.force.ui.util.dynamicIterator
import fm.force.ui.util.isFalsy

fun QuestionEditDTO.validate() {
    if (title.isFalsy) {
        throw SubmissionError.of(
            "title",
            ErrorMessage("Title must not be empty")
        )
    }

    if (text.isFalsy) {
        throw SubmissionError.of(
            "text",
            ErrorMessage("Text must not be empty")
        )
    }

    if (answers.isFalsy) {
        throw SubmissionError.of(
            "answers._error",
            ErrorMessage("Answers must not be empty")
        )
    }

    if (answers.dynamicIterator<AnswerEditDTO>().asSequence().count { it.isCorrect } == 0) {
        throw SubmissionError.of(
            "answers._error",
            ErrorMessage("At least one answer must be correct")
        )
    }
}

fun QuestionEditDTO.getCorrectAnswerIds(createdAnswers: Collection<AnswerFullDTO>) =
    createdAnswers
        .zip(this.answers)
        .filter { (_, editDTO) -> editDTO.isCorrect }
        .map { (created, _) -> created.id }
        .toSet()
