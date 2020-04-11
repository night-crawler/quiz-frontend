@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface FormSectionProps : RProps {
    var name: String
    var component: Any?
        get() = definedExternally
        set(value) = definedExternally
}

open external class FormSection : Component<FormSectionProps, RState /* FormSectionProps<P> & P */> {
    override fun render(): ReactElement?
}
