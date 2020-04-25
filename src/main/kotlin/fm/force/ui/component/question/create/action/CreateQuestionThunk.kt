package fm.force.ui.component.question.create.action

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.defaultSubmitErrorHandler
import fm.force.ui.component.question.dto.QuestionEditDTO
import fm.force.ui.component.question.dto.toPatchDTO
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ThunkForm
import mu.KotlinLogging
import react.router.connected.replace
import redux.RAction
import redux.WrapperAction

class CreateQuestionStart(val questionEditDTO: QuestionEditDTO) : RAction

class CreateQuestionSuccess(val questionEditDTO: QuestionFullDTO) : RAction

private val logger = KotlinLogging.logger("CreateQuestionThunk")

class CreateQuestionThunk(private val editDTO: QuestionEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(CreateQuestionStart(editDTO)) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            editDTO.validate()

            val answers = client.createAnswers(editDTO.answers)
            val questionDTO = editDTO.toPatchDTO(answers)

            val question = client.createQuestion(questionDTO)
            dispatch(CreateQuestionSuccess(question))
            dispatch(replace("/questions/${question.id}/edit").unsafeCast<RAction>())
        }
    }
}
