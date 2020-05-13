package fm.force.ui.reducer

import fm.force.ui.dto.QuizComposerDTO
import fm.force.ui.dto.of
import fm.force.ui.reducer.action.quizcomposer.*
import redux.RAction

fun quizComposerReducer(state: QuizComposerDTO = QuizComposerDTO.of(), action: RAction): QuizComposerDTO =
    when (action) {
        is SelectedQuestionsSentToComposer -> state.copy(questions = (state.questions + action.questions).distinct())
        is QuizComposerBootstrapSuccess -> with(action.quiz) {
            state.copy(
                id = id,
                questions = (state.questions + quizQuestions.map { it.question }).distinct(),
                topics = topics.toList(),
                tags = tags.toList(),
                title = title,
                difficultyScale = difficultyScale
            )
        }
        is QuizComposerSetTags -> state.copy(tags = action.tags)
        is QuizComposerSetTopics -> state.copy(topics = action.topics)
        is QuizComposerSetTitle -> state.copy(title = action.title)
        is QuizComposerSetDifficultyScale -> state.copy(difficultyScale = action.scale)
        is QuizComposerDeleteQuestion -> state.copy(questions = state.questions - action.question)
        is QuizComposerClearQuestions -> state.copy(questions = listOf())
        is QuizComposerClear -> QuizComposerDTO.of()
        else -> state
    }
