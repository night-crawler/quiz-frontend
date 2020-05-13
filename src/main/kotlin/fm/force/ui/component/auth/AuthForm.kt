package fm.force.ui.component.auth

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.FieldError
import fm.force.ui.client.dto.LoginRequestDTO
import fm.force.ui.client.dto.RegisterRequestDTO
import fm.force.ui.component.field.WrappedTextField
import fm.force.ui.component.field.fieldErrors
import fm.force.ui.reducer.action.auth.LoginThunk
import fm.force.ui.reducer.action.auth.RegisterThunk
import fm.force.ui.util.jsApply
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
import styled.styledForm

interface LoginFormProps : InjectedFormProps<LoginRequestDTO, RProps, Any>

class AuthForm(props: LoginFormProps) : RComponent<LoginFormProps, RState>(props) {
    override fun RBuilder.render() {
        styledForm {
            attrs {
                onSubmit = props.handleSubmit.asDynamic()
            }
            mFormControl(fullWidth = true) {
                field(
                    LoginRequestDTO::email,
                    WrappedTextField,
                    jsApply {
                        fieldType = InputType.email
                        label = "Login email"
                        helperText = "Enter your email"
                        variant = MFormControlVariant.filled
                    }
                )
                field(
                    LoginRequestDTO::password,
                    WrappedTextField,
                    jsApply {
                        fieldType = InputType.password
                        label = "Password"
                        variant = MFormControlVariant.filled
                    }
                )
                mButton(
                    "Submit",
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
}

val reduxLoginForm = reduxForm(
    jsApply<ConfigProps<LoginRequestDTO, InjectedFormProps<LoginRequestDTO, RProps, Any>, Any>> {
        initialValues = LoginRequestDTO("", "")
        form = "loginForm"
        onSubmit = { loginRequest, dispatch, _ ->
            // jwtResponseTokensDTO is unchecked, it's just a JavaScript plain object
            dispatch(LoginThunk(loginRequest))
        }
    }
)(AuthForm::class.js)

val reduxRegisterForm = reduxForm(
    jsApply<ConfigProps<RegisterRequestDTO, InjectedFormProps<RegisterRequestDTO, RProps, Any>, Any>> {
        initialValues = RegisterRequestDTO("", "")
        form = "registerForm"
        onSubmit = { loginRequest, dispatch, _ ->
            // jwtResponseTokensDTO is unchecked, it's just a JavaScript plain object
            dispatch(RegisterThunk(loginRequest))
        }
    }
)(AuthForm::class.js)

fun RBuilder.loginForm() = reduxLoginForm {}

fun RBuilder.registerForm() = reduxRegisterForm {}
