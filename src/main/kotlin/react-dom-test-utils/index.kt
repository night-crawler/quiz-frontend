@file:JsModule("react-dom/test-utils")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package react.dom.test

import kotlin.js.Date
import kotlin.js.Promise
import org.w3c.dom.DataTransfer
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface OptionalEventProperties {
    var bubbles: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var cancelable: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var currentTarget: EventTarget?
        get() = definedExternally
        set(value) = definedExternally
    var defaultPrevented: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var eventPhase: Number?
        get() = definedExternally
        set(value) = definedExternally
    var isTrusted: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var nativeEvent: Event?
        get() = definedExternally
        set(value) = definedExternally
    val preventDefault: (() -> Unit)?
        get() = definedExternally
    val stopPropagation: (() -> Unit)?
        get() = definedExternally
    var target: EventTarget?
        get() = definedExternally
        set(value) = definedExternally
    var timeStamp: Date?
        get() = definedExternally
        set(value) = definedExternally
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface SyntheticEventData : OptionalEventProperties {
    var altKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var button: Number?
        get() = definedExternally
        set(value) = definedExternally
    var buttons: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clientX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clientY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var changedTouches: Any?
        get() = definedExternally
        set(value) = definedExternally
    var charCode: Number?
        get() = definedExternally
        set(value) = definedExternally
    var clipboardData: DataTransfer?
        get() = definedExternally
        set(value) = definedExternally
    var ctrlKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var deltaMode: Number?
        get() = definedExternally
        set(value) = definedExternally
    var deltaX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var deltaY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var deltaZ: Number?
        get() = definedExternally
        set(value) = definedExternally
    var detail: Number?
        get() = definedExternally
        set(value) = definedExternally
    val getModifierState: ((key: String) -> Boolean)?
        get() = definedExternally
    var key: String?
        get() = definedExternally
        set(value) = definedExternally
    var keyCode: Number?
        get() = definedExternally
        set(value) = definedExternally
    var locale: String?
        get() = definedExternally
        set(value) = definedExternally
    var location: Number?
        get() = definedExternally
        set(value) = definedExternally
    var metaKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var pageX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pageY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var relatedTarget: EventTarget?
        get() = definedExternally
        set(value) = definedExternally
    var repeat: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var screenX: Number?
        get() = definedExternally
        set(value) = definedExternally
    var screenY: Number?
        get() = definedExternally
        set(value) = definedExternally
    var shiftKey: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var targetTouches: Any?
        get() = definedExternally
        set(value) = definedExternally
    var touches: Any?
        get() = definedExternally
        set(value) = definedExternally
    var view: Any?
        get() = definedExternally
        set(value) = definedExternally
    var which: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MockedComponentClass

external interface ShallowRenderer {
    fun <E : ReactElement> getRenderOutput(): E
    fun render(element: ReactElement, context: Any = definedExternally)
    fun unmount()
}

// external fun <T : Element> renderIntoDocument(element: DOMElement<Any, T>): T

// external fun renderIntoDocument(element: SFCElement<Any>)

// external fun <P, T : Component<P, RState>> renderIntoDocument(element: CElement<P, T>): T

external fun renderIntoDocument(element: ReactElement): dynamic /* Component<P> | Element | Unit */

external fun mockComponent(mocked: MockedComponentClass, mockTagName: String = definedExternally): Any

external fun isElement(element: Any): Boolean

external fun <T : HTMLElement> isElementOfType(element: ReactElement, type: String): Boolean

// external fun <P : DOMAttributes<Any>, T : Element> isElementOfType(element: ReactElement, type: String): Boolean

// external fun <P> isElementOfType(element: ReactElement, type: SFC<P>): Boolean

external fun <P> isElementOfType(
    element: ReactElement,
//    type: ClassType<P, T, C>
    type: dynamic
): Boolean

// type ReactInstance = Component<any> | Element;

external fun isDOMComponent(instance: Element): Boolean
external fun <P : RProps> isDOMComponent(instance: Component<out P, RState>): Boolean

external fun isCompositeComponent(instance: Element): Boolean
external fun <P : RProps> isCompositeComponent(instance: Component<out P, RState>): Boolean

// external fun <T : Component<RProps, RState>, C : RClass<Any>> isCompositeComponentWithType(
//    instance: ReactInstance,
//    type: ClassType<Any, T, C>
// ): Boolean

external fun isCompositeComponentWithType(
    instance: Element,
    type: dynamic
): Boolean

external fun <T : Component<out RProps, RState>> isCompositeComponentWithType(
    instance: T,
    type: dynamic
//    type: ClassType<Any, T, C>
): Boolean

external fun findAllInRenderedTree(root: Component<RProps, RState>, fn: (i: Element) -> Boolean): Array<Element>
// external fun findAllInRenderedTree(root: Component<RProps, RState>, fn: (i: ReactInstance) -> Boolean): Array<ReactInstance>

external fun scryRenderedDOMComponentsWithClass(root: Component<RProps, RState>, className: String): Array<Element>

external fun findRenderedDOMComponentWithClass(root: Component<RProps, RState>, className: String): Element

external fun scryRenderedDOMComponentsWithTag(root: Component<RProps, RState>, tagName: String): Array<Element>

external fun findRenderedDOMComponentWithTag(root: Component<RProps, RState>, tagName: String): Element

// external fun <T : Component<RProps, RState>, C : ComponentClass<Any>> scryRenderedComponentsWithType(
//    root: Component<RProps, RState>,
//    type: ClassType<Any, T, C>
// ): Array<T>

// external fun <T : Component<RProps, RState>, C : RClass<Any>> findRenderedComponentWithType(
//    root: Component<Any>,
//    type: ClassType<Any, T, C>
// ): T

external fun createRenderer(): ShallowRenderer

external fun act(callback: () -> Unit?)

external fun act(callback: () -> Promise<Unit?>): Promise<Nothing?>

external interface DebugPromiseLike {
    fun then(onfulfilled: (value: Any) -> Any, onrejected: (reason: Any) -> Any): Any
}

external fun <T : HTMLElement> isElementOfType(element: ReactElement, type: String): Boolean
