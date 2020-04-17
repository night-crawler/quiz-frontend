package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.mTypography
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.effect.useDebounce
import fm.force.ui.util.IconName
import fm.force.ui.util.runParallel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RSetState
import react.RState
import react.child
import react.functionalComponent
import react.rClass
import react.useEffect
import react.useState
import react.virtualized.auto.sizer.autoSizer
import react.window.fixedSizeList
import react.window.infinite.loader.OnItemsRendered
import react.window.infinite.loader.infiniteLoader
import styled.styledDiv
import kotlin.js.Promise
import kotlin.reflect.KProperty


private val PaginatedQuestions = object {
    lateinit var notifyLoaded: RSetState<Boolean>

    private val store = linkedMapOf<Int, Collection<QuestionFullDTO>>()
    var totalPages: Int = 0
    var totalElements: Long = 0
    var pageSize: Int = 25

    suspend fun getPage(query: String, sort: String, page: Int): PageWrapper<QuestionFullDTO> {
        notifyLoaded(false)
        val questionPage = ReduxStore.DEFAULT.client.findQuestions(
            page = page,
            query = query,
            pageSize = pageSize,
            sort = sort
        )
        totalElements = questionPage.totalElements
        totalPages = questionPage.totalPages
        store[questionPage.currentPage] = questionPage.content
        notifyLoaded(true)
        return questionPage
    }

    fun loadMoreRows(query: String, sort: String, startIndex: Int, stopIndex: Int): Promise<List<PageWrapper<QuestionFullDTO>>> {
        notifyLoaded(false)
        val startPage = startIndex / pageSize + 1
        val stopPage = stopIndex / pageSize + 1

        val realRange = (startPage..stopPage).toSet() - store.keys
        console.log("loadMoreRows $query - $sort - $startIndex - $stopIndex :: realRange: $realRange")

        return GlobalScope.promise {
            realRange.runParallel {
                getPage(query, sort, it)
            }.also { notifyLoaded(true) }
        }
    }

    fun isItemLoaded(index: Int): Boolean {
        val page = index / pageSize + 1
        val isLoaded = page in store
        if (!isLoaded) {
            console.log("CHECK $index PAGE $page ${store.keys}")
        }
        return isLoaded
    }

    fun getItem(index: Int): QuestionFullDTO? {
        val page = index / pageSize + 1
        val offset = index % pageSize
        val value = store[page]?.toList()?.get(offset)
        return value
    }
}


interface RowProps : RProps {
    var index: Int
    var style: dynamic
}

class QuestionRow(props: RowProps) : RComponent<RowProps, RState>(props) {
    override fun RBuilder.render() {
        val question = PaginatedQuestions.getItem(props.index)

        mCard {
            attrs {
                this.asDynamic().style = props.style
            }
            mCardHeader(title = "${question?.text}", subHeader = "September 14",
                avatar = mAvatar(addAsChild = false) {+"R"},
                action = mIconButton("more_vert", addAsChild = false)
            )
            mCardContent {
                mList {

                }
            }
            mCardActions {
                mIconButton(IconName.DELETE_OUTLINE.iconMame, onClick = {  })
            }
        }
    }
}

val QuestionList = functionalComponent<RProps> { props ->
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
        ) { onItemsRendered: OnItemsRendered, ref ->
            fixedSizeList(
                ref = ref,
                onItemsRendered = onItemsRendered,
                rowComponent = QuestionRow::class.rClass,
                height = size.height,
                width = size.width,
                itemCount = PaginatedQuestions.totalElements.toInt(),
                itemSize = 400
            ) {
                attrs {
                    itemSize = 100
                }
            }
        }
    }
}

class UseState <T>(initialValue: T) {
    private val valuePair = useState(initialValue)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = valuePair.first
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = valuePair.second(value)
}

fun RBuilder.questionList() = child(QuestionList) {

}
