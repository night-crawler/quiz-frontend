package fm.force.ui.component.difficultyScale.create

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.component.difficultyscale.create.DifficultyScaleRangeArrayField
import fm.force.ui.reducer.action.difficultyscale.CreateDifficultyScaleThunk
import fm.force.ui.dto.DifficultyScaleEditDTO
import fm.force.ui.dto.of
import fm.force.ui.reducer.action.difficultyscale.EditDifficultyScaleThunk
import fm.force.ui.component.field.WrappedTextField
import fm.force.ui.component.field.fieldErrors
import fm.force.ui.component.main.helmet
import fm.force.ui.util.jsApply
import kotlinx.html.InputType
import kotlinx.html.onSubmit
import react.RBuilder
import react.RProps
import react.dom.title
import react.functionalComponent
import redux.form.*
import styled.styledForm

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
            dispatch(
                CreateDifficultyScaleThunk(
                    difficultyScaleEditDTO
                )
            )
        }
    }
)(DifficultyScaleForm)

val reduxEditDifficultyScaleForm = reduxForm(
    jsApply<ConfigProps<DifficultyScaleEditDTO, EditDifficultyScaleFormProps, Any>> {
        form = "editDifficultyScale"
        onSubmit = { difficultyScaleEditDTO, dispatch, _ ->
            dispatch(
                EditDifficultyScaleThunk(
                    difficultyScaleEditDTO
                )
            )
        }
    }
)(DifficultyScaleForm)

fun RBuilder.createDifficultyScaleForm() = reduxCreateDifficultyScaleForm {
    attrs {
        initialValues = DifficultyScaleEditDTO.of()
    }
}
