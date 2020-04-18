package fm.force.ui.component.question.list

import fm.force.quiz.common.dto.DTOMarker
import fm.force.ui.client.dto.PageWrapper
import kotlin.js.Promise
import react.RSetState
import react.window.VariableSizeList

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

    fun getItem(index: Int): T? {
        val page = index / pageSize + 1
        val offset = index % pageSize
        val value = store[page]?.toList()?.get(offset)
        return value
    }

    fun getHeight(index: Int) = refHeightMap[index]
    fun setHeight(index: Int, height: Int) {
        refHeightMap[index] = height
    }
}
