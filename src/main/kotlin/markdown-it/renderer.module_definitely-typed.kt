@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package markdown.it

external interface `T$3` {
    @nativeGetter
    operator fun get(name: String): TokenRender?

    @nativeSetter
    operator fun set(name: String, value: TokenRender)
}

@JsModule("definitely-typed")
open external class Renderer {
    open var rules: `T$3`
    open fun render(tokens: Array<Token>, options: Any, env: Any): String
    open fun renderAttrs(token: Token): String
    open fun renderInline(tokens: Array<Token>, options: Any, env: Any): String
    open fun renderToken(tokens: Array<Token>, idx: Number, options: Any): String
}
