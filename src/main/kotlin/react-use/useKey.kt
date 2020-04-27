@file:JsModule("react-use/lib")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.use

import org.w3c.dom.events.KeyboardEvent
import react.RDependenciesArray

external interface UseKeyOptions {
    var event: String /* 'keydown' | 'keypress' | 'keyup' */
    var target: Any?
        get() = definedExternally
        set(value) = definedExternally
    var options: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external var useKey: (
    key: dynamic /* Nothing? | Nothing? | String | (event: KeyboardEvent) -> Boolean */,
    fn: (event: KeyboardEvent) -> Unit,
    opts: UseKeyOptions,
    deps: RDependenciesArray
) -> Unit
