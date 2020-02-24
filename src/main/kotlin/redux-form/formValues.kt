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

//external fun <Values, P> formValues(obj: Values): (component: ComponentType<P /* P & Any */>) -> ComponentClass<P /* P & Any */>

external fun <Values, P : RProps> formValues(obj: Values): (component: Component<P, RState>) -> Component<P, RState>

//external fun <FormData, K : Any, P> formValues(vararg names: K): (component: ComponentType<P /* P & Any */>) -> ComponentClass<P /* P & Any */>

external fun <FormData, K : Any, P: RProps> formValues(vararg names: K): (component: Component<P, RState>) -> Component<P, RState >
