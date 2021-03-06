package fm.force.ui.reducer.state

import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.quiz.common.dto.QuizRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO

data class QuizSessionState(
    val seq: Int,
    val questions: List<QuizSessionQuestionRestrictedDTO>,
    val difficultyScale: DifficultyScaleFullDTO?,
    val session: QuizSessionFullDTO?,
    val quiz: QuizRestrictedDTO?,
    val answerMap: Map<Long, Set<Long>>,
    val correctAnswerMap: Map<Long, Set<Long>>,
    val submittedQuestions: Set<Long>,
    val remainingCount: Long
) {
    companion object
}

fun QuizSessionState.Companion.of() =
    QuizSessionState(
        seq = 0,
        questions = listOf(),
        difficultyScale = null,
        session = null,
        answerMap = mapOf(),
        quiz = null,
        submittedQuestions = setOf(),
        correctAnswerMap = mapOf(),
        remainingCount = 0
    )
