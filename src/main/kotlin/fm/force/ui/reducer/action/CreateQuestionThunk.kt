package fm.force.ui.reducer.action

import fm.force.quiz.common.dto.AnswerFullDTO
import fm.force.quiz.common.dto.AnswerPatchDTO
import fm.force.quiz.common.dto.ErrorMessage
import fm.force.quiz.common.dto.QuestionPatchDTO
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.ui.client.FetchError
import fm.force.ui.client.QuizClient
import fm.force.ui.component.form.AnswerEditDTO
import fm.force.ui.component.form.QuestionEditDTO
import fm.force.ui.reducer.State
import fm.force.ui.util.DynamicIterator
import fm.force.ui.util.IconName
import fm.force.ui.util.deepSet
import fm.force.ui.util.dynamicIterator
import fm.force.ui.util.isFalsy
import fm.force.ui.util.runParallelIndexed
import mu.KotlinLogging
import redux.RAction
import redux.WrapperAction

class CreateQuestionStart(val questionEditDTO: QuestionEditDTO) : RAction

class CreateQuestionSuccess(val questionEditDTO: QuestionEditDTO) : RAction

private val logger = KotlinLogging.logger("CreateQuestionThunk")


class CreateQuestionThunk(private val questionEditDTO: QuestionEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(CreateQuestionStart(questionEditDTO)) },
            error = { original, transformed ->
                logger.error { "Original error: $original" }
                logger.error { "Transformed error: $transformed" }

                dispatch(
                    ShowSnack(
                        Snack(
                            title = "Submission error",
                            iconName = IconName.ERROR,
                            timeout = null
                        )
                    )
                )
            }
        ) {
            validate(questionEditDTO)

            val answers = createAnswers(client, questionEditDTO.answers)
            val correctAnswerIds = answers
                .zip(questionEditDTO.answers)
                .filter { (_, editDTO) -> editDTO.isCorrect }
                .map { (created, _) -> created.id }
                .toSet()

            val questionDTO = QuestionPatchDTO(
                title = questionEditDTO.title,
                text = questionEditDTO.text,
                correctAnswers = correctAnswerIds,
                difficulty = questionEditDTO.difficulty,
                answers = answers.map(AnswerFullDTO::id).toSet(),
                tags = questionEditDTO.tags.map(TagFullDTO::id).toSet(),
                topics = questionEditDTO.topics.map(TopicFullDTO::id).toSet()
            )

            val question = client.createQuestion(questionDTO)
            console.log(question)

            dispatch(CreateQuestionSuccess(questionEditDTO))
        }
    }

    private fun validate(questionEditDto: QuestionEditDTO) {
        if (questionEditDto.text.isFalsy) {
            throw SubmissionError.of("text", ErrorMessage("Text must not be empty"))
        }

        if (questionEditDto.answers.isFalsy) {
            throw SubmissionError.of("answers._error", ErrorMessage("Answers must not be empty"))
        }

        if (questionEditDto.answers.dynamicIterator<AnswerEditDTO>().asSequence().count { it.isCorrect } == 0) {
            throw SubmissionError.of("answers._error", ErrorMessage("At least one answer must be correct"))
        }
    }

    private suspend fun createAnswers(client: QuizClient, patches: Array<AnswerEditDTO>) =
        DynamicIterator<AnswerEditDTO>(patches).runParallelIndexed { index, answer ->
            try {
                client.createAnswer(AnswerPatchDTO(answer.text))
            } catch (ex: FetchError) {
                val nestedError = deepSet("answers[$index]", SubmissionError.of(ex).errors)
                // {answers: [{text: [GenericError, ...]}]}
                throw SubmissionError(nestedError)
            }
        }
}
