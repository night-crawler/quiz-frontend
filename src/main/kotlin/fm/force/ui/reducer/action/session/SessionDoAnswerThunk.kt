package fm.force.ui.reducer.action.session

import fm.force.quiz.common.dto.QuizSessionAnswerPatchDTO
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.state.QuizState
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class SessionDoAnswerThunk(
    val session: QuizSessionFullDTO,
    val question: QuizSessionQuestionRestrictedDTO,
    val answers: Set<Long>
) : Thunk<QuizState, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> QuizState,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        val answer = client.doAnswer(
            sessionId = session.id,
            patchDTO = QuizSessionAnswerPatchDTO(
                question = question.id,
                answers = answers
            )
        )
        dispatch(SessionDecreaseRemainingCount())
        return dispatch(SessionAnswersLoaded(listOf(answer)))
    }
}
