package fm.force.ui.component.question.create.action

import fm.force.quiz.common.dto.*
import fm.force.ui.client.QuizClient
import fm.force.ui.component.question.create.QuestionEditDTO
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ShowSnack
import fm.force.ui.reducer.action.Snack
import fm.force.ui.reducer.action.ThunkForm
import fm.force.ui.util.IconName
import mu.KotlinLogging
import redux.RAction
import redux.WrapperAction

class EditQuestionStart(val questionEditDTO: QuestionEditDTO) : RAction

class EditQuestionSuccess(val questionEditDTO: QuestionFullDTO) : RAction

private val logger = KotlinLogging.logger("EditQuestionThunk")

class EditQuestionThunk(private val questionEditDTO: QuestionEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(EditQuestionStart(questionEditDTO)) },
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
            questionEditDTO.validate()

            val modifiedAnswers = questionEditDTO.answers.filter { it.id != null }.toTypedArray()
            val newAnswers = questionEditDTO.answers.filter { it.id == null }.toTypedArray()

            val answers = client.updateAnswers(modifiedAnswers) + client.createAnswers(newAnswers)
            val questionDTO = QuestionPatchDTO(
                title = questionEditDTO.title,
                text = questionEditDTO.text,
                correctAnswers = questionEditDTO.getCorrectAnswerIds(answers),
                difficulty = questionEditDTO.difficulty,
                answers = answers.map(AnswerFullDTO::id).toSet(),
                tags = questionEditDTO.tags.map(TagFullDTO::id).toSet(),
                topics = questionEditDTO.topics.map(TopicFullDTO::id).toSet()
            )

            dispatch(EditQuestionSuccess(client.patchQuestion(questionEditDTO.id!!, questionDTO)))
        }
    }
}
