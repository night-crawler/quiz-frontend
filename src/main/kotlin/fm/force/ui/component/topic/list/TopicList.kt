package fm.force.ui.component.topic.list

import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.client.fromQueryString
import fm.force.ui.client.toQueryString
import fm.force.ui.component.main.helmet
import fm.force.ui.component.main.loadingCard
import fm.force.ui.component.main.noElements
import fm.force.ui.component.main.textSearchBox
import fm.force.ui.hook.UseState
import fm.force.ui.hook.callApi
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDispatch
import fm.force.ui.util.RouterContext
import react.*
import react.dom.title
import react.router.connected.push

val TopicList = functionalComponent<RProps> {
    val (topicPage, setTopicPage) = useState<PageWrapper<TopicFullDTO>?>(null)
    val routerContext = useContext(RouterContext)
    val dispatch = useDispatch()

    var searchCriteria by UseState(DefaultSearchCriteria.fromQueryString(routerContext.location.search))

    useClient(listOf(searchCriteria.hashCode())) {
        findTopics(searchCriteria).apply(setTopicPage)
    }

    useEffect(listOf(searchCriteria.hashCode())) {
        dispatch(push("/topics?${searchCriteria.toQueryString()}"))
        setTopicPage(null)
    }

    helmet { title("All topics") }

    textSearchBox(
        initialCriteria = searchCriteria,
        onSearchCriteriaChange = { searchCriteria = it.copy(page = 1) }
    )

    if (topicPage == null) {
        loadingCard()
        return@functionalComponent
    }

    if (topicPage.totalElements == 0L) {
        noElements()
        return@functionalComponent
    }

    mList {
        topicPage.content.forEach { topic ->
            child(TopicRow::class) {
                attrs {
                    key = "topic:${topic.id}"
                    this.topic = topic
                    onDelete = {
                        callApi {
                            deleteTopic(it.id)
                            findTopics(searchCriteria).apply(setTopicPage)
                        }
                    }
                }
            }
        }
    }
    mTablePagination(
        rowsPerPage = topicPage.pageSize,
        page = searchCriteria.page - 1,
        count = topicPage.totalElements.toInt(),
        onChangePage = { _, page ->
            searchCriteria = searchCriteria.copy(page = page + 1)
        },
        onChangeRowsPerPage = { event ->
            searchCriteria = searchCriteria.copy(pageSize = event.targetValue.toString().toInt(), page = 1)
        }
    )
}

fun RBuilder.topicList() = child(TopicList) { }
