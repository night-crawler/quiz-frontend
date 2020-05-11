package fm.force.ui.client

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.constant.DEFAULT_PAGE_SIZE
import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.parseQueryString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

interface SearchCriteria

@Serializable
data class QuestionSearchSlugs(
    val tags: String = "",
    val topics: String =""
) {
    companion object
}

@Serializable
data class DefaultSearchCriteria(
    val query: String = "",
    val sort: String = "-createdAt",
    val page: Int = 1,
    val pageSize: Int = DEFAULT_PAGE_SIZE
) : SearchCriteria {
    companion object
}

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

fun DefaultSearchCriteria.toMap() = mapOf(
    "page" to page,
    "query" to query,
    "sort" to sort,
    "pageSize" to pageSize
)

fun DefaultSearchCriteria.toQueryString() = QueryBuilder.of(
    toMap()
).toString()

fun DefaultSearchCriteria.Companion.fromQueryString(rawString: String): DefaultSearchCriteria {
//    val dyn = parseQueryString(rawString)
//    return DynamicObjectParser().parse(dyn)
    val s = JSON.stringify(parseQueryString(rawString))
    return QuizJson.jsonX.parse(serializer(), s)
}

fun QuestionSearchSlugs.Companion.fromQueryString(rawString: String): QuestionSearchSlugs {
    val s = JSON.stringify(parseQueryString(rawString))
    return QuizJson.jsonX.parse(serializer(), s)
}

fun QuestionSearchCriteria.Companion.fromQueryString(rawString: String, tags: List<TagFullDTO>): QuestionSearchCriteria {
    val s = JSON.stringify(parseQueryString(rawString))
    val defaults = QuizJson.jsonX.parse(DefaultSearchCriteria.serializer(), s)

    return QuestionSearchCriteria(
        query = defaults.query,
        pageSize = defaults.pageSize,
        page = defaults.page,
        sort = defaults.sort,
        tags = tags
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
