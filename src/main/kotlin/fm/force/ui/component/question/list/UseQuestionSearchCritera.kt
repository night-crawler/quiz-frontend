package fm.force.ui.component.question.list

import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.ui.client.*
import fm.force.ui.hook.useClient
import fm.force.ui.util.RouterContext
import react.useContext

abstract class ElementCache<T> {
    val cacheBySlug = mutableMapOf<String, T>()
    fun getBySlugs(slugs: Collection<String>): List<T> =
        slugs.map { cacheBySlug[it]!! }
    fun getUnknown(slugs: Collection<String>): Set<String> =
        slugs.toSet() - cacheBySlug.keys

    abstract fun addToCache(tags: Collection<T>)
}

val TagCache = object : ElementCache<TagFullDTO>() {
    override fun addToCache(tags: Collection<TagFullDTO>) =
        tags.forEach { cacheBySlug[it.slug] = it }
}
val TopicCache = object : ElementCache<TopicFullDTO>() {
    override fun addToCache(tags: Collection<TopicFullDTO>) =
        tags.forEach { cacheBySlug[it.slug] = it }
}

fun useQuestionSearchCriteria(): QuestionSearchCriteria? {
    val routerContext = useContext(RouterContext)
    val queryString = routerContext.location.search

    val searchSlugs = QuestionSearchSlugs.fromQueryString(queryString)

    val tags = useClient {
        val unknownTags =
            TagCache.getUnknown(searchSlugs.cleanTags)
        if (unknownTags.isNotEmpty())
            findTags(slugs = unknownTags).content.let {
                TagCache.addToCache(it)
            }
        TagCache.getBySlugs(searchSlugs.cleanTags)
    }

    val topics = useClient {
        val unknownTopics =
            TopicCache.getUnknown(searchSlugs.cleanTopics)
        if (unknownTopics.isNotEmpty())
            findTopics(slugs = unknownTopics).content.let {
                TopicCache.addToCache(it)
            }
        TopicCache.getBySlugs(searchSlugs.cleanTopics)
    }

    if (tags == null || topics == null) {
        return null
    }

    return QuestionSearchCriteria.fromQueryString(queryString, tags, topics)
}
