package fm.force.quiz.common.dto

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class QuizSessionAnswerFullDTO(
    @ContextualSerialization
    val id: Long
) : DTOFullSerializationMarker

@Serializable
data class QuizSessionAnswerRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    @ContextualSerialization
    val session: Long,
    val question: QuizSessionQuestionRestrictedDTO,
    val answers: List<QuizSessionQuestionAnswerRestrictedDTO>
) : DTORestrictedSerializationMarker

@Serializable
data class QuizSessionAnswerPatchDTO(
    @ContextualSerialization
    var session: Long = 0,

    @ContextualSerialization
    val question: Long,

    val answers: Set<Long>
) : DTOSerializationMarker

@Serializable
data class QuizSessionAnswerSearchDTO(
    @ContextualSerialization
    val id: Long? = null
) : DTOSearchMarker
