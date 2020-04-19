package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.form.MFormControlMargin
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.targetInputValue
import fm.force.ui.component.helmet
import fm.force.ui.effect.UseState
import fm.force.ui.effect.useDebounce
import kotlin.math.max
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.css.padding
import react.*
import react.dom.title
import react.virtualized.auto.sizer.autoSizer
import react.window.VariableSizeList
import react.window.infinite.loader.OnItemsRendered
import react.window.infinite.loader.infiniteLoader
import react.window.variableSizeList
import styled.css

val QuestionList = functionalComponent<RProps> { props ->
    val (isLoaded, setIsLoaded) = useState(false)
    var clientHeight by UseState(0)
    var searchText by UseState("")
    val debouncedSearchText = useDebounce(searchText, 500)

    useEffect(listOf(debouncedSearchText)) {
        GlobalScope.promise {
            PaginatedQuestions.clear()
            // we pass this helper function down to the context, so it can call it after everything's loaded
            PaginatedQuestions.notifyLoaded = setIsLoaded
            PaginatedQuestions.getPage(debouncedSearchText, "text", 1)
        }
    }

    helmet {
        title("All questions")
    }

    mTextField("Search", fullWidth = true, margin = MFormControlMargin.none) {
        ref { if (it != null) clientHeight = it.clientHeight }
//        css { padding = "0px" }
        attrs {
            onChange = { searchText = it.targetInputValue }
        }
    }

    // we don't render anything until after we have the actual height of this input
    if (clientHeight == 0) {
        return@functionalComponent
    }

    if (!isLoaded) {
        loadingCard()
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
                itemSize = {
                    (PaginatedQuestions.getHeight(it) ?: max(
                        PaginatedQuestions.averageHeight, 100
                    )) + 5
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
