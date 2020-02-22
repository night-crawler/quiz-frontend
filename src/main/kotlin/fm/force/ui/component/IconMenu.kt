package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.menu.MenuOnCloseReason
import com.ccfraser.muirwik.components.menu.mMenu
import fm.force.util.treeIterator
import kotlinx.html.onClick
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.setState
import styled.styledDiv

interface IconMenuProps : RProps {
    var iconName: String
    var handler: RBuilder.() -> Unit
}


class IconMenu(props: IconMenuProps) : RComponent<IconMenuProps, RState>(props) {
    private var isOpened = false
    private var anchorElement: Node? = null

    /**
     * By default, item click does not close the menu. Passing callbacks in [IconMenuProps.handler] is cumbersome.
     * We don't want to close menu if we click the anchor. All other cases must result in closing.
     *
     * @param event - Synthetic Event instance
     */
    private fun handleItemClick(event: Event) {
        val anchor = anchorElement!!
        val negativeMatches = setOf(anchor) + anchor.treeIterator().asSequence().toSet()
        if (event.target !in negativeMatches) {
            close()
        }
    }

    private fun close() = setState { isOpened = false }
    private fun open() = setState() { isOpened = true }

    private fun handleOpen(@Suppress("UNUSED_PARAMETER") event: Event) = open()
    @Suppress("UNUSED_PARAMETER")
    private fun handleClose(event: Event, reason: MenuOnCloseReason) = close()

    override fun RBuilder.render() {
        styledDiv {
            @Suppress("UnsafeCastFromDynamic")
            this.attrs.onClick = ::handleItemClick.asDynamic()

            mIconButton(props.iconName, onClick = ::handleOpen) {
                ref { anchorElement = it.unsafeCast<Node>() }
            }
            mMenu(isOpened, anchorElement = anchorElement, onClose = ::handleClose) {
                props.handler(this)
            }
        }
    }
}

@ExperimentalStdlibApi
fun RBuilder.iconMenu(iconName: String, handler: RBuilder.() -> Unit) = child(IconMenu::class) {
    attrs {
        this.iconName = iconName
        this.handler = handler
    }
}
