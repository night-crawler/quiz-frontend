package fm.force.ui.component.form

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO


data class TagDTO(
    val id: Long,
    val name: String
)

data class QuestionEditDTO(
    val tags: List<TagFullDTO>,
    val topics: List<TopicFullDTO>,
    val name: String,
    val answers: List<Answer>
)
