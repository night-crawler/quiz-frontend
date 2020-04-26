package fm.force.ui.component.question.create

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.component.field.*
import fm.force.ui.component.main.helmet
import fm.force.ui.reducer.action.question.CreateQuestionThunk
import fm.force.ui.reducer.action.question.EditQuestionThunk
import fm.force.ui.dto.QuestionEditDTO
import fm.force.ui.dto.of
import fm.force.ui.util.jsApply
import kotlinx.html.onSubmit
import react.RBuilder
import react.RProps
import react.dom.title
import react.functionalComponent
import redux.form.*
import styled.styledForm

interface EditQuestionFormProps : InjectedFormProps<QuestionEditDTO, RProps, Any> {
    var question: QuestionFullDTO
}

interface MatchParams {
    val id: String
}

private val QuestionForm = functionalComponent<EditQuestionFormProps> { props ->
    helmet {
        title("Create new Question")
    }
    styledForm {
        attrs {
            onSubmit = props.handleSubmit.asDynamic()
        }
        mFormControl(fullWidth = true) {
            field(
                QuestionEditDTO::title,
                WrappedTextField,
                jsApply {
                    label = "Question title"
                    helperText = "Input question title (multiline)"
                    variant = com.ccfraser.muirwik.components.form.MFormControlVariant.outlined
                }
            )
            field(
                QuestionEditDTO::text,
                CodeMirrorField,
                jsApply {
                    label = "Question text"
                }
            )
            field(
                QuestionEditDTO::help,
                CodeMirrorField,
                jsApply {
                    label = "Question help"
                }
            )
            field(
                QuestionEditDTO::tags,
                TagsAutocompleteField,
                jsApply { label = "Tags" }
            )
            field(
                QuestionEditDTO::topics,
                TopicsAutocompleteField,
                jsApply { label = "Topics" }
            )
            field(
                QuestionEditDTO::difficulty,
                WrappedTextField,
                jsApply {
                    fieldType = kotlinx.html.InputType.number
                    label = "Difficulty"
                    helperText = "Question difficulty in range 0-1000"
                    variant = com.ccfraser.muirwik.components.form.MFormControlVariant.outlined
                    rows = 2
                    rowsMax = 8
                }
            )

            fieldArray(
                QuestionEditDTO::answers,
                AnswerArrayField::class
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

val reduxCreateQuestionForm = reduxForm(
    jsApply<ConfigProps<QuestionEditDTO, EditQuestionFormProps, Any>> {
        form = "createQuestion"
        onSubmit = { questionEditDTO, dispatch, _ ->
            dispatch(CreateQuestionThunk(questionEditDTO))
        }
    }
)(QuestionForm)

val reduxEditQuestionForm = reduxForm(
    jsApply<ConfigProps<QuestionEditDTO, EditQuestionFormProps, Any>> {
        form = "editQuestion"
        onSubmit = { questionEditDTO, dispatch, _ ->
            dispatch(EditQuestionThunk(questionEditDTO))
        }
    }
)(QuestionForm)

fun RBuilder.createQuestionForm() = reduxCreateQuestionForm {
    attrs {
        initialValues = QuestionEditDTO.of()
    }
}
