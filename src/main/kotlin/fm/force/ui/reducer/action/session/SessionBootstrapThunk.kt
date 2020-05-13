package fm.force.ui.reducer.action.session

import fm.force.quiz.common.dto.QuizSessionAnswerRestrictedDTO
import fm.force.quiz.common.dto.QuizSessionQuestionRestrictedDTO
import fm.force.ui.client.DefaultSearchCriteria
import fm.force.ui.client.QuizClient
import fm.force.ui.reducer.state.QuizState
import fm.force.ui.util.Thunk
import redux.RAction
import redux.WrapperAction

class SessionBootstrapThunk(private val sessionId: Long) : Thunk<QuizState, RAction, WrapperAction, QuizClient> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> QuizState,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        dispatch(SessionBootstrapStarted())

        val session = client.getSession(sessionId)

        val questions = retrieveQuizQuestions(client)
        val answers = retrieveQuizAnswers(client)

        val difficultyScale = session.difficultyScale?.let {
            client.getDifficultyScale(it)
        }

        val remainingCount = client.getRemainingSessionQuestionCount(sessionId)

        session.quiz?.let {
            dispatch(SessionQuizLoaded(client.getRestrictedQuiz(it)))
        }

        dispatch(SessionRemainingCountSet(remainingCount))
        dispatch(SessionLoaded(session))
        dispatch(SessionQuestionsLoaded(questions.sortedBy { it.seq }))
        dispatch(SessionAnswersLoaded(answers))
        dispatch(SessionSetSequence(0))
        dispatch(SessionDifficultyScaleLoaded(difficultyScale))

        return dispatch(SessionBootstrapCompleted())
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
