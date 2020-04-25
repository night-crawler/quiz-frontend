package fm.force.ui.component.difficultyscale.create

import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.component.difficultyScale.create.EditDifficultyScaleFormProps
import fm.force.ui.component.difficultyScale.create.MatchParams
import fm.force.ui.component.difficultyScale.create.reduxEditDifficultyScaleForm
import fm.force.ui.component.difficultyscale.dto.toEditDTO
import fm.force.ui.component.loadingCard
import fm.force.ui.effect.UseState
import fm.force.ui.util.RouterContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*

val EditDifficultyScaleFormHoc = functionalComponent<EditDifficultyScaleFormProps> {
    val routerContext = useContext(RouterContext)
    val params = routerContext.match.params.unsafeCast<MatchParams>()

    var difficultyScale: DifficultyScaleFullDTO? by UseState(null)

    useEffect(listOf(params.id)) {
        GlobalScope.promise {
            difficultyScale = ReduxStore.DEFAULT.client.getDifficultyScale(params.id.toLong())
        }
    }

    if (difficultyScale == null) {
        loadingCard()
        return@functionalComponent
    }

    reduxEditDifficultyScaleForm {
        attrs {
            difficultyScale?.let { initialValues = it.toEditDTO() }
        }
    }
}

fun RBuilder.editDifficultyScaleForm() = child(EditDifficultyScaleFormHoc) {
}
