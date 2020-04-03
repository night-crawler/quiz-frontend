package fm.force.ui.component.form

import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.ui.component.field.WrappedMultilineField
import fm.force.ui.util.jsApply
import mui.lab.renderSampleAutocomplete
import react.RBuilder
import react.RProps
import react.functionalComponent
import redux.form.ConfigProps
import redux.form.InjectedFormProps
import redux.form.field
import redux.form.fieldArray
import redux.form.reduxForm
import styled.styledForm

interface CreateQuestionFormProps : InjectedFormProps<QuestionEditDTO, RProps, Any>
data class SampleItem(
    val item: String,
    val id: Int
)
val EditQuestionForm = functionalComponent<CreateQuestionFormProps> {
    styledForm {
        mFormControl(fullWidth = true) {
            renderSampleAutocomplete(listOf(SampleItem("sample1", 1), SampleItem("sample2", 2)))
            field(
                QuestionEditDTO::name,
                WrappedMultilineField,
                jsApply {
                    label = "Question text"
                    helperText = "Input question text (multiline)"
                    variant = MFormControlVariant.outlined
                    rows = 2
                    rowsMax = 8
                }
            )

            fieldArray("answers", AnswerArrayField::class)
        }
    }
}

val reduxCreateQuestionForm = reduxForm(
    jsApply<ConfigProps<QuestionEditDTO, CreateQuestionFormProps, Any>> {
        form = "editQuestion"
    }
)(EditQuestionForm)

fun RBuilder.createQuestionForm() = reduxCreateQuestionForm {}
