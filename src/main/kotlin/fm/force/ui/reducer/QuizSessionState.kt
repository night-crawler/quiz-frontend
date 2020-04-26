package fm.force.ui.reducer

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
    val answerMap: Map<Long, Set<Long>>
) {
    companion object
}
