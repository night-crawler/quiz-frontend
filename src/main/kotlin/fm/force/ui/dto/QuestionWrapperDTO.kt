package fm.force.ui.dto

import com.benasher44.uuid.uuid4
import fm.force.quiz.common.dto.QuestionFullDTO

data class QuestionWrapperDTO(
    val question: QuestionFullDTO? = null,
    val uuid: String = uuid4().toString()
) {
    companion object
}

fun QuestionWrapperDTO.Companion.of() =
    QuestionWrapperDTO()

fun QuestionFullDTO.toQuestionWrapperDTO() =
    QuestionWrapperDTO(question = this)
