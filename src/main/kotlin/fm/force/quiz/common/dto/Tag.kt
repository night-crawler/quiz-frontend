package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class TagFullDTO(
    @ContextualSerialization
    val id: Long,
    val name: String,
    val slug: String,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
data class TagRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val name: String,
    val slug: String
) : DTORestrictedSerializationMarker

@Serializable
data class TagPatchDTO(val name: String) : DTOMarker
