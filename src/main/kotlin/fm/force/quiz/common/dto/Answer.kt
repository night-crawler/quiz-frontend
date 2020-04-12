package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class AnswerFullDTO(
    @ContextualSerialization
    val id: Long,

    val text: String,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
data class AnswerRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val text: String
) : DTORestrictedSerializationMarker

@Serializable
data class AnswerPatchDTO(val text: String) : DTOMarker
