package fm.force.ui.extension

import fm.force.quiz.common.dto.TagFullDTO

fun Collection<TagFullDTO>.guessLanguage(): Pair<CodeLanguage, Any> {
    val parts = map {
        it.name.toLowerCase().split("\\s".toRegex())
    }.flatten()

    return CodeLanguage.values()
        .map { lang -> lang to parts.count { lang.name.toLowerCase() == it } }
        .maxBy { (_, count) -> count }
        ?: CodeLanguage.GENERAL to 0
}
