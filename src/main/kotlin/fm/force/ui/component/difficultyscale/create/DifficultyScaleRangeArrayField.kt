package fm.force.ui.component.difficultyscale.create

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.component.difficultyscale.dto.DifficultyScaleRangeEditDTO
import fm.force.ui.component.difficultyscale.dto.of
import fm.force.ui.component.field.WrappedTextField
import fm.force.ui.component.field.arrayFieldMoveButtons
import fm.force.ui.component.field.fieldErrors
import fm.force.ui.util.jsApply
import kotlinx.css.*
import kotlinx.html.InputType
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import redux.form.WrappedFieldArrayProps
import redux.form.field
import styled.css
import styled.styledDiv

// this one must remain a class component since styled is created dynamically
class DifficultyScaleRangeArrayField(props: WrappedFieldArrayProps<DifficultyScaleRangeEditDTO>) :
    RComponent<WrappedFieldArrayProps<DifficultyScaleRangeEditDTO>, RState>(props) {

    @Suppress("UNUSED_PARAMETER")
    private fun addDifficultyScaleRange(event: Event?) = props.fields.push(DifficultyScaleRangeEditDTO.of())

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
                        DifficultyScaleRangeEditDTO::title,
                        WrappedTextField,
                        jsApply {
                            label = "Difficulty Scale Range Title"
                            variant = MFormControlVariant.outlined
                        },
                        key = props.meta.form + fields[index].uuid,
                        prefix = member
                    )

                    styledDiv {
                        css {
                            display = Display.flex
                            flexDirection = FlexDirection.row
                        }
                        field(
                            DifficultyScaleRangeEditDTO::min,
                            WrappedTextField,
                            jsApply {
                                label = "Min"
                                variant = MFormControlVariant.outlined
                                fieldType = InputType.number
                            },
                            key = props.meta.form + "min" + fields[index].uuid,
                            prefix = member
                        )

                        field(
                            DifficultyScaleRangeEditDTO::max,
                            WrappedTextField,
                            jsApply {
                                label = "Max"
                                variant = MFormControlVariant.outlined
                                fieldType = InputType.number
                            },
                            key = props.meta.form + "max" + fields[index].uuid,
                            prefix = member
                        )
                    }

                    arrayFieldMoveButtons(props, index)
                }
            }
        }

        mButton(caption = "Add difficulty Scale Range", onClick = ::addDifficultyScaleRange)

        props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
    }
}
