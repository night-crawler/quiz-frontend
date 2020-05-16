package fm.force.ui.component.tag.list

import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.TagFullDTO
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

val TagList = functionalComponent<RProps> {
    val (tagPage, setTagPage) = useState<PageWrapper<TagFullDTO>?>(null)
    val routerContext = useContext(RouterContext)
    val dispatch = useDispatch()

    var searchCriteria by UseState(DefaultSearchCriteria.fromQueryString(routerContext.location.search))

    useClient(listOf(searchCriteria.hashCode())) {
        findTags(searchCriteria).apply(setTagPage)
    }

    useEffect(listOf(searchCriteria.hashCode())) {
        dispatch(push("/tags?${searchCriteria.toQueryString()}"))
        setTagPage(null)
    }

    helmet { title("All tags") }

    textSearchBox(
        initialCriteria = searchCriteria,
        onSearchCriteriaChange = { searchCriteria = it.copy(page = 1) }
    )

    if (tagPage == null) {
        loadingCard()
        return@functionalComponent
    }

    if (tagPage.totalElements == 0L) {
        noElements()
        return@functionalComponent
    }

    mList {
        tagPage.content.forEach { tag ->
            child(TagRow::class) {
                attrs {
                    key = "tag:${tag.id}"
                    this.tag = tag
                    onDelete = {
                        callApi {
                            deleteTag(it.id)
                            findTags(searchCriteria).apply(setTagPage)
                        }
                    }
                }
            }
        }
    }
    mTablePagination(
        rowsPerPage = tagPage.pageSize,
        page = searchCriteria.page - 1,
        count = tagPage.totalElements.toInt(),
        onChangePage = { _, page ->
            searchCriteria = searchCriteria.copy(page = page + 1)
        },
        onChangeRowsPerPage = { event ->
            searchCriteria = searchCriteria.copy(pageSize = event.targetValue.toString().toInt(), page = 1)
        }
    )
}

fun RBuilder.tagList() = child(TagList) { }
