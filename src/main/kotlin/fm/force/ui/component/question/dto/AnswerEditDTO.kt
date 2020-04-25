package fm.force.ui.component.question.dto

import com.benasher44.uuid.uuid4
import fm.force.quiz.common.dto.AnswerFullDTO

data class AnswerEditDTO(
    val id: Long? = null,
    val text: String,
    val isCorrect: Boolean,

    // we need some unique id here to force react to use it in favour of just an array index
    val uuid: String = uuid4().toString()
) {
    companion object
}

fun AnswerEditDTO.Companion.of() =
    AnswerEditDTO(
        id = null,
        text = "",
        isCorrect = false
    )

fun AnswerFullDTO.toAnswerEditDTO(correctAnswers: Collection<AnswerFullDTO>) =
    AnswerEditDTO(
        id = id,
        text = text,
        isCorrect = this.id in correctAnswers.map { it.id }.toSet()
    )
