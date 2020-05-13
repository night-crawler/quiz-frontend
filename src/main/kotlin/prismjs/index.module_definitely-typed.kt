@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)
@file:JsModule("prismjs")

package prismjs
import kotlin.js.RegExp
import org.w3c.dom.Element
import org.w3c.dom.ParentNode

external var languages: LanguageMapProtocol /* LanguageMapProtocol & LanguageMap */

// external var plugins: Record<String, Any>
external var plugins: dynamic

external fun highlightAll(async: Boolean = definedExternally, callback: HighlightCallback = definedExternally)

external fun highlightAllUnder(
    container: ParentNode,
    async: Boolean = definedExternally,
    callback: HighlightCallback = definedExternally
)

external fun highlightElement(
    element: Element,
    async: Boolean = definedExternally,
    callback: HighlightCallback = definedExternally
)

external fun highlight(
    text: String,
    grammar: GrammarRest /* GrammarRest & Record<String, GrammarValue> */,
    language: String
): String

external fun tokenize(
    text: String,
    grammar: GrammarRest /* GrammarRest & Record<String, GrammarValue> */
): Array<dynamic /* String | Token */>

// external interface Environment : Record<String, Any> {
//
external interface Environment {
    var selector: String?
        get() = definedExternally
        set(value) = definedExternally
    var element: Element?
        get() = definedExternally
        set(value) = definedExternally
    var language: String?
        get() = definedExternally
        set(value) = definedExternally
    var grammar: GrammarRest /* GrammarRest & Record<String, GrammarValue> */
    var code: String?
        get() = definedExternally
        set(value) = definedExternally
    var highlightedCode: String?
        get() = definedExternally
        set(value) = definedExternally
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
    var content: String?
        get() = definedExternally
        set(value) = definedExternally
    var tag: String?
        get() = definedExternally
        set(value) = definedExternally
    var classes: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var attributes: Any?
        get() = definedExternally
        set(value) = definedExternally
    var parent: Array<dynamic /* String | Token */>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GrammarRest {
    operator fun get(key: String): dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
    operator fun set(key: String, value: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */)
    var keyword: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var number: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var function: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var string: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var boolean: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var operator: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var punctuation: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var atrule: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var url: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var selector: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var property: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var important: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var style: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var comment: dynamic /* RegExp | TokenObject | Array<dynamic /* RegExp | TokenObject */> */
        get() = definedExternally
        set(value) = definedExternally
    var rest: GrammarRest /* GrammarRest & Record<String, GrammarValue> */
}

external interface TokenObject {
    var pattern: RegExp
    var lookbehind: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var greedy: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var alias: dynamic /* String | Array<String> */
        get() = definedExternally
        set(value) = definedExternally
    var inside: GrammarRest /* GrammarRest & Record<String, GrammarValue> */
}

external interface LanguageMap {
    @nativeGetter
    operator fun get(language: String): GrammarRest? /* GrammarRest & Record<String, GrammarValue> */

    @nativeSetter
    operator fun set(language: String, value: GrammarRest /* GrammarRest & Record<String, GrammarValue> */)
}

external interface LanguageMapProtocol {
    fun extend(
        id: String,
        redef: GrammarRest /* GrammarRest & Record<String, GrammarValue> */
    ): GrammarRest /* GrammarRest & Record<String, GrammarValue> */

    fun insertBefore(
        inside: String,
        before: String,
        insert: GrammarRest /* GrammarRest & Record<String, GrammarValue> */,
        root: LanguageMap
    ): GrammarRest /* GrammarRest & Record<String, GrammarValue> */
}

open external class Token {
    constructor(type: String, content: String, alias: String, matchedStr: String, greedy: Boolean)
    constructor(type: String, content: String, alias: Array<String>, matchedStr: String, greedy: Boolean)
    constructor(type: String, content: Token, alias: String, matchedStr: String, greedy: Boolean)
    constructor(type: String, content: Token, alias: Array<String>, matchedStr: String, greedy: Boolean)
    constructor(
        type: String,
        content: Array<dynamic /* String | Token */>,
        alias: String,
        matchedStr: String,
        greedy: Boolean
    )

    constructor(
        type: String,
        content: Array<dynamic /* String | Token */>,
        alias: Array<String>,
        matchedStr: String,
        greedy: Boolean
    )

    open var type: String
    open var content: dynamic /* String | Token | Array<dynamic /* String | Token */> */
    open var alias: dynamic /* String | Array<String> */
    open var length: Number
    open var greedy: Boolean

    companion object {
        fun stringify(
            token: String,
            language: String,
            parent: Array<dynamic /* String | Token */> = definedExternally
        ): String

        fun stringify(
            token: Token,
            language: String,
            parent: Array<dynamic /* String | Token */> = definedExternally
        ): String

        fun stringify(
            token: Array<dynamic /* String | Token */>,
            language: String,
            parent: Array<dynamic /* String | Token */> = definedExternally
        ): String
    }
}
