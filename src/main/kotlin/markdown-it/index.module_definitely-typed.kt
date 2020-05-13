@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package markdown.it

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface MarkdownIt {
    fun render(md: String, env: Any = definedExternally): String
    fun renderInline(md: String, env: Any = definedExternally): String
    fun parse(src: String, env: Any): Array<Token>
    fun parseInline(src: String, env: Any): Array<Token>
    fun use(plugin: (md: MarkdownIt, params: Array<Any>) -> Unit, vararg params: Any): MarkdownIt
    var utils: `T$0`
    fun disable(rules: Array<String>, ignoreInvalid: Boolean = definedExternally): MarkdownIt
    fun disable(rules: String, ignoreInvalid: Boolean = definedExternally): MarkdownIt
    fun enable(rules: Array<String>, ignoreInvalid: Boolean = definedExternally): MarkdownIt
    fun enable(rules: String, ignoreInvalid: Boolean = definedExternally): MarkdownIt
    fun set(options: Options): MarkdownIt
    fun normalizeLink(url: String): String
    fun normalizeLinkText(url: String): String
    fun validateLink(url: String): Boolean
    var block: Any
    var core: Any
    var helpers: Any
    var inline: Any
    var linkify: Any
    var renderer: Any

    interface Options {
        var html: Boolean?
            get() = definedExternally
            set(value) = definedExternally
        var xhtmlOut: Boolean?
            get() = definedExternally
            set(value) = definedExternally
        var breaks: Boolean?
            get() = definedExternally
            set(value) = definedExternally
        var langPrefix: String?
            get() = definedExternally
            set(value) = definedExternally
        var linkify: Boolean?
            get() = definedExternally
            set(value) = definedExternally
        var typographer: Boolean?
            get() = definedExternally
            set(value) = definedExternally
        var quotes: String?
            get() = definedExternally
            set(value) = definedExternally
        var highlight: ((str: String, lang: String) -> String)?
            get() = definedExternally
            set(value) = definedExternally
    }

    interface Rule<S : StateCore> {
        @nativeInvoke
        operator fun invoke(state: S, silent: Boolean = definedExternally): dynamic /* Boolean | Unit */
    }

    interface RuleInline : Rule<StateInline>
    interface RuleBlock : Rule<StateBlock>
    interface RulerInline : Ruler<StateInline>
    interface RulerBlock : Ruler<StateBlock>
    interface Delimiter {
        var close: Boolean
        var end: Number
        var jump: Number
        var length: Number
        var level: Number
        var marker: Number
        var open: Boolean
        var token: Number
    }
}

external class MarkdownItConstructor {
    @nativeInvoke
    operator fun invoke(): MarkdownIt

    @nativeInvoke
    operator fun invoke(presetName: String, options: MarkdownIt.Options = definedExternally): MarkdownIt

    @nativeInvoke
    operator fun invoke(options: MarkdownIt.Options): MarkdownIt
}

external interface `T$0` {
    fun assign(obj: Any): Any
    fun isString(obj: Any): Boolean
    fun has(obj: Any, key: String): Boolean
    fun unescapeMd(str: String): String
    fun unescapeAll(str: String): String
    fun isValidEntityCode(str: Any): Boolean
    fun fromCodePoint(str: String): String
    fun escapeHtml(str: String): String
    fun arrayReplaceAt(src: Array<Any>, pos: Number, newElements: Array<Any>): Array<Any>
    fun isSpace(str: Any): Boolean
    fun isWhiteSpace(str: Any): Boolean
    fun isMdAsciiPunct(str: Any): Boolean
    fun isPunctChar(str: Any): Boolean
    fun escapeRE(str: String): String
    fun normalizeReference(str: String): String
}

@JsModule("markdown-it")
external fun mkMarkdown(): MarkdownIt

@JsModule("markdown-it")
external fun mkMarkdown(presetName: String, options: MarkdownIt.Options = definedExternally): MarkdownIt

@JsModule("markdown-it")
external fun mkMarkdown(options: MarkdownIt.Options): MarkdownIt
