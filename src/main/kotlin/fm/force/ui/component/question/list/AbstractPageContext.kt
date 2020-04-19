package fm.force.ui.component.question.list

import fm.force.quiz.common.dto.DTOMarker
import fm.force.ui.client.dto.PageWrapper
import kotlin.js.Promise
import react.RSetState
import react.window.VariableSizeList
import kotlin.math.max

abstract class AbstractPageContext<T : DTOMarker> {
    internal val store = mutableMapOf<Int, Collection<T>>()

    var totalPages: Int = 0
    var totalElements: Long = 0
    var pageSize: Int = 25

    var notifyLoaded: RSetState<Boolean>? = null
    var infiniteListRef: VariableSizeList? = null

    private val refHeightMap = mutableMapOf<Int, Int>()

    val averageHeight = refHeightMap.values.sum() / refHeightMap.size

    abstract suspend fun getPage(query: String, sort: String, page: Int): PageWrapper<T>

    val isInitialized get() = notifyLoaded != null && infiniteListRef != null

    fun clear() {
        store.clear()
        refHeightMap.clear()
        infiniteListRef = null
        notifyLoaded = null
    }

    abstract fun loadMoreRows(
        query: String,
        sort: String,
        startIndex: Int,
        stopIndex: Int
    ): Promise<List<PageWrapper<T>>>

    fun isItemLoaded(index: Int): Boolean {
        val page = index / pageSize + 1
        return page in store
    }

    fun getItemPage(index: Int) = index / pageSize + 1

    fun deleteItem(index: Int) {
        val page = getItemPage(index)
//        (page..totalPages).forEach { store.remove(it) }
//        infiniteListRef?.resetAfterIndex(max(0, (page - 1) * pageSize), true)
        store.clear()
        infiniteListRef?.resetAfterIndex(0, true)
        infiniteListRef?.scrollToItem(0)
    }

    fun getItem(index: Int): T? {
        val offset = index % pageSize
        return store[getItemPage(index)]?.toList()?.get(offset)
    }

    fun getHeight(index: Int) = refHeightMap[index]
    fun setHeight(index: Int, height: Int) {
        refHeightMap[index] = height
    }
}
