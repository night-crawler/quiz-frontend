package fm.force.ui.component.quiz.action

import fm.force.quiz.common.dto.QuizFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.defaultSubmitErrorHandler
import fm.force.ui.component.quiz.dto.QuizEditDTO
import fm.force.ui.component.quiz.dto.toPatchDTO
import fm.force.ui.component.quiz.dto.validate
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ThunkForm
import mu.KotlinLogging
import react.router.connected.replace
import redux.RAction
import redux.WrapperAction

class CreateQuizStart(val quizEditDTO: QuizEditDTO) : RAction

class CreateQuizSuccess(val quizEditDTO: QuizFullDTO) : RAction

private val logger = KotlinLogging.logger("CreateQuizThunk")

class CreateQuizThunk(private val editDTO: QuizEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(CreateQuizStart(editDTO)) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            editDTO.validate()
            val quiz = client.createQuiz(editDTO.toPatchDTO())
            dispatch(CreateQuizSuccess(quiz))
            dispatch(replace("/quizzes/${quiz.id}/edit").unsafeCast<RAction>())
        }
    }
}
