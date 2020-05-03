package fm.force.ui.component.session

import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.client.fromQueryString
import fm.force.ui.client.toQueryString
import fm.force.ui.component.main.helmet
import fm.force.ui.component.main.loadingCard
import fm.force.ui.component.main.noElements
import fm.force.ui.component.main.textSearchBox
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

val QuizSessionList = functionalComponent<RProps> { props ->
    val (sessionPage, setSessionPage) = useState<PageWrapper<QuizSessionFullDTO>?>(null)
    val routerContext = useContext(RouterContext)
    val dispatch = useDispatch()

    var searchCriteria by UseState(DefaultSearchCriteria.fromQueryString(routerContext.location.search))

    useClient(listOf(searchCriteria.hashCode())) {
        findSessions(searchCriteria).apply(setSessionPage)
    }

    useEffect(listOf(searchCriteria.hashCode())) {
        dispatch(push("/sessions?${searchCriteria.toQueryString()}").unsafeCast<RAction>())
    }

    helmet { title("All sessions") }

    textSearchBox(
        initialCriteria = searchCriteria,
        onSearchCriteriaChange = { searchCriteria = it.copy(page = 1) }
    )

    if (sessionPage == null) {
        loadingCard()
        return@functionalComponent
    }

    if (sessionPage.totalElements == 0L) {
        noElements()
        return@functionalComponent
    }

    sessionPage.content.forEach { session ->
        child(QuizSessionRow::class) {
            attrs {
                key = "session:${session.id}"
                this.session = session
                onContinue = { dispatch(push("/sessions/${session.id}/test").unsafeCast<RAction>()) }
                onShowReport = { dispatch(push("/sessions/${session.id}/report").unsafeCast<RAction>()) }
                onCancel = {
                    GlobalScope.promise {
                        ReduxStore.DEFAULT.client.cancelSession(session.id)
                        ReduxStore.DEFAULT.client.findSessions(searchCriteria).apply(setSessionPage)
                    }
                }
                onComplete = {
                    GlobalScope.promise {
                        ReduxStore.DEFAULT.client.completeSession(session.id)
                        ReduxStore.DEFAULT.client.findSessions(searchCriteria).apply(setSessionPage)
                    }
                }
            }
        }
    }
    mTablePagination(
        rowsPerPage = sessionPage.pageSize,
        page = searchCriteria.page - 1,
        count = sessionPage.totalElements.toInt(),
        onChangePage = { _, page ->
            searchCriteria = searchCriteria.copy(page = page + 1)
        },
        onChangeRowsPerPage = { event ->
            searchCriteria = searchCriteria.copy(pageSize = event.targetValue.toString().toInt(), page = 1)
        }
    )
}

fun RBuilder.quizSessionList() = child(QuizSessionList) { }
