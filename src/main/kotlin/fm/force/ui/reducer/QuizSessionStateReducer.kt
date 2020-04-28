package fm.force.ui.reducer

import fm.force.ui.reducer.action.session.*
import redux.RAction

fun quizSessionStateReducer(state: QuizSessionState = QuizSessionState.of(), action: RAction): QuizSessionState =
    when (action) {
        is GoFirstUnanswered -> state.copy(
            seq = state.questions
                .firstOrNull { state.answerMap[it.id]?.isEmpty() ?: true }
                ?.seq
                ?: state.seq
        )

        is GoLastUnanswered -> state.copy(
            seq = state.questions
                .lastOrNull { state.answerMap[it.id]?.isEmpty() ?: true }
                ?.seq
                ?: state.seq
        )

        is SessionSequenceSet -> state.copy(seq = action.seq)
        is SessionQuestionsLoaded -> state.copy(questions = action.questions)
        is SessionAnswersLoaded -> state.copy(
            answerMap = state.answerMap + action.answers.map { it.question to it.answers },
            submittedQuestions = state.submittedQuestions + action.answers.map { it.question }.toSet()
        )
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
