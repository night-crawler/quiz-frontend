@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

package markdown.it

@JsModule("definitely-typed")
open external class ParserInline {
    open fun parse(src: String, md: MarkdownIt, env: Any, outTokens: Array<Token>)
    open fun tokenize(state: StateCore)
    open fun skipToken(state: StateCore)
    open var ruler: MarkdownIt.RulerInline
    open var ruler2: MarkdownIt.RulerInline
}
