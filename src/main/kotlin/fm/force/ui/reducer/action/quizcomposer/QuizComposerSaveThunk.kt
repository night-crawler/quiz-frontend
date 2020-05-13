package fm.force.ui.reducer.action.quizcomposer

import fm.force.ui.client.QuizClient
import fm.force.ui.dto.QuizComposerDTO
import fm.force.ui.reducer.action.ShowSnack
import fm.force.ui.reducer.action.Snack
import fm.force.ui.reducer.state.QuizState
import fm.force.ui.util.Icon
import fm.force.ui.util.Thunk
import react.router.connected.push
import redux.RAction
import redux.WrapperAction

class QuizComposerSaveThunk(
    private val quizComposerDTO: QuizComposerDTO
) : Thunk<QuizState, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> QuizState,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        if (quizComposerDTO.id == 0L) {
            client.createQuiz(quizComposerDTO.toPatchDTO())
        } else {
            client.patchQuiz(quizComposerDTO.id, quizComposerDTO.toPatchDTO())
        }
        dispatch(QuizComposerClear())
        dispatch(
            ShowSnack(
                Snack(
                    title = "Quiz form saved",
                    text = "Quiz ${quizComposerDTO.title} saved",
                    icon = Icon.SAVE,
                    timeout = null
                )
            )
        )

        return dispatch(push("/quizzes"))
    }
}
