package fm.force.ui.component

import com.benasher44.uuid.uuid4
import fm.force.quiz.common.dto.DTOMarker
import react.RBuilder
import react.RClass
import react.RRef
import react.virtualized.auto.sizer.autoSizer
import react.window.VariableSizeList
import react.window.infinite.loader.OnItemsRendered
import react.window.infinite.loader.infiniteLoader
import react.window.variableSizeList

fun <T : DTOMarker, C : AbstractPageContext<T>> RBuilder.infinitePaginator(
    rowComponent: RClass<*>,
    paginator: C,
    offsetTop: Int,
    searchText: String,
    sort: String,
    forceUpdate: () -> Unit,
    uniqueKey: String
) {
    autoSizer { size ->
        infiniteLoader(
            isItemLoaded = paginator::isItemLoaded,
            loadMoreItems = { startIndex: Int, stopIndex: Int ->
                paginator.loadMoreRows(searchText, sort, startIndex, stopIndex)
            },
            itemCount = paginator.totalElements.toInt()
        ) { onItemsRendered: OnItemsRendered, ref: RRef ->
            variableSizeList(
                ref = ref,
                onItemsRendered = onItemsRendered,
                rowComponent = rowComponent,
                height = size.height - offsetTop,
                width = size.width,
                itemCount = paginator.totalElements.toInt(),
                itemSize = paginator::getEffectiveHeight
            ) {
                key = uniqueKey
                this.ref {
                    if (it != null) {
                        val prev = paginator.infiniteListRef
                        paginator.infiniteListRef = it.unsafeCast<VariableSizeList>()
                        if (prev !== it)
                            forceUpdate()
                    }
                }
            }
        }
    }
}
