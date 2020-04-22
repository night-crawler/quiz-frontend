package fm.force.ui.component.quiz.create

import fm.force.quiz.common.dto.ErrorMessage
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.reducer.action.of
import fm.force.ui.util.isFalsy

data class QuizEditDTO(
    val id: Long? = null,
    val title: String,
    val tags: Array<TagFullDTO>,
    val topics: Array<TopicFullDTO>,
    val questionWrappers: Array<QuestionWrapperDTO>
//    val difficultyScale: DifficultyScaleFullDTO?,
) {
    companion object
}

fun QuizEditDTO.Companion.of() = QuizEditDTO(
    id = null,
    title = "",
    tags = arrayOf(),
    topics = arrayOf(),
    questionWrappers = arrayOf()
)


fun QuizEditDTO.validate() {
    if (title.isFalsy) {
        throw SubmissionError.of(
            QuizEditDTO::title.name,
            ErrorMessage("Title must not be empty")
        )
    }

    if (questionWrappers.isFalsy || questionWrappers?.mapNotNull { it.question }.isEmpty()) {
        throw SubmissionError.of(
            "${QuizEditDTO::questionWrappers.name}._error",
            ErrorMessage("Questions must not be empty")
        )
    }
}
