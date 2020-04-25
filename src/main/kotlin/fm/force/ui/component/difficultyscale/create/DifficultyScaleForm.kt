package fm.force.ui.component.difficultyScale.create

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.ui.component.difficultyscale.dto.DifficultyScaleEditDTO
import fm.force.ui.component.field.*
import fm.force.ui.component.helmet
import fm.force.ui.util.jsApply
import kotlinx.html.onSubmit
import react.RBuilder
import react.RProps
import react.dom.title
import react.functionalComponent
import redux.form.*
import styled.styledForm
import com.ccfraser.muirwik.components.form.MFormControlVariant
import fm.force.ui.component.difficultyscale.create.DifficultyScaleRangeArrayField
import fm.force.ui.component.difficultyscale.create.action.CreateDifficultyScaleThunk
import fm.force.ui.component.difficultyscale.dto.of
import fm.force.ui.component.difficultyscale.edit.action.EditDifficultyScaleThunk
import kotlinx.html.InputType

interface EditDifficultyScaleFormProps : InjectedFormProps<DifficultyScaleEditDTO, RProps, Any> {
    var difficultyScale: DifficultyScaleFullDTO
}

interface MatchParams {
    val id: String
}

private val DifficultyScaleForm = functionalComponent<EditDifficultyScaleFormProps> { props ->
    helmet {
        title("Create new DifficultyScale")
    }
    styledForm {
        attrs {
            onSubmit = props.handleSubmit.asDynamic()
        }
        mFormControl(fullWidth = true) {
            field(
                DifficultyScaleEditDTO::name,
                WrappedTextField,
                jsApply {
                    label = "Difficulty Scale name"
                    variant = MFormControlVariant.outlined
                }
            )

            field(
                DifficultyScaleEditDTO::max,
                WrappedTextField,
                jsApply {
                    label = "Max"
                    variant = MFormControlVariant.outlined
                    fieldType = InputType.number
                }
            )

            fieldArray(
                DifficultyScaleEditDTO::ranges,
                DifficultyScaleRangeArrayField::class
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

val reduxCreateDifficultyScaleForm = reduxForm(
    jsApply<ConfigProps<DifficultyScaleEditDTO, EditDifficultyScaleFormProps, Any>> {
        form = "createDifficultyScale"
        onSubmit = { difficultyScaleEditDTO, dispatch, _ ->
            dispatch(CreateDifficultyScaleThunk(difficultyScaleEditDTO))
        }
    }
)(DifficultyScaleForm)

val reduxEditDifficultyScaleForm = reduxForm(
    jsApply<ConfigProps<DifficultyScaleEditDTO, EditDifficultyScaleFormProps, Any>> {
        form = "editDifficultyScale"
        onSubmit = { difficultyScaleEditDTO, dispatch, _ ->
            dispatch(EditDifficultyScaleThunk(difficultyScaleEditDTO))
        }
    }
)(DifficultyScaleForm)

fun RBuilder.createDifficultyScaleForm() = reduxCreateDifficultyScaleForm {
    attrs {
        initialValues = DifficultyScaleEditDTO.of()
    }
}
