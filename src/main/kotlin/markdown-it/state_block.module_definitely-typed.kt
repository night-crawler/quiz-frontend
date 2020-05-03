@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

package markdown.it

@JsModule("markdown-it")
open external class StateBlock : StateCore {
    open var parentType: String /* 'blockquote' | 'list' | 'root' | 'paragraph' | 'reference' */
    open var eMarks: Array<Number>
    open var bMarks: Array<Number>
    open var bsCount: Array<Number>
    open var sCount: Array<Number>
    open var tShift: Array<Number>
    open var blkIndent: Number
    open var ddIndent: Number
    open var line: Number
    open var lineMax: Number
    open var tight: Boolean
}
