package fm.force.quiz.common.dto

import kotlinx.serialization.Serializable

@Serializable
data class PageDTO(
    val sort: SortDTO,
    val numberOfElements: Int,
    val totalElements: Long,
    val totalPages: Int,
    val pageSize: Int,
    val isLast: Boolean,
    val isFirst: Boolean,
    val content: Collection<DTOMarker>
)
