@file:Suppress("PackageDirectoryMismatch")

package react.window.infinite.loader

import react.RBuilder
import react.RRef
import react.ReactElement
import kotlin.js.Promise


fun RBuilder.infiniteLoader(
    isItemLoaded: (index: Int) -> Boolean,
    loadMoreItems: (startIndex: Int, stopIndex: Int) -> Promise<Any>?,
    itemCount: Int,
    handler: RBuilder.(onItemsRendered: OnItemsRendered, ref: RRef) -> ReactElement
) = child(InfiniteLoader::class) {
    attrs {
        this.isItemLoaded = isItemLoaded
        this.loadMoreItems = loadMoreItems
        this.itemCount = itemCount
        children = { props ->
            handler(props.onItemsRendered, props.ref)
        }
    }
}
