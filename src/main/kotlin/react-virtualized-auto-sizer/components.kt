@file:Suppress("PackageDirectoryMismatch")

package react.virtualized.auto.sizer

import react.RBuilder
import react.RElementBuilder
import react.ReactElement

fun RBuilder.autoSizer(
    handler: (size: Size) -> ReactElement
) = child(AutoSizer::class) {
    attrs {
        this.children = handler
    }
}
