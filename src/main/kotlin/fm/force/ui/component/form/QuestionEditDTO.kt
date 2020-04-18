package fm.force.ui.component.form

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO

data class QuestionEditDTO(
    val tags: Array<TagFullDTO>,
    val topics: Array<TopicFullDTO>,
    val title: String,
    val text: String,
    val difficulty: Int,
    val answers: Array<AnswerEditDTO>
)
