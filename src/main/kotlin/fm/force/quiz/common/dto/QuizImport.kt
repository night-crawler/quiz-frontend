package fm.force.quiz.common.dto

import kotlinx.serialization.Serializable

enum class QuizImportType {
    YAML
}

@Serializable
data class QuizImportDTO(
    val text: String,
    val type: QuizImportType
)
