package fm.force.ui.extension

import fm.force.quiz.common.dto.QuestionFullDTO

fun QuestionFullDTO.guessLanguage(): Pair<CodeLanguage, Int> {
    val texts = (
        tags.map { it.name.toLowerCase().split("\\s".toRegex()) } +
            topics.map { it.title.toLowerCase().split("\\s".toRegex()) }
        )
        .flatten().toSet()

    return CodeLanguage.values()
        .map { lang -> lang to texts.count { lang.name.toLowerCase() == it } }
        .maxBy { (_, count) -> count }
        ?: CodeLanguage.GENERAL to 0
}
