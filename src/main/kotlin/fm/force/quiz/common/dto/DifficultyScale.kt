package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class DifficultyScaleFullDTO(
    @ContextualSerialization
    val id: Long,

    val name: String,
    val max: Int,
    val difficultyScaleRanges: Collection<DifficultyScaleRangeFullDTO>,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
data class DifficultyScaleRestrictedDTO(
    @ContextualSerialization
    val id: Long,
    val name: String,
    val max: Int,
    val difficultyScaleRanges: Collection<DifficultyScaleRangeRestrictedDTO>
) : DTORestrictedSerializationMarker

@Serializable
data class DifficultyScalePatchDTO(
    val name: String? = null,
    val max: Int? = null,
    val ranges: Collection<DifficultyScaleRangePatchDTO>? = null
) : DTOMarker
