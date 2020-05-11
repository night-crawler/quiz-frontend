package fm.force.ui.reducer

import fm.force.quiz.common.dto.QuestionFullDTO
import redux.RAction

class QuestionSelectToggled(val question: QuestionFullDTO) : RAction
class QuestionUnset(val question: QuestionFullDTO) : RAction

fun selectedQuestionsReducer(state: Set<QuestionFullDTO> = setOf(), action: RAction): Set<QuestionFullDTO> = when(action) {
    is QuestionSelectToggled -> if (action.question in state) state - action.question else state + action.question
    is QuestionUnset -> state - action.question
    else -> state
}
