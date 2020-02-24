@file:JsModule("react-helmet")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.helmet

import org.w3c.dom.HTMLLinkElement
import org.w3c.dom.HTMLMetaElement
import org.w3c.dom.HTMLScriptElement
import org.w3c.dom.HTMLStyleElement
import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface HelmetTags {
    var baseTag: Array<Any>
    var linkTags: Array<HTMLLinkElement>
    var metaTags: Array<HTMLMetaElement>
    var noscriptTags: Array<Any>
    var scriptTags: Array<HTMLScriptElement>
    var styleTags: Array<HTMLStyleElement>
}

external interface HelmetProps : RProps {
    var async: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var base: Any?
        get() = definedExternally
        set(value) = definedExternally
    var bodyAttributes: Any? /* Any & OtherElementAttributes */
    var defaultTitle: String?
        get() = definedExternally
        set(value) = definedExternally
    var defer: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var encodeSpecialCharacters: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var htmlAttributes: Any? /* Any & OtherElementAttributes */
    var onChangeClientState: ((newState: Any, addedTags: HelmetTags, removedTags: HelmetTags) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var link: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var meta: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var noscript: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var script: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var style: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var titleAttributes: Any?
        get() = definedExternally
        set(value) = definedExternally
    var titleTemplate: String?
        get() = definedExternally
        set(value) = definedExternally
}

open external class Helmet : Component<HelmetProps, RState> {
    companion object {
        fun peek(): HelmetData
        fun rewind(): HelmetData
        fun renderStatic(): HelmetData
        var canUseDOM: Boolean
    }

    override fun render(): ReactElement?
}

external interface HelmetData {
    var base: HelmetDatum
    //    var bodyAttributes: HelmetHTMLBodyDatum
//    var htmlAttributes: HelmetHTMLElementDatum
    var link: HelmetDatum
    var meta: HelmetDatum
    var noscript: HelmetDatum
    var script: HelmetDatum
    var style: HelmetDatum
    var title: HelmetDatum
    var titleAttributes: HelmetDatum
}

external interface HelmetDatum {
    override fun toString(): String
    fun toComponent(): Component<RProps, RState>
}

// external interface HelmetHTMLBodyDatum {
//    override fun toString(): String
//    fun toComponent(): HTMLAttributes<HTMLBodyElement>
// }
//
// external interface HelmetHTMLElementDatum {
//    override fun toString(): String
//    fun toComponent(): React.HTMLAttributes<HTMLHtmlElement>
// }
