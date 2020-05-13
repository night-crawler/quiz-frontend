@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package markdown.it

external interface `T$1`

@Suppress("NOTHING_TO_INLINE")
inline operator fun `T$1`.get(start: Number): Number? = asDynamic()[start]

@Suppress("NOTHING_TO_INLINE")
inline operator fun `T$1`.set(start: Number, value: Number) {
    asDynamic()[start] = value
}

external interface `T$2` {
    var can_open: Boolean
    var can_close: Boolean
    var length: Number
}

@JsModule("definitely-typed")
open external class StateInline : StateCore {
    open var cache: `T$1`
    open var delimiters: Array<MarkdownIt.Delimiter>
    open var pending: String
    open var pendingLevel: Number
    open var pos: Number
    open var posMax: Number
    open fun push(type: String, tag: String, nesting: Number): Token
    open fun pushPending(): Token
    open fun scanDelims(start: Number, canSplitWord: Boolean): `T$2`
}
