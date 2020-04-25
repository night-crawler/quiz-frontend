package fm.force.ui.component.quiz.create

import fm.force.ui.component.loadingCard
import fm.force.ui.component.quiz.dto.toEditDTO
import fm.force.ui.hook.useClient
import fm.force.ui.util.RouterContext
import react.RBuilder
import react.child
import react.functionalComponent
import react.useContext

val EditQuizFormHoc = functionalComponent<EditQuizFormProps> {
    val routerContext = useContext(RouterContext)
    val params = routerContext.match.params.unsafeCast<MatchParams>()

    val quiz = useClient {
        getQuiz(params.id.toLong())
    }

    if (quiz == null) {
        loadingCard()
        return@functionalComponent
    }

    reduxEditQuizForm {
        attrs {
            initialValues = quiz.toEditDTO()
        }
    }
}

fun RBuilder.editQuizForm() = child(EditQuizFormHoc) {
}
