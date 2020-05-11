package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.table.mTablePagination
import com.ccfraser.muirwik.components.targetValue
import fm.force.quiz.common.dto.QuestionFullDTO
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title
import react.router.connected.push

val QuestionList = functionalComponent<RProps> {
    val (questionPage, setQuestionPage) = useState<PageWrapper<QuestionFullDTO>?>(null)
    val dispatch = useDispatch()

    val initialSearchCriteria = useQuestionSearchCriteria()
    var searchCriteria by UseState(initialSearchCriteria)

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
