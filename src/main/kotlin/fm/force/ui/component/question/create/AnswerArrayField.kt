package fm.force.ui.component.question.create

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.component.field.WrappedCheckboxField
import fm.force.ui.component.field.WrappedMultilineField
import fm.force.ui.component.field.arrayFieldMoveButtons
import fm.force.ui.component.field.fieldErrors
import fm.force.ui.util.jsApply
import kotlinx.css.marginTop
import kotlinx.css.paddingLeft
import kotlinx.css.paddingRight
import kotlinx.css.px
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import redux.form.WrappedFieldArrayProps
import redux.form.field
import styled.css

// this one must remain a class component since styled is created dynamically
class AnswerArrayField(props: WrappedFieldArrayProps<AnswerEditDTO>) :
    RComponent<WrappedFieldArrayProps<AnswerEditDTO>, RState>(props) {

    @Suppress("UNUSED_PARAMETER")
    private fun addAnswer(event: Event?) = props.fields.push(AnswerEditDTO.of())

    override fun RBuilder.render() {
        props.fields.map { member: String, index: Int, fields ->
            mCard {
                css {
                    marginTop = 5.px
                }
                mFormControl(fullWidth = true) {
                    css {
                        paddingLeft = 10.px
                        paddingRight = 10.px
                    }
                    field(
                        AnswerEditDTO::text,
                        WrappedMultilineField,
                        jsApply {
                            label = "Answer Text"
                            variant = MFormControlVariant.outlined
                            rows = 2
                            rowsMax = 8
                        },
                        key = props.meta.form + fields[index].uuid,
                        prefix = member
                    )
                    field(
                        AnswerEditDTO::isCorrect,
                        WrappedCheckboxField,
                        jsApply {
                            fieldType = kotlinx.html.InputType.checkBox
                            label = "Is correct"
                            variant = MFormControlVariant.outlined
                        },
                        prefix = member
                    )

                    arrayFieldMoveButtons(props, index)
                }
            }
        }

        mButton(caption = "Add answer", onClick = ::addAnswer)

        props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
    }
}
