package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class DifficultyScaleRangeFullDTO(
    @ContextualSerialization
    val id: Long,

    @ContextualSerialization
    val difficultyScale: Long,

    val title: String,
    val min: Int,
    val max: Int,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
data class DifficultyScaleRangeRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val title: String,
    val min: Int,
    val max: Int
) : DTORestrictedSerializationMarker

@Serializable
data class DifficultyScaleRangePatchDTO(
    val difficultyScale: Long? = null,
    val title: String? = null,
    val min: Int? = null,
    val max: Int? = null
) : DTOMarker

@Serializable
data class DifficultyScaleRangeSearchDTO(
    val difficultyScale: Long? = null,
    val title: String? = null
) : DTOSearchMarker
