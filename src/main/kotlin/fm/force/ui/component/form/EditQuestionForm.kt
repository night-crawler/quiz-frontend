package fm.force.ui.component.form

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.component.field.TagsAutocompleteField
import fm.force.ui.component.field.TopicsAutocompleteField
import fm.force.ui.component.field.WrappedMultilineField
import fm.force.ui.component.field.WrappedTextField
import fm.force.ui.component.field.fieldErrors
import fm.force.ui.reducer.action.CreateQuestionThunk
import fm.force.ui.util.jsApply
import kotlinx.html.InputType
import kotlinx.html.onSubmit
import react.RBuilder
import react.RProps
import react.functionalComponent
import redux.form.ConfigProps
import redux.form.InjectedFormProps
import redux.form.field
import redux.form.fieldArray
import redux.form.reduxForm
import styled.styledForm

interface EditQuestionFormProps : InjectedFormProps<QuestionEditDTO, RProps, Any>

val EditQuestionForm = functionalComponent<EditQuestionFormProps> { props ->
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
                    variant = MFormControlVariant.outlined
                }
            )
            field(
                QuestionEditDTO::text,
                WrappedMultilineField,
                jsApply {
                    label = "Question text"
                    helperText = "Input question text (multiline)"
                    variant = MFormControlVariant.outlined
                    rows = 2
                    rowsMax = 8
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
                    fieldType = InputType.number
                    label = "Difficulty"
                    helperText = "Question difficulty in range 0-1000"
                    variant = MFormControlVariant.outlined
                    rows = 2
                    rowsMax = 8
                }
            )

            fieldArray(QuestionEditDTO::answers, AnswerArrayField::class)

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
        form = "editQuestion"
        onSubmit = { questionEditDTO, dispatch, _ ->
            dispatch(CreateQuestionThunk(questionEditDTO))
        }
    }
)(EditQuestionForm)

fun RBuilder.createQuestionForm() = reduxCreateQuestionForm {}
