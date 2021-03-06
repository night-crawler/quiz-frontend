package fm.force.ui.reducer.action.question

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.main.defaultSubmitErrorHandler
import fm.force.ui.dto.QuestionEditDTO
import fm.force.ui.dto.toPatchDTO
import fm.force.ui.dto.validate
import fm.force.ui.reducer.action.ThunkForm
import fm.force.ui.reducer.state.QuizState
import react.router.connected.replace
import redux.RAction
import redux.WrapperAction

class CreateQuestionStart(val questionEditDTO: QuestionEditDTO) : RAction

class CreateQuestionSuccess(val questionEditDTO: QuestionFullDTO) : RAction

class CreateQuestionThunk(private val editDTO: QuestionEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> QuizState,
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
            // wonderful refresh
            dispatch(replace("/"))
            dispatch(replace("/questions/create").unsafeCast<RAction>())
        }
    }
}
