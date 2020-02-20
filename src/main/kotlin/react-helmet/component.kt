@file:Suppress("PackageDirectoryMismatch")

package react.helmet

import react.RBuilder

fun RBuilder.helmet(
    async: Boolean? = undefined,
    base: Any? = undefined,
    bodyAttributes: Any? /* Any & OtherElementAttributes */ = undefined,
    defaultTitle: String? = undefined,
    defer: Boolean? = undefined,
    encodeSpecialCharacters: Boolean? = undefined,
    htmlAttributes: Any? /* Any & OtherElementAttributes */ = undefined,
    onChangeClientState: ((newState: Any, addedTags: HelmetTags, removedTags: HelmetTags) -> Unit)? = undefined,
    link: Array<Any>? = undefined,
    meta: Array<Any>? = undefined,
    noscript: Array<Any>? = undefined,
    script: Array<Any>? = undefined,
    style: Array<Any>? = undefined,
    title: String? = undefined,
    titleAttributes: Any? = undefined,
    titleTemplate: String? = undefined,
    children: RBuilder.() -> Unit
) = child(Helmet::class) {
    attrs {
        this.async = async
        this.base = base
        this.bodyAttributes = bodyAttributes
        this.defaultTitle = defaultTitle
        this.defer = defer
        this.encodeSpecialCharacters = encodeSpecialCharacters
        this.htmlAttributes = htmlAttributes
        this.onChangeClientState = onChangeClientState
        this.link = link
        this.meta = meta
        this.noscript = noscript
        this.script = script
        this.style = style
        this.title = title
        this.titleAttributes = titleAttributes
        this.titleTemplate = titleTemplate
    }
    children()
}
