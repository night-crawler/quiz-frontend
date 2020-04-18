package fm.force.ui.component

import com.ccfraser.muirwik.components.MTypographyAlign
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.mTypography
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.effect.useDebounce
import fm.force.ui.util.IconName
import fm.force.ui.util.runParallel
import kotlinext.js.jsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.RBuilder
import react.RComponent
import react.RProps
import react.RRef
import react.RSetState
import react.RState
import react.child
import react.functionalComponent
import react.rClass
import react.useEffect
import react.useState
import react.virtualized.auto.sizer.autoSizer
import react.window.VariableSizeList
import react.window.infinite.loader.OnItemsRendered
import react.window.infinite.loader.infiniteLoader
import react.window.variableSizeList
import kotlin.js.Promise
import kotlin.math.max


private val PaginatedQuestions = object {
    lateinit var notifyLoaded: RSetState<Boolean>
    lateinit var list: VariableSizeList

    val refHeightMap = mutableMapOf<Int, Int>()

    private val store = mutableMapOf<Int, Collection<QuestionFullDTO>>()
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

    fun loadMoreRows(
        query: String,
        sort: String,
        startIndex: Int,
        stopIndex: Int
    ): Promise<List<PageWrapper<QuestionFullDTO>>> {
        notifyLoaded(false)
        val startPage = startIndex / pageSize + 1
        val stopPage = stopIndex / pageSize + 1

        val realRange = (startPage..stopPage).toSet() - store.keys

        return GlobalScope.promise {
            realRange.runParallel {
                getPage(query, sort, it)
            }.also { notifyLoaded(true) }
        }
    }

    fun isItemLoaded(index: Int): Boolean {
        val page = index / pageSize + 1
        return page in store
    }

    fun getItem(index: Int): QuestionFullDTO? {
        val page = index / pageSize + 1
        val offset = index % pageSize
        val value = store[page]?.toList()?.get(offset)
        return value
    }

    fun getHeight(index: Int) = refHeightMap[index]
    fun setHeight(index: Int, height: Int) {
        refHeightMap[index] = height
    }

    val averageHeight = refHeightMap.values.sum() / refHeightMap.size
}


interface RowProps : RProps {
    var index: Int
    var style: dynamic
}

class QuestionRow(props: RowProps) : RComponent<RowProps, RState>(props) {
    var reference: dynamic = null

    private fun forceListRecalculateHeights() {
        if (reference != null && reference.clientHeight != null) {
            val oldHeight = PaginatedQuestions.getHeight(props.index)
            val newHeight: Int = reference.clientHeight as Int
            if (oldHeight != newHeight) {
                console.log("I AM UPDATING $oldHeight -> $newHeight")
                PaginatedQuestions.setHeight(props.index, newHeight)
                PaginatedQuestions.list.resetAfterIndex(props.index, true)
            }
        }
    }

    override fun componentDidMount() = forceListRecalculateHeights()

    override fun componentDidUpdate(prevProps: RowProps, prevState: RState, snapshot: Any) =
        forceListRecalculateHeights()

    override fun RBuilder.render() {
        val question = PaginatedQuestions.getItem(props.index)
        if (question == null) {
            mCard {
                mCardContent {
                    mTypography(align = MTypographyAlign.center) { +"Loading" }
                }
            }
            return
        }

        mCard {
            ref {
                reference = it
            }
            attrs {
                val style = jsObject<dynamic> {
                    position = props.style.position
                    left = props.style.left
                    top = props.style.top
                    // don't touch height
                    // height: 0
                    width = props.style.width
                }

                this.asDynamic().style = style
            }
            mCardHeader(
                title = "${question?.text}", subHeader = "September 14",
                avatar = mAvatar(addAsChild = false) { +"R" },
                action = mIconButton("more_vert", addAsChild = false)
            )
            mCardContent {

            }
            mCardActions {
                mIconButton(IconName.DELETE_OUTLINE.iconMame, onClick = { })
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
        ) { onItemsRendered: OnItemsRendered, ref: RRef ->
            variableSizeList(
                ref = ref,
                onItemsRendered = onItemsRendered,
                rowComponent = QuestionRow::class.rClass,
                height = size.height,
                width = size.width,
                itemCount = PaginatedQuestions.totalElements.toInt(),
                itemSize = {
                    PaginatedQuestions.getHeight(it) ?: max(PaginatedQuestions.averageHeight, 100)
                }
            ) {
                this.ref {
                    if (it != null)
                        PaginatedQuestions.list = it.unsafeCast<VariableSizeList>()
                }
            }
        }
    }
}

fun RBuilder.questionList() = child(QuestionList) {

}
