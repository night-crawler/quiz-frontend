package fm.force.ui.container

import fm.force.ui.component.QuizComposer
import fm.force.ui.component.QuizComposerProps
import fm.force.ui.reducer.action.quizcomposer.*
import fm.force.ui.reducer.state.QuizState
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface QuizComposerConnectedProps : RProps

private interface QuizComposerStateProps : RProps {
    var state: QuizState
}

private interface QuizComposerDispatchProps : RProps {
    var dispatch: (RAction) -> WrapperAction
}

private val mapStateToProps: QuizComposerStateProps.(QuizState, QuizComposerConnectedProps) -> Unit = { state, _ ->
    this.state = state
}

private val mapDispatchToProps: QuizComposerDispatchProps.((RAction) -> WrapperAction, QuizComposerConnectedProps) -> Unit =
    { dispatch, _ ->
        this.dispatch = dispatch
    }

val quizComposer: RClass<QuizComposerConnectedProps> =
    rConnect<QuizState, RAction, WrapperAction, QuizComposerConnectedProps, QuizComposerStateProps, QuizComposerDispatchProps, QuizComposerProps>(
        mapStateToProps,
        mapDispatchToProps,
        { stateProps, dispatchProps, _ ->
            val state = stateProps.state
            val dispatch = dispatchProps.dispatch

            questions = state.quizComposerDTO.questions
            title = state.quizComposerDTO.title
            topics = state.quizComposerDTO.topics
            tags = state.quizComposerDTO.tags
            difficultyScale = state.quizComposerDTO.difficultyScale

            bootstrap = { dispatch(QuizComposerBootstrapThunk(it)) }
            onTagsChanged = { dispatch(QuizComposerSetTags(it)) }
            onTopicsChanged = {
                dispatch(
                    QuizComposerSetTopics(
                        it
                    )
                )
            }
            onTitleChanged = {
                dispatch(
                    QuizComposerSetTitle(
                        it
                    )
                )
            }
            onDifficultyScaleChanged = {
                dispatch(
                    QuizComposerSetDifficultyScale(
                        it
                    )
                )
            }
            onDeleteQuestion = {
                dispatch(
                    QuizComposerDeleteQuestion(
                        it
                    )
                )
            }
            onClear = { dispatch(QuizComposerClearQuestions()) }
            onSave = {
                dispatch(
                    QuizComposerSaveThunk(
                        state.quizComposerDTO
                    )
                )
            }
        }
    )(QuizComposer.unsafeCast<RClass<QuizComposerProps>>())
