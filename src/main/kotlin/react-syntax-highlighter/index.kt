@file:JsModule("react-syntax-highlighter")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.syntax.highlighter

import react.*

external interface SyntaxHighlighterProps : RProps {
    var language: String?
        get() = definedExternally
        set(value) = definedExternally
    var style: Any?
        get() = definedExternally
        set(value) = definedExternally
    var customStyle: Any?
        get() = definedExternally
        set(value) = definedExternally
    var lineProps: dynamic /* lineTagPropsFunction | React.HTMLProps<HTMLElement> */
        get() = definedExternally
        set(value) = definedExternally
    var codeTagProps: Any?
        get() = definedExternally
        set(value) = definedExternally
    var useInlineStyles: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showLineNumbers: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var startingLineNumber: Number?
        get() = definedExternally
        set(value) = definedExternally
    var lineNumberStyle: Any?
        get() = definedExternally
        set(value) = definedExternally

    @nativeGetter
    operator fun get(spread: String): Any?

    @nativeSetter
    operator fun set(spread: String, value: Any)
}

@JsName("default")
external class ReactSyntaxHighlighter : Component<SyntaxHighlighterProps, RState> {
    override fun render(): ReactElement?
}

@JsName("Prism")
external class PrismSyntaxHighlighter : Component<SyntaxHighlighterProps, RState> {
    override fun render(): ReactElement?
}
