package fm.force.ui.reducer

import fm.force.ui.reducer.state.QuizState
import fm.force.ui.util.customCombineReducers
import history.History
import react.router.connected.connectRouter
import redux.form.reducer

fun combinedReducers(history: History<*>) =
    customCombineReducers(
        mapOf(
            QuizState::currentUser to ::currentUserReducer,
            QuizState::snacks to ::snackReducer,
            QuizState::appPreferences to ::appPreferencesReducer,
            QuizState::router to connectRouter(
                history
            ),
            QuizState::form to reducer,
            QuizState::quizSessionState to ::quizSessionStateReducer,
            QuizState::selectedQuestions to ::selectedQuestionsReducer,
            QuizState::quizComposerDTO to ::quizComposerReducer
        )
    )
