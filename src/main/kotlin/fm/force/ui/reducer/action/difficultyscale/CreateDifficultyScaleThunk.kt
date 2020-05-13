package fm.force.ui.reducer.action.difficultyscale

import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.main.defaultSubmitErrorHandler
import fm.force.ui.dto.DifficultyScaleEditDTO
import fm.force.ui.dto.toPatchDTO
import fm.force.ui.dto.validate
import fm.force.ui.reducer.action.ThunkForm
import fm.force.ui.reducer.state.QuizState
import react.router.connected.replace
import redux.RAction
import redux.WrapperAction

class CreateDifficultyScaleStart(val difficultyScaleEditDTO: DifficultyScaleEditDTO) : RAction

class CreateDifficultyScaleSuccess(val difficultyScaleEditDTO: DifficultyScaleFullDTO) : RAction

class CreateDifficultyScaleThunk(private val editDTO: DifficultyScaleEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> QuizState,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = {
                dispatch(
                    CreateDifficultyScaleStart(
                        editDTO
                    )
                )
            },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            editDTO.validate()
            val patchDTO = editDTO.toPatchDTO()

            val difficultyScale = client.createDifficultyScale(patchDTO)
            dispatch(
                CreateDifficultyScaleSuccess(
                    difficultyScale
                )
            )
            dispatch(replace("/difficulty-scales/${difficultyScale.id}/edit"))
        }
    }
}
