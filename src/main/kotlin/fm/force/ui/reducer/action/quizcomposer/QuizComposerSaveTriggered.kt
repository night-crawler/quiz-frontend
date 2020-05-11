package fm.force.ui.reducer.action.quizcomposer

import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.QuizComposer
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.ShowSnack
import fm.force.ui.reducer.action.Snack
import fm.force.ui.reducer.toPatchDTO
import fm.force.ui.util.IconName
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class QuizComposerSaveTriggered(
    private val quizComposer: QuizComposer
) : Thunk<State, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE") client: QuizClient
    ): WrapperAction {
        val quiz = if (quizComposer.id == 0L) {
            client.createQuiz(quizComposer.toPatchDTO())
        } else {
            client.patchQuiz(quizComposer.id, quizComposer.toPatchDTO())
        }
        dispatch(QuizComposerBootstrap(quiz.id))
        return dispatch(
            ShowSnack(
                Snack(
                    title = "Quiz form saved",
                    text = "Quiz ${quizComposer.title} saved",
                    iconName = IconName.SAVE,
                    timeout = null
                )
            )
        )
    }
}
