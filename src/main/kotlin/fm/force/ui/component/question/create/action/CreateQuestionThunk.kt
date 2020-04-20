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
import react.router.connected.replace
import redux.RAction
import redux.WrapperAction

class CreateQuestionStart(val questionEditDTO: QuestionEditDTO) : RAction

class CreateQuestionSuccess(val questionEditDTO: QuestionFullDTO) : RAction

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
            questionEditDTO.validate()

            val answers = client.createAnswers(questionEditDTO.answers)
            val questionDTO = QuestionPatchDTO(
                title = questionEditDTO.title,
                text = questionEditDTO.text,
                correctAnswers = questionEditDTO.getCorrectAnswerIds(answers),
                difficulty = questionEditDTO.difficulty,
                answers = answers.map(AnswerFullDTO::id).toSet(),
                tags = questionEditDTO.tags.map(TagFullDTO::id).toSet(),
                topics = questionEditDTO.topics.map(TopicFullDTO::id).toSet()
            )

            val question = client.createQuestion(questionDTO)
            dispatch(CreateQuestionSuccess(question))
            dispatch(replace("/questions/${question.id}/edit").unsafeCast<RAction>())
        }
    }
}
