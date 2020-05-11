package fm.force.ui.client

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.constant.DEFAULT_PAGE_SIZE
import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.parseQueryString
import kotlinx.serialization.Serializable

@Serializable
data class QuestionSearchCriteria(
    val query: String = "",
    val sort: String = "-createdAt",
    val page: Int = 1,
    val pageSize: Int = DEFAULT_PAGE_SIZE,
    val tags: List<TagFullDTO> = listOf(),
    val topics: List<TopicFullDTO> = listOf()
) : SearchCriteria {
    companion object
}

fun QuestionSearchCriteria.Companion.fromQueryString(
    rawString: String,
    tags: List<TagFullDTO>,
    topics: List<TopicFullDTO>
): QuestionSearchCriteria {
    val s = JSON.stringify(parseQueryString(rawString))
    val defaults = QuizJson.jsonX.parse(DefaultSearchCriteria.serializer(), s)

    return QuestionSearchCriteria(
        query = defaults.query,
        pageSize = defaults.pageSize,
        page = defaults.page,
        sort = defaults.sort,
        tags = tags,
        topics = topics
    )
}

fun QuestionSearchCriteria.toMap() = mapOf(
    "page" to page,
    "query" to query,
    "sort" to sort,
    "pageSize" to pageSize,
    "topics" to topics.joinToString(",") { it.slug },
    "tags" to tags.joinToString(",") { it.slug }
)

fun QuestionSearchCriteria.toQueryString() = QueryBuilder.of(
    toMap()
).toString()
