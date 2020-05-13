@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

package markdown.it

@JsModule("definitely-typed")
open external class ParserBlock {
    open fun parse(src: String, md: MarkdownIt, env: Any, outTokens: Array<Token>)
    open var ruler: MarkdownIt.RulerBlock
}
