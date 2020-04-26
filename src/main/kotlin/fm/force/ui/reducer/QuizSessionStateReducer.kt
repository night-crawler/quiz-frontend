package fm.force.ui.reducer

import fm.force.ui.reducer.action.session.*
import redux.RAction

fun quizSessionStateReducer(state: QuizSessionState = QuizSessionState.of(), action: RAction): QuizSessionState = when (action) {
    is SessionSequenceSet -> state.copy(seq = action.seq)
    is SessionQuestionsLoaded -> state.copy(questions = action.questions)
    is SessionDifficultyScaleLoaded -> state.copy(difficultyScale = action.difficultyScale)
    is SessionLoaded -> state.copy(session = action.session)
    is SessionQuizLoaded -> state.copy(quiz = action.quiz)
    is SessionAnswerToggled -> run {
        val checkedAnswers = state.answerMap[action.question.id] ?: setOf()
        if (action.answer.id in checkedAnswers) {
            state.copy(
                answerMap = state.answerMap + mapOf(
                    action.question.id to checkedAnswers - action.answer.id
                )
            )
        } else {
            state.copy(
                answerMap = state.answerMap + mapOf(
                    action.question.id to checkedAnswers + action.answer.id
                )
            )
        }
    }
    else -> state
}
