package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class QuestionFullDTO(
    @ContextualSerialization
    val id: Long,

    val text: String,
    val answers: Collection<AnswerFullDTO>,
    val correctAnswers: Collection<AnswerFullDTO>,
    val tags: Collection<TagFullDTO>,
    val topics: Collection<TopicFullDTO>,
    val difficulty: Int,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker

@Serializable
data class QuestionRestrictedDTO(
    @ContextualSerialization
    val id: Long,

    val text: String,
    val answers: Collection<AnswerRestrictedDTO>,
    val difficulty: Int
) : DTORestrictedSerializationMarker

@Serializable
data class QuestionPatchDTO(
    val text: String? = null,
    val answers: Set<Long>? = null,
    val correctAnswers: Set<Long>? = null,
    val tags: Set<Long>? = null,
    val topics: Set<Long>? = null,
    val difficulty: Int? = 0
) : DTOMarker
