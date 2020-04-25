package fm.force.ui.component.question.create.action

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.defaultSubmitErrorHandler
import fm.force.ui.component.question.dto.QuestionEditDTO
import fm.force.ui.component.question.dto.toPatchDTO
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ThunkForm
import mu.KotlinLogging
import redux.RAction
import redux.WrapperAction

class EditQuestionStart(val questionEditDTO: QuestionEditDTO) : RAction

class EditQuestionSuccess(val questionEditDTO: QuestionFullDTO) : RAction

private val logger = KotlinLogging.logger("EditQuestionThunk")

class EditQuestionThunk(private val editDTO: QuestionEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(EditQuestionStart(editDTO)) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            editDTO.validate()

            val modifiedAnswers = editDTO.answers.filter { it.id != null }.toTypedArray()
            val newAnswers = editDTO.answers.filter { it.id == null }.toTypedArray()

            val answers = client.updateAnswers(modifiedAnswers) + client.createAnswers(newAnswers)
            val questionDTO = editDTO.toPatchDTO(answers)

            dispatch(EditQuestionSuccess(client.patchQuestion(editDTO.id!!, questionDTO)))
        }
    }
}
