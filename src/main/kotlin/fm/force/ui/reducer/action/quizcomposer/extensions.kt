package fm.force.ui.reducer.action.quizcomposer

import fm.force.quiz.common.dto.QuizPatchDTO
import fm.force.ui.dto.QuizComposerDTO

fun QuizComposerDTO.toPatchDTO() =
    QuizPatchDTO(
        title = title,
        questions = questions.map { it.id },
        topics = topics.map { it.id }.toSet(),
        tags = tags.map { it.id }.toSet(),
        difficultyScale = difficultyScale?.id
    )
