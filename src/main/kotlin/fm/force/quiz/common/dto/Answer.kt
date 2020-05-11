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
) : DTOFullSerializationMarker {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AnswerFullDTO) return false

        if (id != other.id) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }
}

@Serializable
data class AnswerRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val text: String
) : DTORestrictedSerializationMarker

@Serializable
data class AnswerPatchDTO(val text: String) : DTOMarker
