package fm.force.ui.reducer.state

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.dto.QuizComposerDTO
import fm.force.ui.dto.of
import fm.force.ui.reducer.action.Snack
import fm.force.ui.util.createLocation
import kotlinext.js.jsObject
import react.router.connected.RouterState

data class QuizState(
    val currentUser: UserFullDTO = UserFullDTO.of(),
    val snacks: List<Snack> = listOf(),
    val appPreferences: AppPreferences = AppPreferences.of(),
    val router: RouterState<CustomLocationState> = RouterState(
        createLocation(state = CustomLocationState()), "POP"
    ),
    val form: dynamic = jsObject { },

    val quizSessionState: QuizSessionState = QuizSessionState.of(),
    val selectedQuestions: Set<QuestionFullDTO> = setOf(),
    val quizComposerDTO: QuizComposerDTO = QuizComposerDTO.of()
)
