package fm.force.quiz.common.dto

import kotlinx.serialization.Serializable

@Serializable
data class RemainingSessionQuestionCount(
    val count: Long
) : DTOMarker
