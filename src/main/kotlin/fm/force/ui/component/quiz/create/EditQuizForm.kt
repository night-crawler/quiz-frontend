package fm.force.ui.component.quiz.create

import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.quiz.common.dto.QuizFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.component.loadingCard
import fm.force.ui.effect.UseState
import fm.force.ui.util.RouterContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import react.*

fun QuestionFullDTO.toQuestionWrapperDTO() = QuestionWrapperDTO(question = this)

fun QuizFullDTO.toEditDTO() = QuizEditDTO(
    id = id,
    title = title,
    tags = tags.toTypedArray(),
    topics = topics.toTypedArray(),
    questionWrappers = quizQuestions.map { it.question.toQuestionWrapperDTO() }.toTypedArray()
)

val EditQuizFormHoc = functionalComponent<EditQuizFormProps> {
    val routerContext = useContext(RouterContext)
    val params = routerContext.match.params.unsafeCast<MatchParams>()

    var quiz: QuizFullDTO? by UseState(null)

    useEffect(listOf(params.id)) {
        GlobalScope.promise {
            quiz = ReduxStore.DEFAULT.client.getQuiz(params.id.toLong())
        }
    }

    if (quiz == null) {
        loadingCard()
        return@functionalComponent
    }

    reduxEditQuizForm {
        attrs {
            quiz?.let { initialValues = it.toEditDTO() }
        }
    }
}

fun RBuilder.editQuizForm() = child(EditQuizFormHoc) {
}
