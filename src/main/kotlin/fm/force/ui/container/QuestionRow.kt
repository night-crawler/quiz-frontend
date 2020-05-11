package fm.force.ui.container

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.component.question.list.QuestionRow
import fm.force.ui.component.question.list.QuestionRowProps
import fm.force.ui.reducer.QuestionSelectToggled
import fm.force.ui.reducer.QuestionUnset
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.DrawerOpenToggle
import fm.force.ui.reducer.action.auth.LogoutThunk
import fm.force.ui.reducer.action.SetThemeType
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

private val mapStateToProps: QuestionRowStateProps.(State, QuestionRowConnectedProps) -> Unit = { state, connectedProps ->
    isSelected = connectedProps.question in state.selectedQuestions
    question = connectedProps.question
}

private val mapDispatchToProps: QuestionRowDispatchProps.((RAction) -> WrapperAction, QuestionRowConnectedProps) -> Unit =
    { dispatch, connectedProps ->
        onDelete = {
            connectedProps.onDelete(it)
            dispatch(QuestionUnset(it))
        }
        onSelectToggle = { dispatch(QuestionSelectToggled(it)) }
    }

val questionRow: RClass<QuestionRowConnectedProps> =
    rConnect<State, RAction, WrapperAction, QuestionRowConnectedProps, QuestionRowStateProps, QuestionRowDispatchProps, QuestionRowProps>(
        mapStateToProps,
        mapDispatchToProps
    )(QuestionRow::class.rClass)
