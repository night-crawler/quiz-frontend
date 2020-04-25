package fm.force.ui.component.quiz.dto

import fm.force.quiz.common.dto.*

data class QuizEditDTO(
    val id: Long? = null,
    val title: String,
    val tags: Array<TagFullDTO>,
    val topics: Array<TopicFullDTO>,
    val questionWrappers: Array<QuestionWrapperDTO>,
    val difficultyScale: DifficultyScaleFullDTO?
) {
    companion object
}

fun QuizEditDTO.Companion.of() =
    QuizEditDTO(
        id = null,
        title = "",
        tags = arrayOf(),
        topics = arrayOf(),
        questionWrappers = arrayOf(),
        difficultyScale = null
    )

fun QuizFullDTO.toEditDTO() = QuizEditDTO(
    id = id,
    title = title,
    tags = tags.toTypedArray(),
    topics = topics.toTypedArray(),
    questionWrappers = quizQuestions.map { it.question.toQuestionWrapperDTO() }.toTypedArray(),
    difficultyScale = difficultyScale
)

fun QuizEditDTO.toPatchDTO() = QuizPatchDTO(
    title = title,
    topics = topics.map { it.id }.toSet(),
    tags = tags.map { it.id }.toSet(),
    difficultyScale = difficultyScale?.id,
    questions = questionWrappers.mapNotNull { it.question?.id }
)
