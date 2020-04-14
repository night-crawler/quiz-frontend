package fm.force.ui.component.form

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.component.field.WrappedCheckboxField
import fm.force.ui.component.field.WrappedMultilineField
import fm.force.ui.component.field.fieldErrors
import fm.force.ui.util.IconName
import fm.force.ui.util.jsApply
import kotlinx.css.Direction
import kotlinx.css.direction
import kotlinx.css.marginTop
import kotlinx.css.paddingLeft
import kotlinx.css.paddingRight
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RState
import redux.form.WrappedFieldArrayProps
import redux.form.field
import styled.css

// this one must remain a class component since styled is created dynamically
class AnswerArrayField(props: WrappedFieldArrayProps<AnswerEditDTO>) :
    RComponent<WrappedFieldArrayProps<AnswerEditDTO>, RState>(props) {

    override fun RBuilder.render() {
        mButton(caption = "Add answer", onClick = { props.fields.push(AnswerEditDTO("", isCorrect = false)) })

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

                    mCardActions {
                        css {
                            direction = Direction.rtl
                        }
                        mIconButton(IconName.DELETE_OUTLINE.iconMame, onClick = { props.fields.remove(index) })
                        if (index > 0) {
                            mIconButton(
                                IconName.KEYBOARD_ARROW_UP.iconMame,
                                onClick = { props.fields.move(index, index - 1) }
                            )
                        }

                        if (index < fields.length - 1) {
                            mIconButton(
                                IconName.KEYBOARD_ARROW_DOWN.iconMame,
                                onClick = { props.fields.move(index, index + 1) }
                            )
                        }
                    }
                }
            }
        }

        props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
    }
}
