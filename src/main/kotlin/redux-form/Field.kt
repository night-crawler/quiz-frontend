@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

import org.w3c.dom.DragEvent
import org.w3c.dom.events.FocusEvent
import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface EventOrValueHandler<Event> /*: EventHandler<Event> */ {
    @nativeInvoke
    operator fun invoke(value: Any)
}

external interface CommonFieldInputProps : RProps {
    var name: String
    var onDragStart: EventHandler<DragEvent>
    var onDrop: EventHandler<DragEvent>
    var onFocus: EventHandler<FocusEvent>
}

external interface CommonFieldProps : CommonFieldInputProps {
    var onBlur: EventWithDataHandler<FocusEvent>
    var onChange: EventWithDataHandler<Any>
}

external interface BaseFieldProps<P> : CommonFieldProps {
    override var name: String
    var component: dynamic /* ComponentType<WrappedFieldProps /* WrappedFieldProps & P */> | "input" | "select" | "textarea" */
        get() = definedExternally
        set(value) = definedExternally
    var format: Formatter?
        get() = definedExternally
        set(value) = definedExternally
    var normalize: Normalizer?
        get() = definedExternally
        set(value) = definedExternally
    var props: P?
        get() = definedExternally
        set(value) = definedExternally
    var parse: Parser?
        get() = definedExternally
        set(value) = definedExternally
    var validate: dynamic /* Validator | Array<Validator> */
        get() = definedExternally
        set(value) = definedExternally
    var warn: dynamic /* Validator | Array<Validator> */
        get() = definedExternally
        set(value) = definedExternally
    var forwardRef: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var immutableProps: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
}

external interface GenericField<P> : Component<BaseFieldProps<P>, RState /* BaseFieldProps<P> & P */> {
    var dirty: Boolean
    var name: String
    var pristine: Boolean
    var value: Any
    fun getRenderedComponent(): Component<WrappedFieldProps, RState /* WrappedFieldProps & P */>
}

open external class Field : Component<BaseFieldProps<dynamic>, RState> {
    open var dirty: Boolean
    open var name: String
    open var pristine: Boolean
    open var value: dynamic
    open fun getRenderedComponent(): Component<WrappedFieldProps, RState /* WrappedFieldProps & P */>
    override fun render(): ReactElement?
}

external interface WrappedFieldProps : RProps {
    var input: WrappedFieldInputProps
    var meta: WrappedFieldMetaProps
}

external interface WrappedFieldInputProps : CommonFieldInputProps {
    var checked: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var value: Any
    //    var onBlur: EventOrValueHandler<FocusEvent>
//    var onChange: EventOrValueHandler<Any>
    var onBlur: dynamic
    var onChange: dynamic
}

external interface WrappedFieldMetaProps {
    var active: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var autofilled: Boolean
    var asyncValidating: Boolean
    var dirty: Boolean
    var dispatch: Any
    var error: Any?
        get() = definedExternally
        set(value) = definedExternally
    var form: String
    var initial: Any
    var invalid: Boolean
    var pristine: Boolean
    var submitting: Boolean
    var submitFailed: Boolean
    var touched: Boolean
    var valid: Boolean
    var visited: Boolean
    var warning: Any?
        get() = definedExternally
        set(value) = definedExternally
}
