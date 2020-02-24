@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface _BaseFieldArrayProps<P, FieldValue> : RProps {
    var name: String
    var component: Any
    var validate: dynamic /* Validator | Array<Validator> */
        get() = definedExternally
        set(value) = definedExternally
    var warn: dynamic /* Validator | Array<Validator> */
        get() = definedExternally
        set(value) = definedExternally
    var withRef: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var rerenderOnEveryChange: Boolean?
        get() = definedExternally
        set(value) = definedExternally

    var props: P
}

external interface `T$2`<P> {
    var props: P
}

/*
export interface GenericFieldArray<FieldValue = any, P = {}> extends Component<BaseFieldArrayProps<P, FieldValue>> {
    name: string;
    valid: boolean;
    getRenderedComponent(): Component<WrappedFieldArrayProps<FieldValue> & P>;
}
 */

external interface GenericFieldArray<FieldValue, P> : Component<dynamic, RState> {
    var name: String
    var valid: Boolean
    fun getRenderedComponent(): Component<WrappedFieldArrayProps<FieldValue>, RState>
    override fun render(): ReactElement?
}

external open class FieldArray<P, FieldValue> : Component<dynamic, RState>,
    GenericFieldArray<FieldValue, P> {
    override var name: String
    override var valid: Boolean
    override fun getRenderedComponent(): Component<WrappedFieldArrayProps<FieldValue>, RState>
    override fun render(): ReactElement?
}

external interface WrappedFieldArrayProps<FieldValue> : RProps {
    var fields: FieldArrayFieldsProps<FieldValue>
    var meta: FieldArrayMetaProps
}

external interface FieldArrayFieldsProps<FieldValue> {
    fun forEach(callback: FieldIterate<FieldValue, dynamic>)
    fun get(index: Number): FieldValue
    fun getAll(): Array<FieldValue>
    fun removeAll()
    fun insert(index: Number, value: FieldValue)
    var name: String
    var length: Number
    fun <R> map(callback: FieldIterate<FieldValue, R>): Array<R>
    fun pop(): FieldValue
    fun push(value: FieldValue)
    fun remove(index: Number)
    fun shift(): FieldValue
    fun swap(indexA: Number, indexB: Number)
    fun move(from: Number, to: Number)
    fun unshift(value: FieldValue)
}

external interface FieldArrayMetaProps {
    var dirty: Boolean
    var error: Any?
        get() = definedExternally
        set(value) = definedExternally
    var form: String
    var invalid: Boolean
    var pristine: Boolean
    var submitFailed: Boolean
    var submitting: Boolean
    var valid: Boolean
    var warning: Any?
        get() = definedExternally
        set(value) = definedExternally
}
