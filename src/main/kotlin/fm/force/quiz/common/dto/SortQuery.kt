package fm.force.quiz.common.dto

data class SortQuery(
    val sort: Collection<String>?
) {
    companion object {
        fun byIdDesc() = SortQuery(sort = listOf("-id"))
    }
}
