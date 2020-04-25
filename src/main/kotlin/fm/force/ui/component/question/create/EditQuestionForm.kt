package fm.force.ui.component.question.create

import fm.force.ui.component.loadingCard
import fm.force.ui.component.question.dto.toEditDTO
import fm.force.ui.hook.useClient
import fm.force.ui.util.RouterContext
import react.RBuilder
import react.child
import react.functionalComponent
import react.useContext

val EditQuestionFormHoc = functionalComponent<EditQuestionFormProps> {
    val routerContext = useContext(RouterContext)
    val params = routerContext.match.params.unsafeCast<MatchParams>()

    val question = useClient(listOf(params.id)) {
        getQuestion(params.id.toLong())
    }

    if (question == null) {
        loadingCard()
        return@functionalComponent
    }

    reduxEditQuestionForm {
        attrs {
            initialValues = question.toEditDTO()
        }
    }
}

fun RBuilder.editQuestionForm() = child(EditQuestionFormHoc) {
}
