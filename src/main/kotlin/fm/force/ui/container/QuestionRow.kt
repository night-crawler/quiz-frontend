package fm.force.ui.container

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.component.question.list.QuestionRow
import fm.force.ui.component.question.list.QuestionRowProps
import fm.force.ui.reducer.SelectedQuestionsQuestionUnset
import fm.force.ui.reducer.SelectedQuestionsSelectToggled
import fm.force.ui.reducer.state.QuizState
import react.RClass
import react.RProps
import react.invoke
import react.rClass
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface QuestionRowConnectedProps : QuestionRowProps

private interface QuestionRowStateProps : RProps {
    var isSelected: Boolean
    var question: QuestionFullDTO
}

private interface QuestionRowDispatchProps : RProps {
    var onDelete: (question: QuestionFullDTO) -> Unit
    var onSelectToggle: (question: QuestionFullDTO) -> Unit
}

private val mapStateToProps: QuestionRowStateProps.(QuizState, QuestionRowConnectedProps) -> Unit =
    { state, connectedProps ->
        isSelected = connectedProps.question in state.selectedQuestions
        question = connectedProps.question
    }

private val mapDispatchToProps: QuestionRowDispatchProps.((RAction) -> WrapperAction, QuestionRowConnectedProps) -> Unit =
    { dispatch, connectedProps ->
        onDelete = {
            connectedProps.onDelete(it)
            dispatch(SelectedQuestionsQuestionUnset(it))
        }
        onSelectToggle = { dispatch(SelectedQuestionsSelectToggled(it)) }
    }

val questionRow: RClass<QuestionRowConnectedProps> =
    rConnect<QuizState, RAction, WrapperAction, QuestionRowConnectedProps, QuestionRowStateProps, QuestionRowDispatchProps, QuestionRowProps>(
        mapStateToProps,
        mapDispatchToProps
    )(QuestionRow::class.rClass)
