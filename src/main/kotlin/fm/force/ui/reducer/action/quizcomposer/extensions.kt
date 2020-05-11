package fm.force.ui.reducer.action.quizcomposer

import fm.force.ui.reducer.QuizComposer

fun QuizComposer.Companion.of() =
    QuizComposer(
        id = 0,
        questions = listOf(),
        tags = listOf(),
        topics = listOf(),
        title = "",
        difficultyScale = null
    )
