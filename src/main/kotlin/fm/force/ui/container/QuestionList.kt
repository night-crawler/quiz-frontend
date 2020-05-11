package fm.force.ui.container

import fm.force.ui.component.question.list.QuestionList
import fm.force.ui.component.question.list.QuestionListProps
import fm.force.ui.reducer.SelectedQuestionsAllUnselected
import fm.force.ui.reducer.SelectedQuestionsSelectedAllOnPage
import fm.force.ui.reducer.SelectedQuestionsSentToComposer
import fm.force.ui.reducer.State
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import react.router.connected.push
import redux.RAction
import redux.WrapperAction

interface QuestionListConnectedProps : RProps

private interface QuestionListStateProps : RProps {
    var state: State
}

private interface QuestionListDispatchProps : RProps {
    var dispatch: (RAction) -> WrapperAction
}

private val mapStateToProps: QuestionListStateProps.(State, QuestionListConnectedProps) -> Unit =
    { state, _ ->
        this.state = state
    }

private val mapDispatchToProps: QuestionListDispatchProps.((RAction) -> WrapperAction, QuestionListConnectedProps) -> Unit =
    { dispatch, _ ->
        this.dispatch = dispatch
    }

val questionList: RClass<QuestionListConnectedProps> =
    rConnect<State, RAction, WrapperAction, QuestionListConnectedProps, QuestionListStateProps, QuestionListDispatchProps, QuestionListProps>(
        mapStateToProps,
        mapDispatchToProps,
        { stateProps, dispatchProps, _ ->
            val state = stateProps.state
            val dispatch = dispatchProps.dispatch

            onSelectAllOnPage = { dispatch(SelectedQuestionsSelectedAllOnPage(it)) }
            selectedQuestionsCount = state.selectedQuestions.size
            onUnselectedAll = { dispatch(SelectedQuestionsAllUnselected()) }
            onSelectedQuestionsSentToComposer = {
                SelectedQuestionsSentToComposer(stateProps.state.selectedQuestions).run {
                    dispatchProps.dispatch(this)
                }
            }
            onOpenComposer = {
                dispatchProps.dispatch(push("/quizzes/${stateProps.state.quizComposer.id}/compose"))
            }
        }
    )(QuestionList.unsafeCast<RClass<QuestionListProps>>())
