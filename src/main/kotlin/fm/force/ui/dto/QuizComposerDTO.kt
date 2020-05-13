package fm.force.ui.dto

import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO

data class QuizComposerDTO(
    var id: Long,
    val title: String,
    val tags: List<TagFullDTO>,
    val topics: List<TopicFullDTO>,
    val questions: List<QuestionFullDTO>,
    val difficultyScale: DifficultyScaleFullDTO?
) {
    companion object
}

fun QuizComposerDTO.Companion.of() =
    QuizComposerDTO(
        id = 0,
        questions = listOf(),
        tags = listOf(),
        topics = listOf(),
        title = "",
        difficultyScale = null
    )
