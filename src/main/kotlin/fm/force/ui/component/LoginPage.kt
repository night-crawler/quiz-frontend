package fm.force.ui.component

import com.ccfraser.muirwik.components.form.MFormControlVariant
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.spacingUnits
import fm.force.ui.component.LoginPage.ComponentStyles.textField
import fm.force.util.jsApply
import kotlinx.css.marginLeft
import kotlinx.css.marginRight
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import redux.form.ConfigProps
import redux.form.WrappedFieldProps
import redux.form.field
import redux.form.reduxForm
import styled.StyleSheet
import styled.css
import styled.styledForm

class BasicInputWrapper(props: WrappedFieldProps) : RComponent<WrappedFieldProps, RState>(props) {
    override fun RBuilder.render() {
        mTextField(
            label = "Label?",
            name = props.input.name,
            value = props.input.value.asDynamic(),
            variant = MFormControlVariant.standard,
            onChange = props.input.onChange
        ) {
            css(textField)
        }
    }
}

class LoginPage(props: RProps) : RComponent<RProps, RState>(props) {
    object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
        val textField by css {
            marginLeft = 1.spacingUnits
            marginRight = 1.spacingUnits
        }
    }

    override fun RBuilder.render() {
        styledForm {
            field(BasicInputWrapper::class)
        }
    }
}

val wrappedStuff = reduxForm(
    jsApply<ConfigProps<Any, RProps, Any>> {
        form = "wrappedStuff"
        onSubmit = { a: dynamic, b: dynamic, c: dynamic, d: dynamic -> console.log(a, b, c, d) }
//    getFormState = { a -> jsObject {} }
    }
)(LoginPage::class.js)

fun RBuilder.loginPage() = wrappedStuff {}
