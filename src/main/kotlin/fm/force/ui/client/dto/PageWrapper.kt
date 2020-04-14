package fm.force.ui.client.dto

import fm.force.quiz.common.dto.DTOMarker
import fm.force.quiz.common.dto.PageDTO
import fm.force.quiz.common.dto.SortDTO

data class PageWrapper<T : DTOMarker>(
    val sort: SortDTO,
    val numberOfElements: Int,
    val totalElements: Long,
    val totalPages: Int,
    val pageSize: Int,
    val isLast: Boolean,
    val isFirst: Boolean,
    val content: Collection<T>
) {
    companion object
}

fun <T : DTOMarker> PageDTO.toTypedPage() = PageWrapper(
    sort = sort,
    numberOfElements = numberOfElements,
    totalElements = totalElements,
    totalPages = totalPages,
    pageSize = pageSize,
    isLast = isLast,
    isFirst = isFirst,
    content = content.unsafeCast<List<T>>()
)
