package fm.force.ui.component.form

import fm.force.ui.client.dto.TagFullDTO
import fm.force.ui.client.dto.TopicFullDTO

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
