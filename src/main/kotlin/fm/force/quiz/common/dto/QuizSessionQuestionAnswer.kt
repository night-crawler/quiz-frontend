package fm.force.quiz.common.dto

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class QuizSessionQuestionAnswerFullDTO(
    @ContextualSerialization
    val id: Long,

    @ContextualSerialization
    val quizSession: Long,

    @ContextualSerialization
    val quizSessionQuestion: Long,

    @ContextualSerialization
    val originalAnswer: Long?,

    val text: String,
    val isCorrect: Boolean
) : DTOFullSerializationMarker

@Serializable
data class QuizSessionQuestionAnswerRestrictedDTO(
    @ContextualSerialization
    val id: Long,

    val text: String
)
