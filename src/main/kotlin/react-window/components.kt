package react.window

import kotlin.browser.window
import react.*
import react.window.infinite.loader.OnItemsRendered

fun RBuilder.fixedSizeList(
    ref: RRef,
    onItemsRendered: OnItemsRendered,
    rowComponent: RClass<*>,
    height: dynamic,
    width: dynamic,
    itemCount: Int,
    itemSize: Int,
    handler: (RElementBuilder<FixedSizeListProps>.() -> Unit)? = null
) = child(FixedSizeList::class) {
    attrs {
        this.height = height
        this.width = width
        this.itemCount = itemCount
        this.itemSize = itemSize
        children = rowComponent
        this.onItemsRendered = onItemsRendered
        this.ref = ref
    }
    handler?.invoke(this)
}

fun RBuilder.variableSizeList(
    ref: RRef,
    onItemsRendered: OnItemsRendered,
    rowComponent: RClass<*>,
    height: dynamic,
    width: dynamic,
    itemCount: Int,
    itemSize: (index: Int) -> Int,
    handler: (RElementBuilder<VariableSizeListProps>.() -> Unit)? = null
) = child(VariableSizeList::class) {
    attrs {
        this.height = height
        this.width = width
        this.itemCount = itemCount
        this.itemSize = itemSize
        children = rowComponent
        this.onItemsRendered = onItemsRendered
        this.ref = ref
    }
    handler?.invoke(this)
}

fun RBuilder.variableSizeList(
    ref: RRef,
    onItemsRendered: OnItemsRendered,
    getRow: (ListChildComponentProps) -> ReactElement,
    height: dynamic,
    width: dynamic,
    itemCount: Int,
    itemSize: (index: Int) -> Int,
    handler: (RElementBuilder<VariableSizeListProps>.() -> Unit)? = null
) = child(VariableSizeList::class) {
    attrs {
        this.height = height
        this.width = width
        this.itemCount = itemCount
        this.itemSize = itemSize
        children = getRow
        this.onItemsRendered = onItemsRendered
        this.ref = ref
    }
    handler?.invoke(this)
}
