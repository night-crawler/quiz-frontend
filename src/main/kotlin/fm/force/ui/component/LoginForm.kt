package fm.force.ui.component

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.spacingUnits
import fm.force.ui.component.field.WrappedMTextField
import fm.force.util.jsApply
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.css.marginLeft
import kotlinx.css.marginRight
import kotlinx.html.InputType
import kotlinx.html.onSubmit
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import redux.form.ConfigProps
import redux.form.InjectedFormProps
import redux.form.field
import redux.form.reduxForm
import styled.StyleSheet
import styled.styledForm

data class LoginDTO(
    val email: String,
    val password: String
)

interface LoginFormProps : InjectedFormProps<LoginDTO, RProps, Any>

class LoginForm(props: LoginFormProps) : RComponent<LoginFormProps, RState>(props) {
    object ComponentStyles : StyleSheet("SampleStyles", isStatic = true) {
        val textField by css {
            marginLeft = 1.spacingUnits
            marginRight = 1.spacingUnits
            display = Display.block
        }
    }

    override fun RBuilder.render() {
        styledForm {
            attrs {
                onSubmit = props.handleSubmit.asDynamic()
            }
            mFormControl(fullWidth = true) {
                field("email", WrappedMTextField::class, jsApply {
                    fieldType = InputType.email
                    label = "Login email"
                    helperText = "Enter your email"
                    variant = MFormControlVariant.filled
                })
                field("password", WrappedMTextField::class, jsApply {
                    fieldType = InputType.password
                    label = "Password"
                    variant = MFormControlVariant.filled
                })
                mButton(
                    "Login",
                    color = MColor.primary,
                    variant = MButtonVariant.contained,
                    onClick = { props.handleSubmit(it) }
                ) {
                    attrs.asDynamic().type = "submit"
                }
            }
        }
    }
}

val reduxLoginForm = reduxForm(
    jsApply<ConfigProps<LoginDTO, InjectedFormProps<LoginDTO, RProps, Any>, Any>> {
        form = "loginForm"
        onSubmit = { a, b, c -> console.log(a, b, c) }
    }
)(LoginForm::class.js)

fun RBuilder.loginForm() = reduxLoginForm {}
