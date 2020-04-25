package fm.force.ui.component.difficultyscale.list

import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.client.fromQueryString
import fm.force.ui.client.toQueryString
import fm.force.ui.component.helmet
import fm.force.ui.component.loadingCard
import fm.force.ui.component.noElements
import fm.force.ui.component.textSearchBox
import fm.force.ui.hook.UseState
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDispatch
import fm.force.ui.util.RouterContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title
import react.router.connected.push
import redux.RAction

val DifficultyScaleList = functionalComponent<RProps> { props ->
    val (difficultyScalePage, setDifficultyScalePage) = useState<PageWrapper<DifficultyScaleFullDTO>?>(null)
    val routerContext = useContext(RouterContext)
    val dispatch = useDispatch()

    var searchCriteria by UseState(DefaultSearchCriteria.fromQueryString(routerContext.location.search))

    useClient(listOf(searchCriteria.hashCode())) {
        findDifficultyScales(searchCriteria).apply(setDifficultyScalePage)
    }

    useEffect(listOf(searchCriteria.hashCode())) {
        dispatch(push("/difficulty-scales?${searchCriteria.toQueryString()}").unsafeCast<RAction>())
    }

    helmet { title("All Difficulty Scales") }

    textSearchBox(
        initialCriteria = searchCriteria,
        onSearchCriteriaChange = { searchCriteria = it.copy(page = 1) }
    )

    if (difficultyScalePage == null) {
        loadingCard()
        return@functionalComponent
    }

    if (difficultyScalePage.totalElements == 0L) {
        noElements()
        return@functionalComponent
    }

    difficultyScalePage.content.forEach { difficultyScale ->
        child(DifficultyScaleRow::class) {
            attrs {
                key = "difficultyScale:${difficultyScale.id}"
                this.difficultyScale = difficultyScale
                onDelete = {
                    GlobalScope.promise {
                        ReduxStore.DEFAULT.client.deleteDifficultyScale(it.id)
                        ReduxStore.DEFAULT.client.findDifficultyScales(searchCriteria).apply(setDifficultyScalePage)
                    }
                }
            }
        }
    }
    mTablePagination(
        rowsPerPage = difficultyScalePage.pageSize,
        page = searchCriteria.page - 1,
        count = difficultyScalePage.totalElements.toInt(),
        onChangePage = { _, page ->
            searchCriteria = searchCriteria.copy(page = page + 1)
        },
        onChangeRowsPerPage = { event ->
            searchCriteria = searchCriteria.copy(pageSize = event.targetValue.toString().toInt(), page = 1)
        }
    )
}

fun RBuilder.difficultyScaleList() = child(DifficultyScaleList) { }
