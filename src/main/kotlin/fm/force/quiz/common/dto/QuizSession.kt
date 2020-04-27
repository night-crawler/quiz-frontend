package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class QuizSessionFullDTO(
    @ContextualSerialization
    val id: Long,

    @ContextualSerialization
    val quiz: Long?,

    @ContextualSerialization
    val difficultyScale: Long?,

    @ContextualSerialization
    val validTill: InstantAlias,

    val isCompleted: Boolean = false,
    val isCancelled: Boolean = false,

    @ContextualSerialization
    val completedAt: InstantAlias? = null,

    @ContextualSerialization
    val cancelledAt: InstantAlias? = null,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
data class QuizSessionPatchDTO(
    @ContextualSerialization
    var quiz: Long
) : DTOSerializationMarker

@Serializable
data class QuizSessionSearchDTO(
    @ContextualSerialization
    val quiz: Long? = null,
    val difficultyScale: Long? = null,
    val isCompleted: Boolean? = null,
    val isCancelled: Boolean? = null
) : DTOSearchMarker
