package fm.force.quiz.common.dto

import kotlinx.serialization.Serializable


@Serializable
data class ScoreRestrictedDTO(
    val tag: TagRestrictedDTO?,
    val topic: TopicRestrictedDTO?,
    var isCorrect: Boolean,
    var count: Long
) : DTORestrictedSerializationMarker


@Serializable
data class QuizSessionScoresDTO(
    val topicScores: Collection<ScoreRestrictedDTO>,
    val tagScores: Collection<ScoreRestrictedDTO>
) : DTORestrictedSerializationMarker
