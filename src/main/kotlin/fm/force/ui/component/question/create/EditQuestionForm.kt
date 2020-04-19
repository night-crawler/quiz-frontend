package fm.force.ui.component.question.create

import fm.force.quiz.common.dto.AnswerFullDTO
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.component.question.list.loadingCard
import fm.force.ui.effect.UseState
import fm.force.ui.reducer.action.CreateQuestionThunk
import fm.force.ui.util.RouterContext
import fm.force.ui.util.jsApply
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*
import redux.form.ConfigProps
import redux.form.reduxForm

fun AnswerFullDTO.toAnswerEditDTO(correctAnswers: Collection<AnswerFullDTO>) = AnswerEditDTO(
    text = text,
    isCorrect = this.id in correctAnswers.map { it.id }.toSet()
)

fun QuestionFullDTO.toEditDTO() = QuestionEditDTO(
    tags = tags.toTypedArray(),
    topics = topics.toTypedArray(),
    answers = answers.map { it.toAnswerEditDTO(correctAnswers) }.toTypedArray(),
    title = title,
    text = text,
    difficulty = difficulty
)

val wrapper = functionalComponent<EditQuestionFormProps> {
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

val reduxEditQuestionForm = reduxForm(
    jsApply<ConfigProps<QuestionEditDTO, EditQuestionFormProps, Any>> {
        form = "editQuestion"
        onSubmit = { questionEditDTO, dispatch, _ ->
            dispatch(CreateQuestionThunk(questionEditDTO))
        }
    }
)(EditQuestionForm)


fun RBuilder.editQuestionForm() = child(wrapper) {

}
