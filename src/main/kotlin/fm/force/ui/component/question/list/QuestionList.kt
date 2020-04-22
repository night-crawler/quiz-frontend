package fm.force.ui.component.question.list

import fm.force.ui.component.helmet
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import react.dom.title
import react.virtualized.auto.sizer.autoSizer
import react.window.VariableSizeList
import react.window.infinite.loader.OnItemsRendered
import react.window.infinite.loader.infiniteLoader
import react.window.variableSizeList

val QuestionList = functionalComponent<RProps> { props ->
    val (isLoaded, setIsLoaded) = useState(false)
    var clientHeight by UseState(0)
    var searchText by UseState("")
    val debouncedSearchText = useDebounce(searchText, 500)
    var placeHolderToForceRerender by UseState<dynamic>(null)

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            PaginatedQuestions.clear()
            // we pass this helper function down to the context, so it can call it after everything's loaded
            PaginatedQuestions.notifyLoaded = setIsLoaded
            PaginatedQuestions.getPage(debouncedSearchText, "-createdAt", 1)
        }
    }

    searchBox {
        attrs {
            onHeightChange = { clientHeight = it }
            onSearchTextChange = { searchText = it }
        }
    }

    helmet {
        title("All questions")
    }

    // we don't render anything until after we have the actual height of this input
    if (clientHeight == 0) {
        return@functionalComponent
    }

    autoSizer { size ->
        infiniteLoader(
            isItemLoaded = PaginatedQuestions::isItemLoaded,
            loadMoreItems = { startIndex: Int, stopIndex: Int ->
                PaginatedQuestions.loadMoreRows(debouncedSearchText, "text", startIndex, stopIndex)
            },
            itemCount = PaginatedQuestions.totalElements.toInt()
        ) { onItemsRendered: OnItemsRendered, ref: RRef ->
            variableSizeList(
                ref = ref,
                onItemsRendered = onItemsRendered,
                rowComponent = QuestionRow::class.rClass,
                height = size.height - clientHeight,
                width = size.width,
                itemCount = PaginatedQuestions.totalElements.toInt(),
                itemSize = PaginatedQuestions::getEffectiveHeight
            ) {
                this.ref {
                    if (it != null) {
                        PaginatedQuestions.infiniteListRef = it.unsafeCast<VariableSizeList>()
                        placeHolderToForceRerender = it
                    }
                }
            }
        }
    }
}

fun RBuilder.questionList() = child(QuestionList) { }
