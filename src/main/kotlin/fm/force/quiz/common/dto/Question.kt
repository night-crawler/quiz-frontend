package fm.force.quiz.common.dto

import fm.force.quiz.common.serializer.InstantAlias
import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
data class QuestionFullDTO(
    @ContextualSerialization
    val id: Long,

    val title: String,
    val text: String,
    val help: String,
    val answers: Collection<AnswerFullDTO>,
    val correctAnswers: Collection<AnswerFullDTO>,
    val tags: Collection<TagFullDTO>,
    val topics: Collection<TopicFullDTO>,
    val difficulty: Int,

    @ContextualSerialization
    val createdAt: InstantAlias,

    @ContextualSerialization
    val updatedAt: InstantAlias
) : DTOFullSerializationMarker {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is QuestionFullDTO) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (text != other.text) return false
        if (help != other.help) return false
        if (answers != other.answers) return false
        if (correctAnswers != other.correctAnswers) return false
        if (tags != other.tags) return false
        if (topics != other.topics) return false
        if (difficulty != other.difficulty) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + help.hashCode()
        result = 31 * result + answers.hashCode()
        result = 31 * result + correctAnswers.hashCode()
        result = 31 * result + tags.hashCode()
        result = 31 * result + topics.hashCode()
        result = 31 * result + difficulty
        return result
    }
}

@Serializable
data class QuestionRestrictedDTO(
    @ContextualSerialization
    val id: Long,

    val title: String,
    val text: String,
    val help: String,
    val answers: Collection<AnswerRestrictedDTO>,
    val difficulty: Int
) : DTORestrictedSerializationMarker

@Serializable
data class QuestionPatchDTO(
    val title: String? = null,
    val text: String? = null,
    val help: String? = null,
    val answers: Set<Long>? = null,
    val correctAnswers: Set<Long>? = null,
    val tags: Set<Long>? = null,
    val topics: Set<Long>? = null,
    val difficulty: Int? = 0
) : DTOMarker
