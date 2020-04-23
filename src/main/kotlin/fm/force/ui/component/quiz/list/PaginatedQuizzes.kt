package fm.force.ui.component.quiz.list

import fm.force.quiz.common.dto.QuizFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.client.dto.PageWrapper
import fm.force.ui.component.AbstractPageContext

object PaginatedQuizzes : AbstractPageContext<QuizFullDTO>() {
    override suspend fun getPage(query: String, sort: String, page: Int): PageWrapper<QuizFullDTO> {
//        val notify = notifyLoaded ?: throw IllegalStateException("Set notifyLoaded")
//        notify(false)
        val quizPage = ReduxStore.DEFAULT.client.findQuizzes(
            page = page,
            query = query,
            pageSize = pageSize,
            sort = sort
        )
        totalElements = quizPage.totalElements
        totalPages = quizPage.totalPages
        store[quizPage.currentPage] = quizPage.content
//        notify(true)
        return quizPage
    }
}
