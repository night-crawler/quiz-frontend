@file:JsModule("history")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package history

import org.w3c.dom.events.EventListener
import org.w3c.dom.events.EventTarget

external var isExtraneousPopstateEvent: Boolean

external fun addEventListener(node: EventTarget, event: String, listener: EventListener)

external fun addEventListener(node: EventTarget, event: String, listener: EventListener)

external fun removeEventListener(node: EventTarget, event: String, listener: EventListener)

external fun removeEventListener(node: EventTarget, event: String, listener: EventListener)

external fun getConfirmation(message: String, callback: (result: Boolean) -> Unit)

external fun supportsHistory(): Boolean

external fun supportsGoWithoutReloadUsingHash(): Boolean
