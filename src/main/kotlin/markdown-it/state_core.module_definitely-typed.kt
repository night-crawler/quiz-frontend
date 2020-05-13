@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package markdown.it

@JsModule("definitely-typed")
open external class StateCore(src: String, md: MarkdownIt, env: Any) {
    open var env: Any
    open var level: Number
    open var md: Any
    open var src: String
    open var tokens: Array<Token>
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun StateCore.get(undocumented: String): Any? = asDynamic()[undocumented]

@Suppress("NOTHING_TO_INLINE")
inline operator fun StateCore.set(undocumented: String, value: Any) {
    asDynamic()[undocumented] = value
}
