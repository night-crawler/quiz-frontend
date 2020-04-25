package fm.force.ui.component.question.create

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.component.loadingCard
import fm.force.ui.component.question.dto.toEditDTO
import fm.force.ui.effect.UseState
import fm.force.ui.util.RouterContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*


val EditQuestionFormHoc = functionalComponent<EditQuestionFormProps> {
    val routerContext = useContext(RouterContext)
    val params = routerContext.match.params.unsafeCast<MatchParams>()

    var question: QuestionFullDTO? by UseState(null)

    useEffect(listOf(params.id)) {
        GlobalScope.promise {
            question = ReduxStore.DEFAULT.client.getQuestion(params.id.toLong())
        }
    }

    if (question == null) {
        loadingCard()
        return@functionalComponent
    }

    reduxEditQuestionForm {
        attrs {
            question?.let { initialValues = it.toEditDTO() }
        }
    }
}

fun RBuilder.editQuestionForm() = child(EditQuestionFormHoc) {
}
