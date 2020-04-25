package fm.force.ui.component.quiz.action

import fm.force.quiz.common.dto.QuizFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.defaultSubmitErrorHandler
import fm.force.ui.component.quiz.dto.QuizEditDTO
import fm.force.ui.component.quiz.dto.toPatchDTO
import fm.force.ui.component.quiz.dto.validate
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ThunkForm
import react.router.connected.replace
import redux.RAction
import redux.WrapperAction

class EditQuizStart(val quizEditDTO: QuizEditDTO) : RAction

class EditQuizSuccess(val quizEditDTO: QuizFullDTO) : RAction

class EditQuizThunk(private val editDTO: QuizEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(EditQuizStart(editDTO)) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            editDTO.validate()

            val quiz = client.patchQuiz(editDTO.id!!, editDTO.toPatchDTO())
            dispatch(EditQuizSuccess(quiz))
            dispatch(replace("/quizzes/${quiz.id}/edit").unsafeCast<RAction>())
        }
    }
}
