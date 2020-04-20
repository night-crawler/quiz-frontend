package fm.force.ui.component.question.create

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO

data class QuestionEditDTO(
    val tags: Array<TagFullDTO>,
    val topics: Array<TopicFullDTO>,
    val title: String,
    val text: String,
    val difficulty: Int,
    val answers: Array<AnswerEditDTO>
) {
    companion object
}

fun QuestionEditDTO.Companion.of() = QuestionEditDTO(
    tags = emptyArray(),
    topics = emptyArray(),
    title = "",
    text = "",
    difficulty = 0,
    answers = emptyArray()
)
