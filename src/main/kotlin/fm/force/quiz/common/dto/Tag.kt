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
) : DTOFullSerializationMarker {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TagFullDTO) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (slug != other.slug) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + slug.hashCode()
        return result
    }
}

@Serializable
data class TagRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val name: String,
    val slug: String
) : DTORestrictedSerializationMarker

@Serializable
data class TagPatchDTO(val name: String) : DTOMarker
