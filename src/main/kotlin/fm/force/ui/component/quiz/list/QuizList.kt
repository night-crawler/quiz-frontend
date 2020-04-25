package fm.force.ui.component.quiz.list

import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.QuizFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.client.fromQueryString
import fm.force.ui.client.toQueryString
import fm.force.ui.component.helmet
import fm.force.ui.component.loadingCard
import fm.force.ui.component.noElements
import fm.force.ui.component.textSearchBox
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDispatch
import fm.force.ui.util.RouterContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title
import react.router.connected.push
import redux.RAction

val QuizList = functionalComponent<RProps> { props ->
    val (quizPage, setQuizPage) = useState<PageWrapper<QuizFullDTO>?>(null)
    val routerContext = useContext(RouterContext)
    val dispatch = useDispatch()

    var searchCriteria by UseState(DefaultSearchCriteria.fromQueryString(routerContext.location.search))

    useEffect(listOf(searchCriteria.hashCode())) {
        GlobalScope.promise {
            ReduxStore.DEFAULT.client.findQuizzes(searchCriteria).apply(setQuizPage)
        }
    }

    useEffect(listOf(searchCriteria.hashCode())) {
        dispatch(push("/quizzes?${searchCriteria.toQueryString()}").unsafeCast<RAction>())
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
                    GlobalScope.promise {
                        ReduxStore.DEFAULT.client.deleteQuiz(it.id)
                        ReduxStore.DEFAULT.client.findQuizzes(searchCriteria).apply(setQuizPage)
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
