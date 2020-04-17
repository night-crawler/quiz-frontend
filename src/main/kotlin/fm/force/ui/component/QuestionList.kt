package fm.force.ui.component

import com.ccfraser.muirwik.components.list.mList
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.span
import react.functionalComponent
import react.rClass
import react.window.fixedSizeList
import react.window.infinite.loader.InfiniteLoader
import react.window.infinite.loader.OnItemsRendered
import react.window.infinite.loader.infiniteLoader
import styled.styledDiv

interface RowProps : RProps {
    var index: Int
    var style: dynamic
}

class Row(props: RowProps) : RComponent<RowProps, RState>(props) {
    override fun RBuilder.render() {
        console.log(props.index, props.style)
        styledDiv {
            attrs {
                style = props.style
            }
            + "AZAZA ${props.index}"
        }
    }
}

class QuestionList(props: RProps) : RComponent<RProps, RState>(props) {
    override fun RBuilder.render() {
        infiniteLoader(
            isItemLoaded = { true },
            loadMoreItems = { startIndex: Int, stopIndex: Int -> null },
            itemCount = 1000
        ) { onItemsRendered: OnItemsRendered, ref ->
            fixedSizeList(
                ref = ref,
                onItemsRendered = onItemsRendered,
                rowComponent = Row::class.rClass,
                height = 500,
                width = "100%",
                itemCount = 1000,
                itemSize = 400
            ) {
                attrs {
                    itemSize = 10
                }
            }
        }
    }
}

fun RBuilder.questionList() = child(QuestionList::class) {}
