package fm.force.ui.component.question.list

import com.benasher44.uuid.uuid4
import fm.force.ui.component.helmet
import fm.force.ui.component.infinitePaginator
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import fm.force.ui.effect.useForceUpdate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title

val QuestionList = functionalComponent<RProps> { props ->
    var searchBoxHeight by UseState(0)
    var searchText by UseState("")
    val debouncedSearchText = useDebounce(searchText, 500)
    val forceUpdate = useForceUpdate()
    var uniqueKey by UseState(uuid4().toString())

    val sort = "-createdAt"

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            PaginatedQuestions.clear()
            // we pass this helper function down to the context, so it can call it after everything's loaded
//            PaginatedQuestions.notifyLoaded = setIsLoaded
            PaginatedQuestions.forceUpdate = forceUpdate
            PaginatedQuestions.getPage(debouncedSearchText, sort, 1)
            forceUpdate()
        }
    }

    questionSearchBox {
        attrs {
            onHeightChange = { searchBoxHeight = it }
            onSearchTextChange = { searchText = it }
        }
    }

    helmet {
        title("All questions")
    }

    // we don't render anything until after we have the actual height of this input
    if (searchBoxHeight == 0) {
        return@functionalComponent
    }
    infinitePaginator(
        rowComponent = QuestionRow::class.rClass,
        paginator = PaginatedQuestions,
        offsetTop = searchBoxHeight,
        searchText = debouncedSearchText,
        sort = sort,
        forceUpdate = forceUpdate,
        uniqueKey = uniqueKey
    )
}

fun RBuilder.questionList() = child(QuestionList) { }
