package fm.force.ui.component.main

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.createStyled
import com.ccfraser.muirwik.components.menu.MMenuProps
import com.ccfraser.muirwik.components.menu.MenuOnCloseReason
import com.ccfraser.muirwik.components.menu.mMenu
import fm.force.ui.util.treeIterator
import kotlinx.html.onClick
import org.w3c.dom.Node
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import react.RBuilder
import react.RComponent
import react.RState
import react.setState
import styled.StyledHandler
import styled.styledDiv

interface IconMenuProps : StyledPropsWithCommonAttributes {
    var iconName: String
    var shouldClose: ((eventTarget: EventTarget?) -> Boolean)?
    var handler: StyledHandler<out MMenuProps>
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
        val eventTarget = event.target
        if (eventTarget !in negativeMatches) {
            if (shouldClose(eventTarget)) {
                close()
            }
        }
    }

    private fun shouldClose(eventTarget: EventTarget?): Boolean {
        val cb = props.shouldClose
        return if (cb != null) cb(eventTarget) else true
    }

    private fun close() = setState { isOpened = false }
    private fun open() = setState { isOpened = true }

    private fun handleOpen(
        @Suppress("UNUSED_PARAMETER")
        event: Event
    ) = open()

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

fun RBuilder.iconMenu(
    iconName: String,
    shouldClose: ((eventTarget: EventTarget?) -> Boolean)? = null,
    handler: StyledHandler<out MMenuProps>
) = createStyled(IconMenu::class) {
    attrs {
        this.iconName = iconName
        this.handler = handler
        this.shouldClose = shouldClose
    }
}
