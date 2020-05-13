package fm.force.ui.reducer.action.session

import fm.force.quiz.common.dto.*
import redux.RAction

class SessionAnswerToggle(
    val question: QuizSessionQuestionRestrictedDTO,
    val answer: QuizSessionQuestionAnswerRestrictedDTO
) : RAction

class SessionDifficultyScaleLoaded(val difficultyScale: DifficultyScaleFullDTO?) : RAction
class SessionLoaded(val session: QuizSessionFullDTO) : RAction
class SessionQuestionsLoaded(val questions: List<QuizSessionQuestionRestrictedDTO>) : RAction
class SessionAnswersLoaded(val answers: List<QuizSessionAnswerRestrictedDTO>) : RAction
class SessionQuizLoaded(val quiz: QuizRestrictedDTO) : RAction
class SessionSetSequence(val seq: Int) : RAction
class SessionRemainingCountSet(val count: Long) : RAction
class SessionDecreaseRemainingCount : RAction
class SessionBootstrapStarted : RAction
class SessionBootstrapCompleted : RAction
class SessionGoFirstUnanswered : RAction
class SessionGoLastUnanswered : RAction
