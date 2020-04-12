package fm.force.quiz.common.dto

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class QuizSessionQuestionFullDTO(
    @ContextualSerialization
    val id: Long
) : DTOFullSerializationMarker

@Serializable
data class QuizSessionQuestionRestrictedDTO(
    @ContextualSerialization
    val id: Long,

    val text: String,
    val answers: List<QuizSessionQuestionAnswerRestrictedDTO>,
    val seq: Int
) : DTORestrictedSerializationMarker

@Serializable
data class QuizSessionQuestionPatchDTO(
    @ContextualSerialization
    val id: Long?
) : DTOMarker

@Serializable
data class QuizSessionQuestionSearchDTO(
    @ContextualSerialization
    var quizSession: Long? = null,
    @ContextualSerialization
    val originalQuestion: Long? = null,
    val text: String? = null
) : DTOSearchMarker
