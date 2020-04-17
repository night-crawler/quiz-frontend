package react.window

import react.RBuilder
import react.RClass
import react.RElementBuilder
import react.RRef
import react.ref
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
