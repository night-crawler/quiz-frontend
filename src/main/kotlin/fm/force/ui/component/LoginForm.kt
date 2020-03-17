package fm.force.ui.component

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.MButtonVariant
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.form.mFormControl
import com.ccfraser.muirwik.components.spacingUnits
import fm.force.ui.action.LoginThunk
import fm.force.ui.client.dto.ErrorMessage
import fm.force.ui.client.dto.JwtResponseTokensDTO
import fm.force.ui.client.dto.LoginRequestDTO
import fm.force.ui.component.field.WrappedMTextField
import fm.force.ui.util.jsApply
import kotlinext.js.js
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
import react.dom.li
import react.dom.ul
import redux.form.ConfigProps
import redux.form.InjectedFormProps
import redux.form.field
import redux.form.reduxForm
import styled.StyleSheet
import styled.styledForm

interface LoginFormProps : InjectedFormProps<LoginRequestDTO, RProps, Any>

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
                field(
                    "email", WrappedMTextField::class,
                    jsApply {
                        fieldType = InputType.email
                        label = "Login email"
                        helperText = "Enter your email"
                        variant = MFormControlVariant.filled
                    }
                )
                field(
                    "password", WrappedMTextField::class,
                    jsApply {
                        fieldType = InputType.password
                        label = "Password"
                        variant = MFormControlVariant.filled
                    }
                )
                mButton(
                    "Login",
                    color = MColor.primary,
                    variant = MButtonVariant.contained,
                    disabled = /*props.pristine ||*/ props.submitting
                ) {
                    attrs.asDynamic().type = "submit"
                }

                if (props.error != undefined) {
                    ul {
                        props.error.unsafeCast<List<ErrorMessage>>().map { errorMessage ->
                            li { +errorMessage.message }
                        }
                    }
                }
            }
        }
    }
}

val reduxLoginForm = reduxForm(
    jsApply<ConfigProps<LoginRequestDTO, InjectedFormProps<LoginRequestDTO, RProps, Any>, Any>> {
        initialValues = js { email = "admin@force.fm"; password = "sample123" }
        form = "loginForm"
        onSubmit = { loginRequest, dispatch, _ ->
            // jwtResponseTokensDTO is unchecked, it's just a JavaScript plain object
            dispatch(LoginThunk(loginRequest))
        }
    }
)(LoginForm::class.js)

fun RBuilder.loginForm() = reduxLoginForm {}
