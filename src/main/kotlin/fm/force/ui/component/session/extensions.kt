package fm.force.ui.component.session

import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.ui.client.compareTo
import kotlin.js.Date

val QuizSessionFullDTO.isExpired get() = validTill < Date()
val QuizSessionFullDTO.isAnswerable get() = !isCancelled && !isCompleted && !isExpired
