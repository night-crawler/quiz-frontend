package fm.force.ui.container

import fm.force.ui.component.QuizComposer
import fm.force.ui.component.QuizComposerProps
import fm.force.ui.reducer.*
import fm.force.ui.reducer.action.quizcomposer.QuizComposerBootstrap
import fm.force.ui.reducer.action.quizcomposer.QuizComposerSaveTriggered
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface QuizComposerConnectedProps : RProps

private interface QuizComposerStateProps : RProps {
    var state: State
}

private interface QuizComposerDispatchProps : RProps {
    var dispatch: (RAction) -> WrapperAction
}

private val mapStateToProps: QuizComposerStateProps.(State, QuizComposerConnectedProps) -> Unit = { state, _ ->
    this.state = state
}

private val mapDispatchToProps: QuizComposerDispatchProps.((RAction) -> WrapperAction, QuizComposerConnectedProps) -> Unit =
    { dispatch, _ ->
        this.dispatch = dispatch
    }

val quizComposer: RClass<QuizComposerConnectedProps> =
    rConnect<State, RAction, WrapperAction, QuizComposerConnectedProps, QuizComposerStateProps, QuizComposerDispatchProps, QuizComposerProps>(
        mapStateToProps,
        mapDispatchToProps,
        { stateProps, dispatchProps, _ ->
            val state = stateProps.state
            val dispatch = dispatchProps.dispatch

            questions = state.quizComposer.questions
            title = state.quizComposer.title
            topics = state.quizComposer.topics
            tags = state.quizComposer.tags
            difficultyScale = state.quizComposer.difficultyScale

            bootstrap = { dispatch(QuizComposerBootstrap(it)) }
            onTagsChanged = { dispatch(QuizComposerTagsChanged(it)) }
            onTopicsChanged = { dispatch(QuizComposerTopicsChanged(it)) }
            onTitleChanged = { dispatch(QuizComposerTitleChanged(it)) }
            onDifficultyScaleChanged = { dispatch(QuizComposerDifficultyScaleChanged(it)) }
            onSave = { dispatch(
                QuizComposerSaveTriggered(
                    state.quizComposer
                )
            ) }
        }
    )(QuizComposer.unsafeCast<RClass<QuizComposerProps>>())
