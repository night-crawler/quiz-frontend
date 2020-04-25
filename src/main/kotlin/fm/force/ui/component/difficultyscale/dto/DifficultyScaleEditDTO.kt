package fm.force.ui.component.difficultyscale.dto

import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.quiz.common.dto.DifficultyScalePatchDTO
import fm.force.quiz.common.dto.DifficultyScaleRangeFullDTO

data class DifficultyScaleEditDTO(
    val id: Long?,
    val name: String,
    val max: Int,
    val ranges: Array<DifficultyScaleRangeEditDTO>
) {
    companion object
}

fun DifficultyScaleEditDTO.Companion.of() =
    DifficultyScaleEditDTO(
        id = null,
        name = "",
        max = 10,
        ranges = arrayOf()
    )

fun DifficultyScaleEditDTO.toPatchDTO() =
    DifficultyScalePatchDTO(
        name = name,
        max = max,
        ranges = ranges.map { it.toPatchDTO() }
    )

fun DifficultyScaleFullDTO.toEditDTO() = DifficultyScaleEditDTO(
    id = id,
    name = name,
    max = max,
    ranges = difficultyScaleRanges.map { it.toEditDTO() }.toTypedArray()
)

fun DifficultyScaleRangeFullDTO.toEditDTO() = DifficultyScaleRangeEditDTO(
    title = title,
    min = min,
    max = max
)
