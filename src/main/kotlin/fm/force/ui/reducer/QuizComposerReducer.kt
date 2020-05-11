package fm.force.ui.reducer

import fm.force.quiz.common.dto.*
import fm.force.ui.reducer.action.quizcomposer.of
import redux.RAction

class QuizComposerBootstrapSuccess(val quiz: QuizFullDTO) : RAction
class QuizComposerTagsChanged(val tags: List<TagFullDTO>) : RAction
class QuizComposerTopicsChanged(val topics: List<TopicFullDTO>) : RAction
class QuizComposerDifficultyScaleChanged(val scale: DifficultyScaleFullDTO) : RAction
class QuizComposerTitleChanged(val title: String) : RAction

fun QuizComposer.toPatchDTO() = QuizPatchDTO(
    title = title,
    questions = questions.map { it.id },
    topics = topics.map { it.id }.toSet(),
    tags = tags.map { it.id }.toSet(),
    difficultyScale = difficultyScale?.id
)

fun quizComposerReducer(state: QuizComposer = QuizComposer.of(), action: RAction): QuizComposer = when (action) {
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
    is QuizComposerTagsChanged -> state.copy(tags = action.tags)
    is QuizComposerTopicsChanged -> state.copy(topics = action.topics)
    is QuizComposerTitleChanged -> state.copy(title = action.title)
    is QuizComposerDifficultyScaleChanged -> state.copy(difficultyScale = action.scale)
    else -> state
}
