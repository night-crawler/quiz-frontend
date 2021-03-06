package fm.force.ui.component.quiz.list

import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.QuizFullDTO
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

val QuizList = functionalComponent<RProps> {
    val (quizPage, setQuizPage) = useState<PageWrapper<QuizFullDTO>?>(null)
    val routerContext = useContext(RouterContext)
    val dispatch = useDispatch()

    var searchCriteria by UseState(DefaultSearchCriteria.fromQueryString(routerContext.location.search))

    useClient(listOf(searchCriteria.hashCode())) {
        findQuizzes(searchCriteria).apply(setQuizPage)
    }

    useEffect(listOf(searchCriteria.hashCode())) {
        dispatch(push("/quizzes?${searchCriteria.toQueryString()}"))
    }

    helmet { title("All quizzes") }

    textSearchBox(
        initialCriteria = searchCriteria,
        onSearchCriteriaChange = { searchCriteria = it.copy(page = 1) }
    )

    if (quizPage == null) {
        loadingCard()
        return@functionalComponent
    }

    if (quizPage.totalElements == 0L) {
        noElements()
        return@functionalComponent
    }

    quizPage.content.forEach { quiz ->
        child(QuizRow::class) {
            attrs {
                key = "quiz:${quiz.id}"
                this.quiz = quiz
                onDelete = {
                    callApi {
                        deleteQuiz(it.id)
                        findQuizzes(searchCriteria).apply(setQuizPage)
                    }
                }
            }
        }
    }
    mTablePagination(
        rowsPerPage = quizPage.pageSize,
        page = searchCriteria.page - 1,
        count = quizPage.totalElements.toInt(),
        onChangePage = { _, page ->
            searchCriteria = searchCriteria.copy(page = page + 1)
        },
        onChangeRowsPerPage = { event ->
            searchCriteria = searchCriteria.copy(pageSize = event.targetValue.toString().toInt(), page = 1)
        }
    )
}

fun RBuilder.quizList() = child(QuizList) { }
