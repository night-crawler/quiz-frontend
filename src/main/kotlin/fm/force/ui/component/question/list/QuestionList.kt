package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.*
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.component.main.helmet
import fm.force.ui.component.main.loadingCard
import fm.force.ui.component.main.noElements
import fm.force.ui.container.questionRow
import fm.force.ui.hook.UseState
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDispatch
import fm.force.ui.util.RouterContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title
import react.router.connected.push

interface ITagCache {
    fun getTagsBySlugs(slugs: Collection<String>): List<TagFullDTO>
    fun getUnknownTags(slugs: Collection<String>) : Set<String>
    fun cacheTags(tags: Collection<TagFullDTO>)
}

val TagCache = object : ITagCache {
    val cacheBySlug = mutableMapOf<String, TagFullDTO>()
    override fun getTagsBySlugs(slugs: Collection<String>): List<TagFullDTO> = slugs.map { cacheBySlug[it]!! }
    override fun getUnknownTags(slugs: Collection<String>): Set<String> = slugs.toSet() - cacheBySlug.keys
    override fun cacheTags(tags: Collection<TagFullDTO>) = tags.forEach { cacheBySlug[it.slug] = it }
}


fun useQuestionSearchCriteria(): QuestionSearchCriteria? {
    val routerContext = useContext(RouterContext)
    val queryString = routerContext.location.search

    val tagsAndTopics = QuestionSearchSlugs.fromQueryString(queryString)
    val tagSlugs = tagsAndTopics.tags.split(",").map { it.trim() }.filterNot { it.isEmpty() }

    val tags = useClient {
        val unknownTags = TagCache.getUnknownTags(tagSlugs)
        if (unknownTags.isNotEmpty())
            findTags(slugs = unknownTags).content.let {
                TagCache.cacheTags(it)
            }
        TagCache.getTagsBySlugs(tagSlugs)
    } ?: return null

    return QuestionSearchCriteria.fromQueryString(queryString, tags)
}

val QuestionList = functionalComponent<RProps> {
    val (questionPage, setQuestionPage) = useState<PageWrapper<QuestionFullDTO>?>(null)
    val dispatch = useDispatch()

    val initialSearchCriteria = useQuestionSearchCriteria()
    var searchCriteria by UseState(initialSearchCriteria)

    console.log("RENDER?", searchCriteria.hashCode(), initialSearchCriteria.hashCode())

    useClient(listOf(searchCriteria.hashCode(), initialSearchCriteria.hashCode())) {
        if (searchCriteria != null) {
            findQuestions(searchCriteria!!).apply(setQuestionPage)
        }
    }

    useEffect(listOf(searchCriteria.hashCode(), initialSearchCriteria.hashCode())) {
        if (searchCriteria == null && initialSearchCriteria != null) {
            searchCriteria = initialSearchCriteria
        }
        if (searchCriteria != null)
            dispatch(push("/questions?${searchCriteria!!.toQueryString()}"))
    }

    helmet { title("All questions") }

    if (questionPage == null || searchCriteria == null) {
        loadingCard()
        return@functionalComponent
    }

    questionTextSearchBox(
        initialCriteria = searchCriteria!!,
        onSearchCriteriaChange = { searchCriteria = it.copy(page = 1) }
    )

    if (questionPage.totalElements == 0L) {
        noElements()
        return@functionalComponent
    }

    questionPage.content.forEach { question ->
        questionRow {
            attrs {
                key = "question:${question.id}"
                this.question = question
                onDelete = {
                    GlobalScope.promise {
                        ReduxStore.DEFAULT.client.deleteQuestion(it.id)
                        ReduxStore.DEFAULT.client.findQuestions(searchCriteria!!).apply(setQuestionPage)
                    }
                }
            }
        }
    }
    mTablePagination(
        rowsPerPage = questionPage.pageSize,
        page = searchCriteria!!.page - 1,
        count = questionPage.totalElements.toInt(),
        onChangePage = { _, page ->
            searchCriteria = searchCriteria!!.copy(page = page + 1)
        },
        onChangeRowsPerPage = { event ->
            searchCriteria = searchCriteria!!.copy(pageSize = event.targetValue.toString().toInt(), page = 1)
        }
    )
}

fun RBuilder.questionList() = child(QuestionList) { }
