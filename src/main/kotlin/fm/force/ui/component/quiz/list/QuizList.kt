package fm.force.ui.component.quiz.list

import fm.force.ui.component.helmet
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import fm.force.ui.effect.useForceUpdate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title

val QuizList = functionalComponent<RProps> { props ->
    var searchBoxHeight by UseState(0)
    var searchText by UseState("")
    val debouncedSearchText = useDebounce(searchText, 500)
    val forceUpdate = useForceUpdate()

    val sort = "-createdAt"

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            PaginatedQuizzes.clear()
            // we pass this helper function down to the context, so it can call it after everything's loaded
//            PaginatedQuizzes.notifyLoaded = setIsLoaded
            PaginatedQuizzes.getPage(debouncedSearchText, sort, 1)
        }
    }

//    quizSearchBox {
//        attrs {
//            onHeightChange = { searchBoxHeight = it }
//            onSearchTextChange = { searchText = it }
//        }
//    }

    helmet {
        title("All quizs")
    }

    // we don't render anything until after we have the actual height of this input
    if (searchBoxHeight == 0) {
        return@functionalComponent
    }
//    infinitePaginator(
//        rowComponent = QuizRow::class.rClass,
//        paginator = PaginatedQuizzes,
//        offsetTop = searchBoxHeight,
//        searchText = debouncedSearchText,
//        sort = sort,
//        forceUpdate = forceUpdate
//    )
}

fun RBuilder.quizList() = child(QuizList) { }

