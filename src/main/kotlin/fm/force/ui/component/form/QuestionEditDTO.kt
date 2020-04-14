package fm.force.ui.component.form

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO

data class QuestionEditDTO(
    val tags: List<TagFullDTO>,
    val topics: List<TopicFullDTO>,
    val text: String,
    val answers: List<AnswerEditDTO>
)
