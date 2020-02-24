@file:JsModule("redux-form")
@file:Suppress(
    "PackageDirectoryMismatch"
)

package redux.form

import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface FormSubmitProp<FormData, P, ErrorType> {
    var onSubmit: FormSubmitHandler<FormData, P, ErrorType>?
        get() = definedExternally
        set(value) = definedExternally
}

//external open class GenericForm<FormData, P, ErrorType> :
//    Component<Omit<FormHTMLAttributes<HTMLFormElement>, String /* "onSubmit" */> /* Omit<FormHTMLAttributes<HTMLFormElement>, String /* "onSubmit" */> & FormSubmitProp<FormData, P, ErrorType> */>
//
//external open class Form<FormData, P, ErrorType> :
//    Component<Omit<FormHTMLAttributes<HTMLFormElement>, String /* "onSubmit" */> /* Omit<FormHTMLAttributes<HTMLFormElement>, String /* "onSubmit" */> & FormSubmitProp<FormData, P, ErrorType> */>,
//    GenericForm<FormData, P, ErrorType>

open external class GenericForm : Component<RProps, RState> {
    override fun render(): ReactElement?
}

open external class Form<FormData, P, ErrorType> : Component<RProps, RState> {
    override fun render(): ReactElement?
}
