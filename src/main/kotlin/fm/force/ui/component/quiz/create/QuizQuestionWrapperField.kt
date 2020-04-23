package fm.force.ui.component.quiz.create

import com.benasher44.uuid.uuid4
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.component.field.*
import fm.force.ui.util.jsApply
import kotlinx.css.*
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RState
import redux.form.WrappedFieldArrayProps
import redux.form.field
import styled.css

data class QuestionWrapperDTO(
    val question: QuestionFullDTO? = null,
    val uuid: String = uuid4().toString()
) {
    companion object
}

fun QuestionWrapperDTO.Companion.of() = QuestionWrapperDTO()

// this one must remain a class component since styled is created dynamically
class QuizQuestionWrapperField(props: WrappedFieldArrayProps<QuestionWrapperDTO>) :
    RComponent<WrappedFieldArrayProps<QuestionWrapperDTO>, RState>(props) {

    @Suppress("UNUSED_PARAMETER")
    private fun addAnswer(event: Event?) = props.fields.push(QuestionWrapperDTO.of())

    override fun RBuilder.render() {
        props.fields.map { member: String, index: Int, _ ->
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
                        QuestionWrapperDTO::question,
                        QuestionAutocompleteField,
                        jsApply { label = "Question" },
                        prefix = member
                    )

                    arrayFieldMoveButtons(props, index)
                }
            }
        }

        mButton(caption = "Add question", onClick = ::addAnswer)

        props.meta.error?.let { fieldErrors(it.unsafeCast<List<FieldError>>()) }
    }
}
