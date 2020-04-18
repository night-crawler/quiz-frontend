package fm.force.ui.component.question.list

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.util.runParallel
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

internal val PaginatedQuestions = object : AbstractPageContext<QuestionFullDTO>() {
    override suspend fun getPage(query: String, sort: String, page: Int): PageWrapper<QuestionFullDTO> {
        val notify = notifyLoaded ?: throw IllegalStateException("Set notifyLoaded")
        notify(false)
        val questionPage = ReduxStore.DEFAULT.client.findQuestions(
            page = page,
            query = query,
            pageSize = pageSize,
            sort = sort
        )
        totalElements = questionPage.totalElements
        totalPages = questionPage.totalPages
        store[questionPage.currentPage] = questionPage.content
        notify(true)
        return questionPage
    }

    override fun loadMoreRows(
        query: String,
        sort: String,
        startIndex: Int,
        stopIndex: Int
    ): Promise<List<PageWrapper<QuestionFullDTO>>> {
        val notify = notifyLoaded ?: throw IllegalStateException("Set notifyLoaded")

        notify(false)
        val startPage = startIndex / pageSize + 1
        val stopPage = stopIndex / pageSize + 1

        val realRange = (startPage..stopPage).toSet() - store.keys

        return GlobalScope.promise {
            realRange.runParallel {
                getPage(query, sort, it)
            }.also { notify(true) }
        }
    }
}
