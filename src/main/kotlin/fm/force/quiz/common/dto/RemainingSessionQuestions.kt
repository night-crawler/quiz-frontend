package fm.force.quiz.common.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemainingSessionQuestions(
    val quizSessionQuestionIds: Collection<Long>
) : DTOMarker
