@file:JsModule("react-window-infinite-loader")

@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.window.infinite.loader

import react.*
import kotlin.js.Promise


external interface ChildrenCallbackProps {
    var onItemsRendered: OnItemsRendered
    var ref: RRef
}

external interface InfiniteLoaderProps : RProps {
    var isItemLoaded: (index: Int) -> Boolean
    var loadMoreItems: (startIndex: Int, stopIndex: Int) -> Promise<Any>?
    var itemCount: Int
    var children: (props: ChildrenCallbackProps) -> ReactElement
    var threshold: Int?
        get() = definedExternally
        set(value) = definedExternally
    var minimumBatchSize: Int?
        get() = definedExternally
        set(value) = definedExternally
}

@JsName("default")
open external class InfiniteLoader : Component<InfiniteLoaderProps, RState> {
    override fun render(): ReactElement?
}
