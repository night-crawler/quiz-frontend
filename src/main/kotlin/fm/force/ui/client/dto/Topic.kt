package fm.force.ui.client.dto

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("TopicFullDTO")
data class TopicFullDTO(
    @ContextualSerialization
    val id: Long,
    val title: String,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
@SerialName("TopicRestrictedDTO")
data class TopicRestrictedDTO(
    @ContextualSerialization
    val id: Long,

    val title: String
) : DTORestrictedSerializationMarker

@Serializable
@SerialName("TopicPatchDTO")
data class TopicPatchDTO(val title: String) : DTOMarker
