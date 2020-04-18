package fm.force.ui.component.question.list

import fm.force.ui.component.helmet
import fm.force.ui.effect.useDebounce
import kotlin.math.max
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
    helmet {
        title("All questions")
    }

    val (isLoaded, setIsLoaded) = useState(false)
    val debouncedSearchText = useDebounce("", 500)
    PaginatedQuestions.notifyLoaded = setIsLoaded

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            PaginatedQuestions.getPage(debouncedSearchText, "text", 1)
        }
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
                height = size.height,
                width = size.width,
                itemCount = PaginatedQuestions.totalElements.toInt(),
                itemSize = {
                    PaginatedQuestions.getHeight(it) ?: max(
                        PaginatedQuestions.averageHeight, 100
                    )
                }
            ) {
                this.ref {
                    if (it != null)
                        PaginatedQuestions.infiniteListRef = it.unsafeCast<VariableSizeList>()
                }
            }
        }
    }
}

fun RBuilder.questionList() = child(QuestionList) { }
