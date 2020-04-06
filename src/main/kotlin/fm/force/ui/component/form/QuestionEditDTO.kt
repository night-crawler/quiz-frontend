package fm.force.ui.component.form

data class TagDTO(
    val id: Long,
    val name: String
)

data class QuestionEditDTO(
    val tags: List<TagDTO>,
    val name: String,
    val answers: List<Answer>
)
