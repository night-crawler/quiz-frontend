package fm.force.ui.component.quiz.create

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.*
import fm.force.ui.component.field.*
import fm.force.ui.component.helmet
import fm.force.ui.component.quiz.action.CreateQuizThunk
import fm.force.ui.component.quiz.action.EditQuizThunk
import fm.force.ui.util.jsApply
import kotlinx.html.onSubmit
import react.RBuilder
import react.RProps
import react.dom.title
import react.functionalComponent
import redux.form.*
import styled.styledForm

interface EditQuizFormProps : InjectedFormProps<QuizEditDTO, RProps, Any> {
    var quiz: QuizFullDTO
}

interface MatchParams {
    val id: String
}

private val QuizForm = functionalComponent<EditQuizFormProps> { props ->
    helmet {
        title("Create new Quiz")
    }
    styledForm {
        attrs {
            onSubmit = props.handleSubmit.asDynamic()
        }
        mFormControl(fullWidth = true) {
            field(
                QuizEditDTO::title,
                WrappedTextField,
                jsApply {
                    label = "Quiz title"
                    variant = MFormControlVariant.outlined
                }
            )
            field(
                QuizEditDTO::tags,
                TagsAutocompleteField,
                jsApply { label = "Tags" }
            )
            field(
                QuizEditDTO::topics,
                TopicsAutocompleteField,
                jsApply { label = "Topics" }
            )
            fieldArray(
                QuizEditDTO::questionWrappers,
                QuizQuestionWrapperField::class
            )

            mButton(
                "Save",
                color = MColor.primary,
                variant = MButtonVariant.contained,
                disabled = /*props.pristine ||*/ props.submitting
            ) {
                attrs.asDynamic().type = "submit"
            }

            props.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
        }
    }
}

val reduxCreateQuizForm = reduxForm(
    jsApply<ConfigProps<QuizEditDTO, EditQuizFormProps, Any>> {
        form = "createQuiz"
        onSubmit = { quizEditDTO, dispatch, _ ->
            dispatch(CreateQuizThunk(quizEditDTO))
        }
    }
)(QuizForm)

val reduxEditQuizForm = reduxForm(
    jsApply<ConfigProps<QuizEditDTO, EditQuizFormProps, Any>> {
        form = "editQuiz"
        onSubmit = { quizEditDTO, dispatch, _ ->
            dispatch(EditQuizThunk(quizEditDTO))
        }
    }
)(QuizForm)

fun RBuilder.createQuizForm() = reduxCreateQuizForm {
    attrs {
        initialValues = QuizEditDTO.of()
    }
}
