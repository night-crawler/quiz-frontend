package fm.force.ui.reducer

import fm.force.ui.reducer.action.auth.LogoutSuccess
import fm.force.ui.reducer.action.session.*
import fm.force.ui.reducer.state.QuizSessionState
import fm.force.ui.reducer.state.of
import redux.RAction

fun quizSessionStateReducer(state: QuizSessionState = QuizSessionState.of(), action: RAction): QuizSessionState =
    when (action) {
        is SessionGoFirstUnanswered -> state.copy(
            seq = state.questions
                .firstOrNull { state.answerMap[it.id]?.isEmpty() ?: true }
                ?.seq
                ?: state.seq
        )

        is SessionGoLastUnanswered -> state.copy(
            seq = state.questions
                .lastOrNull { state.answerMap[it.id]?.isEmpty() ?: true }
                ?.seq
                ?: state.seq
        )

        is SessionSetSequence -> state.copy(seq = action.seq)
        is SessionQuestionsLoaded -> state.copy(questions = action.questions)
        is SessionAnswersLoaded -> state.copy(
            answerMap = state.answerMap + action.answers.map { it.question to it.answers },
            submittedQuestions = state.submittedQuestions + action.answers.map { it.question }.toSet(),
            correctAnswerMap = state.correctAnswerMap + action.answers.map { it.question to it.correctAnswers }
        )
        is SessionDifficultyScaleLoaded -> state.copy(difficultyScale = action.difficultyScale)
        is SessionLoaded -> state.copy(session = action.session)
        is SessionQuizLoaded -> state.copy(quiz = action.quiz)
        is SessionRemainingCountSet -> state.copy(remainingCount = action.count)
        is SessionDecreaseRemainingCount -> state.copy(remainingCount = state.remainingCount - 1)
        is SessionAnswerToggle -> run {
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
        is LogoutSuccess -> QuizSessionState.of()
        else -> state
    }
