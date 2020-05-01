@file:JsModule("react-window")

@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.window

import react.*

external interface ListStyles {
    var position: String
    var left: Int
    var top: Int
    var height: Int
    var width: Any
}

external interface ListChildComponentProps {
    var index: Int
    var style: ListStyles
    var data: Any
    var isScrolling: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GridChildComponentProps {
    var columnIndex: Int
    var rowIndex: Int
    var style: Any
    var data: Any
    var isScrolling: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface CommonProps : RProps {
    var className: String?
        get() = definedExternally
        set(value) = definedExternally
    var innerElementType: dynamic /* FunctionComponent<Any> | ComponentClass<Any> | String */
        get() = definedExternally
        set(value) = definedExternally
    var innerRef: RRef?
        get() = definedExternally
        set(value) = definedExternally
    var innerTagName: String?
        get() = definedExternally
        set(value) = definedExternally
    var itemData: Any?
        get() = definedExternally
        set(value) = definedExternally
    var outerElementType: dynamic /* FunctionComponent<Any> | ComponentClass<Any> | String */
        get() = definedExternally
        set(value) = definedExternally
    var outerRef: RRef?
        get() = definedExternally
        set(value) = definedExternally
    var outerTagName: String?
        get() = definedExternally
        set(value) = definedExternally
    var style: Any?
        get() = definedExternally
        set(value) = definedExternally
    var useIsScrolling: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ListOnItemsRenderedProps {
    var overscanStartIndex: Int
    var overscanStopIndex: Int
    var visibleStartIndex: Int
    var visibleStopIndex: Int
}

external interface ListOnScrollProps {
    var scrollDirection: String /* "forward" | "backward" */
    var scrollOffset: Int
    var scrollUpdateWasRequested: Boolean
}

external interface ListProps : CommonProps {
    var children: Any
    var height: dynamic /* Number | String */
        get() = definedExternally
        set(value) = definedExternally
    var itemCount: Int
    var width: dynamic /* Number | String */
        get() = definedExternally
        set(value) = definedExternally
    var direction: String /* "ltr" | "rtl" | "vertical" | "horizontal" */
    var layout: String /* "vertical" | "horizontal" */
    var initialScrollOffset: Int?
        get() = definedExternally
        set(value) = definedExternally
    var itemKey: ListItemKeySelector?
        get() = definedExternally
        set(value) = definedExternally
    var overscanCount: Int?
        get() = definedExternally
        set(value) = definedExternally
    var onItemsRendered: ((props: ListOnItemsRenderedProps) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var onScroll: ((props: ListOnScrollProps) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$4` {
    var columnIndex: Int
    var rowIndex: Int
    var data: Any
}

external interface GridOnItemsRenderedProps {
    var overscanColumnStartIndex: Int
    var overscanColumnStopIndex: Int
    var overscanRowStartIndex: Int
    var overscanRowStopIndex: Int
    var visibleColumnStartIndex: Int
    var visibleColumnStopIndex: Int
    var visibleRowStartIndex: Int
    var visibleRowStopIndex: Int
}

external interface GridOnScrollProps {
    var horizontalScrollDirection: String /* "forward" | "backward" */
    var scrollLeft: Int
    var scrollTop: Int
    var scrollUpdateWasRequested: Boolean
    var verticalScrollDirection: String /* "forward" | "backward" */
}

external interface GridProps : CommonProps {
    var children: Any
    var columnCount: Int
    var direction: String /* "ltr" | "rtl" */
    var height: Int
    var initialScrollLeft: Int?
        get() = definedExternally
        set(value) = definedExternally
    var initialScrollTop: Int?
        get() = definedExternally
        set(value) = definedExternally
    var itemKey: GridItemKeySelector?
        get() = definedExternally
        set(value) = definedExternally
    var onItemsRendered: ((props: GridOnItemsRenderedProps) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var onScroll: ((props: GridOnScrollProps) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var overscanColumnsCount: Int?
        get() = definedExternally
        set(value) = definedExternally
    var overscanColumnCount: Int?
        get() = definedExternally
        set(value) = definedExternally
    var overscanRowsCount: Int?
        get() = definedExternally
        set(value) = definedExternally
    var overscanRowCount: Int?
        get() = definedExternally
        set(value) = definedExternally
    var overscanCount: Int?
        get() = definedExternally
        set(value) = definedExternally
    var rowCount: Int
    var width: Int
}

external interface FixedSizeListProps : ListProps {
    var itemSize: Int
}

external interface VariableSizeListProps : ListProps {
    var estimatedItemSize: Int?
        get() = definedExternally
        set(value) = definedExternally
    var itemSize: (index: Int) -> Int
}

external interface FixedSizeGridProps : GridProps {
    var columnWidth: Int
    var rowHeight: Int
}

external interface VariableSizeGridProps : GridProps {
    var columnWidth: (index: Int) -> Int
    var estimatedColumnWidth: Int?
        get() = definedExternally
        set(value) = definedExternally
    var estimatedRowHeight: Int?
        get() = definedExternally
        set(value) = definedExternally
    var rowHeight: (index: Int) -> Int
}

open external class FixedSizeList : Component<FixedSizeListProps, RState> {
    open fun scrollTo(scrollOffset: Int)
    open fun scrollToItem(index: Int, align: String = definedExternally)
    open fun scrollToItem(index: Int)
    override fun render(): ReactElement?
}

open external class VariableSizeList : Component<VariableSizeListProps, RState> {
    open fun scrollTo(scrollOffset: Int)
    open fun scrollToItem(index: Int, align: String = definedExternally)
    open fun resetAfterIndex(index: Int, shouldForceUpdate: Boolean)
    open fun scrollToItem(index: Int)
    override fun render(): ReactElement?
}

external interface `T$0` {
    var scrollLeft: Int
    var scrollTop: Int
}

external interface `T$1` {
    var align: String /* "auto" | "smart" | "center" | "end" | "start" */
    var columnIndex: Int?
        get() = definedExternally
        set(value) = definedExternally
    var rowIndex: Int?
        get() = definedExternally
        set(value) = definedExternally
}

open external class FixedSizeGrid : Component<FixedSizeGridProps, RState> {
    open fun scrollTo(params: `T$0`)
    open fun scrollToItem(params: `T$1`)
    override fun render(): ReactElement
}

external interface `T$2` {
    var columnIndex: Int
    var rowIndex: Int
    var shouldForceUpdate: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

open external class VariableSizeGrid : Component<VariableSizeGridProps, RState> {
    open fun scrollTo(params: `T$0`)
    open fun scrollToItem(params: `T$1`)
    open fun resetAfterColumnIndex(index: Int, shouldForceUpdate: Boolean = definedExternally)
    open fun resetAfterIndices(params: `T$2`)
    open fun resetAfterRowIndex(index: Int, shouldForceUpdate: Boolean = definedExternally)
    override fun render(): ReactElement?
}

external fun areEqual(prevProps: RProps, nextProps: RProps): Boolean

external interface `T$3`<P, S> {
    var props: P
    var state: S
}

external fun <P, S> shouldComponentUpdate(self: `T$3`<P, S>, nextProps: RProps, nextState: RProps): Boolean
