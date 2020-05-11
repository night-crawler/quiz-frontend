package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class TopicFullDTO(
    @ContextualSerialization
    val id: Long,
    val title: String,
    val slug: String,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TopicFullDTO) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (slug != other.slug) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + slug.hashCode()
        return result
    }
}

@Serializable
data class TopicRestrictedDTO(
    @ContextualSerialization
    val id: Long,

    val title: String,
    val slug: String
) : DTORestrictedSerializationMarker

@Serializable
data class TopicPatchDTO(val title: String) : DTOMarker
