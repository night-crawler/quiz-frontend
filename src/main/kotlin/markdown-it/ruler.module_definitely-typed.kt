@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package markdown.it

@JsModule("definitely-typed")
open external class Ruler<S : StateCore> {
    open fun after(afterName: String, ruleName: String, rule: MarkdownIt.Rule<S>, options: Any = definedExternally)
    open fun at(name: String, rule: MarkdownIt.Rule<S>, options: Any = definedExternally)
    open fun before(beforeName: String, ruleName: String, rule: MarkdownIt.Rule<S>, options: Any = definedExternally)
    open fun disable(rules: String, ignoreInvalid: Boolean = definedExternally): Array<String>
    open fun disable(rules: Array<String>, ignoreInvalid: Boolean = definedExternally): Array<String>
    open fun enable(rules: String, ignoreInvalid: Boolean = definedExternally): Array<String>
    open fun enable(rules: Array<String>, ignoreInvalid: Boolean = definedExternally): Array<String>
    open fun enableOnly(rule: String, ignoreInvalid: Boolean = definedExternally)
    open fun getRules(chain: String): Array<MarkdownIt.Rule<S>>
    open fun push(ruleName: String, rule: MarkdownIt.Rule<S>, options: Any = definedExternally)
}
