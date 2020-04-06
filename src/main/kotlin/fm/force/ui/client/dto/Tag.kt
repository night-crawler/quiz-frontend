package fm.force.ui.client.dto

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("TagFullDTO")
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
@SerialName("TagRestrictedDTO")
data class TagRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val name: String,
    val slug: String
) : DTORestrictedSerializationMarker

@Serializable
@SerialName("TagPatchDTO")
data class TagPatchDTO(val name: String) : DTOMarker
