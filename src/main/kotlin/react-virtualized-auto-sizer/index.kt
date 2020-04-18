@file:JsModule("react-virtualized-auto-sizer")

@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.virtualized.auto.sizer

import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface Size {
    var height: Number
    var width: Number
}

external interface AutoSizerProps : RProps {
    var children: (size: Size) -> ReactElement
    var className: String?
        get() = definedExternally
        set(value) = definedExternally
    var defaultHeight: Int?
        get() = definedExternally
        set(value) = definedExternally
    var defaultWidth: Int?
        get() = definedExternally
        set(value) = definedExternally
    var disableHeight: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var disableWidth: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var nonce: String?
        get() = definedExternally
        set(value) = definedExternally
    var onResize: ((size: Size) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var style: Any?
        get() = definedExternally
        set(value) = definedExternally
}

@JsName("default")
external class AutoSizer : Component<AutoSizerProps, RState> {
    override fun render(): ReactElement?
}
