package fm.force.ui.component.question.dto

import fm.force.quiz.common.dto.*
import fm.force.ui.component.question.create.action.getCorrectAnswerIds

data class QuestionEditDTO(
    val id: Long? = null,
    val tags: Array<TagFullDTO>,
    val topics: Array<TopicFullDTO>,
    val title: String,
    val text: String,
    val help: String,
    val difficulty: Int,
    val answers: Array<AnswerEditDTO>
) {
    companion object
}

fun QuestionEditDTO.Companion.of() =
    QuestionEditDTO(
        tags = emptyArray(),
        topics = emptyArray(),
        title = "",
        text = "",
        help = "",
        difficulty = 0,
        answers = emptyArray()
    )

fun QuestionFullDTO.toEditDTO() = QuestionEditDTO(
    id = id,
    tags = tags.toTypedArray(),
    topics = topics.toTypedArray(),
    answers = answers.map { it.toAnswerEditDTO(correctAnswers) }.toTypedArray(),
    title = title,
    text = text,
    help = help,
    difficulty = difficulty
)

fun QuestionEditDTO.toPatchDTO(createdAnswers: List<AnswerFullDTO>): QuestionPatchDTO {
    return QuestionPatchDTO(
        title = title,
        text = text,
        help = help,
        correctAnswers = getCorrectAnswerIds(createdAnswers),
        difficulty = difficulty,
        answers = createdAnswers.map(AnswerFullDTO::id).toSet(),
        tags = tags.map(TagFullDTO::id).toSet(),
        topics = topics.map(TopicFullDTO::id).toSet()
    )
}
