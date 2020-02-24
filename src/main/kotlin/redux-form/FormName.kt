@file:JsModule("redux-form")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

import react.RClass
import react.RProps

external interface `T$3` {
    var form: String
    var sectionPrefix: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FormNameProps : RProps {
    var children: (props: `T$3`) -> dynamic // ReactNode
}

@JsName("default")
external var FormName: RClass<FormNameProps>
