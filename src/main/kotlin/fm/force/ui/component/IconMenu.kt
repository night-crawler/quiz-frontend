package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.menu.MenuOnCloseReason
import com.ccfraser.muirwik.components.menu.mMenu
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
    var items: RBuilder.() -> Unit
}

class IconMenu(props: IconMenuProps) : RComponent<IconMenuProps, RState>(props) {
    private var isOpened = false
    private var anchorElement: Node? = null

    private fun onOpen(e: Event) {
        val anchor = e.currentTarget.asDynamic() as Node
        setState {
            anchorElement = anchor
            isOpened = true
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClose(event: Event, reason: MenuOnCloseReason) = setState { isOpened = false }

    override fun RBuilder.render() {
        styledDiv {
            mIconButton(props.iconName, onClick = ::onOpen)
            mMenu(isOpened, anchorElement = anchorElement, onClose = ::onClose) {
                props.items(this)
            }
        }
    }
}

fun RBuilder.iconMenu(iconName: String, items: RBuilder.() -> Unit) = child(IconMenu::class) {
    attrs {
        this.iconName = iconName
        this.items = items
    }
}
