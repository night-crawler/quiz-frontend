@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

package markdown.it

@JsModule("definitely-typed")
open external class Token(type: String, tag: String, nesting: Number) {
    open var attrGet: (name: String) -> String?
    open var attrIndex: (name: String) -> Number
    open var attrJoin: (name: String, value: String) -> Unit
    open var attrPush: (attrData: Array<String>) -> Unit
    open var attrSet: (name: String, value: String) -> Unit
    open var attrs: Array<Array<String>>
    open var block: Boolean
    open var children: Array<Token>
    open var content: String
    open var hidden: Boolean
    open var info: String
    open var level: Number
    open var map: Array<Number>
    open var markup: String
    open var meta: Any
    open var nesting: Number
    open var tag: String
    open var type: String
}
