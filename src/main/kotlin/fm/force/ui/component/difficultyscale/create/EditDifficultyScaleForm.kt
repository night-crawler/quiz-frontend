package fm.force.ui.component.difficultyscale.create

import fm.force.ui.component.difficultyScale.create.EditDifficultyScaleFormProps
import fm.force.ui.component.difficultyScale.create.MatchParams
import fm.force.ui.component.difficultyScale.create.reduxEditDifficultyScaleForm
import fm.force.ui.component.main.loadingCard
import fm.force.ui.dto.toEditDTO
import fm.force.ui.hook.useClient
import fm.force.ui.util.RouterContext
import react.RBuilder
import react.child
import react.functionalComponent
import react.useContext

val EditDifficultyScaleFormHoc = functionalComponent<EditDifficultyScaleFormProps> {
    val routerContext = useContext(RouterContext)
    val params = routerContext.match.params.unsafeCast<MatchParams>()

    val difficultyScale = useClient {
        getDifficultyScale(params.id.toLong())
    }

    if (difficultyScale == null) {
        loadingCard()
        return@functionalComponent
    }

    reduxEditDifficultyScaleForm {
        attrs {
            initialValues = difficultyScale.toEditDTO()
        }
    }
}

fun RBuilder.editDifficultyScaleForm() = child(EditDifficultyScaleFormHoc) {
}
