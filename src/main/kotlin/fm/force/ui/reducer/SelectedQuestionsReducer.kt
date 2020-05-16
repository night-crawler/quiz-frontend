package fm.force.ui.reducer

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.reducer.action.auth.LogoutSuccess
import redux.RAction

class SelectedQuestionsSelectToggled(val question: QuestionFullDTO) : RAction
class SelectedQuestionsQuestionUnset(val question: QuestionFullDTO) : RAction
class SelectedQuestionsAllUnselected : RAction
class SelectedQuestionsSelectedAllOnPage(val questions: Collection<QuestionFullDTO>) : RAction
class SelectedQuestionsSentToComposer(val questions: Collection<QuestionFullDTO>) : RAction

fun selectedQuestionsReducer(state: Set<QuestionFullDTO> = setOf(), action: RAction): Set<QuestionFullDTO> =
    when (action) {
        is SelectedQuestionsSelectToggled -> if (action.question in state) state - action.question else state + action.question
        is SelectedQuestionsQuestionUnset -> state - action.question
        is SelectedQuestionsSelectedAllOnPage -> state + action.questions
        is SelectedQuestionsAllUnselected -> setOf()
        is SelectedQuestionsSentToComposer -> setOf()
        is LogoutSuccess -> setOf()
        else -> state
    }
