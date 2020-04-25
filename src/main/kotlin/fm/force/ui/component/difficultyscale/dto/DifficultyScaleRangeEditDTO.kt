package fm.force.ui.component.difficultyscale.dto

import com.benasher44.uuid.uuid4
import fm.force.quiz.common.dto.DifficultyScaleRangePatchDTO

data class DifficultyScaleRangeEditDTO(
    val title: String,
    val min: Int,
    val max: Int,
    val uuid: String = uuid4().toString()
) {
    companion object
}

fun DifficultyScaleRangeEditDTO.Companion.of() =
    DifficultyScaleRangeEditDTO(
        title = "",
        min = 0,
        max = 0
    )

fun DifficultyScaleRangeEditDTO.toPatchDTO() = DifficultyScaleRangePatchDTO(
    title = title,
    min = min,
    max = max
)
