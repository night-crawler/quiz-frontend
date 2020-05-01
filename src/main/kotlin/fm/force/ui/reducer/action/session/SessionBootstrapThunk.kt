package fm.force.ui.reducer.action.session

import fm.force.quiz.common.dto.*
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.State
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class SessionAnswerToggled(
    val question: QuizSessionQuestionRestrictedDTO,
    val answer: QuizSessionQuestionAnswerRestrictedDTO
) : RAction

class SessionDifficultyScaleLoaded(val difficultyScale: DifficultyScaleFullDTO?) : RAction
class SessionLoaded(val session: QuizSessionFullDTO) : RAction
class SessionQuestionsLoaded(val questions: List<QuizSessionQuestionRestrictedDTO>) : RAction
class SessionAnswersLoaded(val answers: List<QuizSessionAnswerRestrictedDTO>) : RAction
class SessionQuizLoaded(val quiz: QuizRestrictedDTO) : RAction
class SessionSequenceSet(val seq: Int) : RAction

class SessionBootstrapStart : RAction
class SessionBootstrapComplete : RAction

class GoFirstUnanswered : RAction
class GoLastUnanswered : RAction

class SessionBootstrapThunk(private val sessionId: Long) : Thunk<State, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        dispatch(SessionBootstrapStart())

        val session = client.getSession(sessionId)

        val questions = retrieveQuizQuestions(client)
        val answers = retrieveQuizAnswers(client)

        val difficultyScale = session.difficultyScale?.let {
            client.getDifficultyScale(it)
        }

        session.quiz?.let {
            dispatch(SessionQuizLoaded(client.getRestrictedQuiz(it)))
        }

        dispatch(SessionLoaded(session))
        dispatch(SessionQuestionsLoaded(questions.sortedBy { it.seq }))
        dispatch(SessionAnswersLoaded(answers))
        dispatch(SessionSequenceSet(0))
        dispatch(SessionDifficultyScaleLoaded(difficultyScale))

        return dispatch(SessionBootstrapComplete())
    }

    private suspend fun retrieveQuizQuestions(client: QuizClient): MutableList<QuizSessionQuestionRestrictedDTO> {
        val questions = mutableListOf<QuizSessionQuestionRestrictedDTO>()
        var criteria = DefaultSearchCriteria()

        while (true) {
            val sessionQuestionPage = client.findSessionQuestions(sessionId, criteria)
            questions.addAll(sessionQuestionPage.content)
            if (sessionQuestionPage.isLast) {
                break
            }
            criteria = criteria.copy(page = criteria.page + 1)
        }
        return questions
    }

    private suspend fun retrieveQuizAnswers(client: QuizClient): MutableList<QuizSessionAnswerRestrictedDTO> {
        val answers = mutableListOf<QuizSessionAnswerRestrictedDTO>()
        var criteria = DefaultSearchCriteria()

        while (true) {
            val page = client.findSessionAnswers(sessionId, criteria)
            answers.addAll(page.content)
            if (page.isLast) {
                break
            }
            criteria = criteria.copy(page = criteria.page + 1)
        }
        return answers
    }
}
