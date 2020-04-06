@file:JsModule("@material-ui/lab/Autocomplete")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package mui.lab

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import react.Component
import react.FunctionalComponent
import react.RState
import react.ReactElement

external interface BaseSyntheticEvent<E, C, T> {
    var nativeEvent: E
    var currentTarget: C
    var target: T
    var bubbles: Boolean
    var cancelable: Boolean
    var defaultPrevented: Boolean
    var eventPhase: Number
    var isTrusted: Boolean
    fun preventDefault()
    fun isDefaultPrevented(): Boolean
    fun stopPropagation()
    fun isPropagationStopped(): Boolean
    fun persist()
    var timeStamp: Number
    var type: String
}

external interface SyntheticEvent<T, E> :
    BaseSyntheticEvent<E, EventTarget /* EventTarget & T */, EventTarget>

external interface ChangeEvent<T> : SyntheticEvent<T, Event> {
    override var target: EventTarget /* EventTarget & T */
}

external interface RenderOptionState {
    var inputValue: String
    var selected: Boolean
}

external interface `T$1` {
    var index: Number
}

external interface RenderGroupParams {
    var key: String
    var children: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$0` {
    var ref: dynamic /* RefCallback<Any> | RefObject<Any> | Nothing? */
        get() = definedExternally
        set(value) = definedExternally
    var className: String
    var startAdornment: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
        get() = definedExternally
        set(value) = definedExternally
    var endAdornment: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
        get() = definedExternally
        set(value) = definedExternally
}

external interface RenderInputParams {
    var id: String
    var disabled: Boolean
    var InputLabelProps: Any?
        get() = definedExternally
        set(value) = definedExternally
    var InputProps: `T$0`
    var inputProps: dynamic
        get() = definedExternally
        set(value) = definedExternally
}

@JsName("default")
external var StubAutocomplete: dynamic

@JsName("default")
external class Autocomplete<T> : Component<AutocompleteProps<T>, RState> {
    override fun render(): ReactElement?
}
