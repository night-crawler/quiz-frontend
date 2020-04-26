package fm.force.ui.reducer.action.difficultyscale

import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.ui.client.QuizClient
import fm.force.ui.component.main.defaultSubmitErrorHandler
import fm.force.ui.dto.DifficultyScaleEditDTO
import fm.force.ui.dto.toPatchDTO
import fm.force.ui.dto.validate
import fm.force.ui.reducer.State
import fm.force.ui.reducer.action.Noop
import fm.force.ui.reducer.action.ThunkForm
import mu.KotlinLogging
import redux.RAction
import redux.WrapperAction

class EditDifficultyScaleStart(val difficultyScaleEditDTO: DifficultyScaleEditDTO) : RAction

class EditDifficultyScaleSuccess(val difficultyScaleEditDTO: DifficultyScaleFullDTO) : RAction

private val logger = KotlinLogging.logger("EditDifficultyScaleThunk")

class EditDifficultyScaleThunk(private val editDTO: DifficultyScaleEditDTO) : ThunkForm() {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
        client: QuizClient
    ): WrapperAction {
        return checkedRun(
            start = { dispatch(
                EditDifficultyScaleStart(
                    editDTO
                )
            ) },
            error = defaultSubmitErrorHandler(dispatch)
        ) {
            editDTO.validate()
            val patchDTO = editDTO.toPatchDTO()

            val difficultyScale = client.patchDifficultyScale(editDTO.id!!, patchDTO)
            dispatch(
                EditDifficultyScaleSuccess(
                    difficultyScale
                )
            )
            dispatch(Noop())
        }
    }
}
