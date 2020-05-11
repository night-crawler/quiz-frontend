package fm.force.ui.client

import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.util.parseQueryString
import kotlinx.serialization.Serializable

@Serializable
data class QuestionSearchSlugs(
    val tags: String = "",
    val topics: String =""
) {
    companion object
}

fun QuestionSearchSlugs.Companion.fromQueryString(rawString: String): QuestionSearchSlugs {
    val s = JSON.stringify(parseQueryString(rawString))
    return QuizJson.jsonX.parse(serializer(), s)
}

val QuestionSearchSlugs.cleanTags get() =
    tags.split(",").map { it.trim() }.filterNot { it.isEmpty() }

val QuestionSearchSlugs.cleanTopics get() =
    topics.split(",").map { it.trim() }.filterNot { it.isEmpty() }
