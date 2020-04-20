package fm.force.ui.component.question.create.action

import fm.force.quiz.common.dto.AnswerPatchDTO
import fm.force.ui.client.FetchError
import fm.force.ui.client.QuizClient
import fm.force.ui.component.question.create.AnswerEditDTO
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.reducer.action.of
import fm.force.ui.util.deepSet
import fm.force.ui.util.runParallelIndexed

suspend fun QuizClient.createAnswers(patches: Array<AnswerEditDTO>) =
    patches.runParallelIndexed { index, answer ->
        try {
            createAnswer(AnswerPatchDTO(answer.text))
        } catch (ex: FetchError) {
            val nestedError = deepSet(
                "answers[$index]",
                SubmissionError.of(ex).errors
            )
            // {answers: [{text: [GenericError, ...]}]}
            throw SubmissionError(nestedError)
        }
    }

suspend fun QuizClient.updateAnswers(patches: Array<AnswerEditDTO>) =
    patches.runParallelIndexed { index, answer ->
        try {
            patchAnswer(answer.id!!, AnswerPatchDTO(answer.text))
        } catch (ex: FetchError) {
            val nestedError = deepSet(
                "answers[$index]",
                SubmissionError.of(ex).errors
            )
            // {answers: [{text: [GenericError, ...]}]}
            throw SubmissionError(nestedError)
        }
    }
