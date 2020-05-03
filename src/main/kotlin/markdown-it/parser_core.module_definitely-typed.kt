@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

package markdown.it

@JsModule("definitely-typed")
open external class ParserCore {
    open fun process(state: Any)
    open var ruler: Any
    open var State: Any
}
