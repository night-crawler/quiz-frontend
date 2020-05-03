@file:JsModule("definitely-typed")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package sanitize.html

import kotlin.js.RegExp

external interface Attributes {
    @nativeGetter
    operator fun get(attr: String): String?

    @nativeSetter
    operator fun set(attr: String, value: String)
}

external interface Tag {
    var tagName: String
    var attribs: Attributes
    var text: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$6` {
    var name: String
    var multiple: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var values: Array<String>
}

external interface `T$0` {
    @nativeGetter
    operator fun get(index: String): Array<dynamic /* String | `T$6` */>?

    @nativeSetter
    operator fun set(index: String, value: Array<dynamic /* String | `T$6` */>)
}

external interface `T$1` {
    @nativeGetter
    operator fun get(index: String): Array<String>?

    @nativeSetter
    operator fun set(index: String, value: Array<String>)
}

external interface IDefaults {
    var allowedAttributes: `T$0`
    var allowedSchemes: Array<String>
    var allowedSchemesByTag: `T$1`
    var allowedTags: Array<String>
    var selfClosing: Array<String>
}

external interface `T$2` {
    @nativeGetter
    operator fun get(index: String): String?

    @nativeSetter
    operator fun set(index: String, value: String)
}

external interface IFrame {
    var tag: String
    var attribs: `T$2`
    var text: String
    var tagPosition: Number
}

external interface `T$3` {
    @nativeGetter
    operator fun get(index: String): Array<RegExp>?

    @nativeSetter
    operator fun set(index: String, value: Array<RegExp>)
}

external interface `T$4` {
    @nativeGetter
    operator fun get(index: String): `T$3`?

    @nativeSetter
    operator fun set(index: String, value: `T$3`)
}

external interface `T$5` {
    @nativeGetter
    operator fun get(tagName: String): dynamic /* String | Transformer */

    @nativeSetter
    operator fun set(tagName: String, value: String)

    @nativeSetter
    operator fun set(tagName: String, value: Transformer)
}

external interface IOptions {
    var allowedAttributes: dynamic /* `T$0` | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var allowedStyles: `T$4`?
        get() = definedExternally
        set(value) = definedExternally
    var allowedClasses: dynamic /* `T$1` | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var allowedIframeHostnames: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var allowIframeRelativeUrls: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var allowedSchemes: dynamic /* Array<String> | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var allowedSchemesByTag: dynamic /* `T$1` | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var allowedSchemesAppliedToAttributes: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var allowProtocolRelative: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var allowedTags: dynamic /* Array<String> | Boolean */
        get() = definedExternally
        set(value) = definedExternally
    var textFilter: ((text: String) -> String)?
        get() = definedExternally
        set(value) = definedExternally
    var exclusiveFilter: ((frame: IFrame) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var nonTextTags: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var selfClosing: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var transformTags: `T$5`?
        get() = definedExternally
        set(value) = definedExternally
    var parser: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external var defaults: IDefaults

external fun simpleTransform(tagName: String, attribs: Attributes, merge: Boolean = definedExternally): Transformer
