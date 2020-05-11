package fm.force.ui.client

import fm.force.quiz.common.serializer.QuizJson
import fm.force.ui.constant.DEFAULT_PAGE_SIZE
import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.parseQueryString
import kotlinx.serialization.Serializable

@Serializable
data class DefaultSearchCriteria(
    val query: String = "",
    val sort: String = "-createdAt",
    val page: Int = 1,
    val pageSize: Int = DEFAULT_PAGE_SIZE
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
