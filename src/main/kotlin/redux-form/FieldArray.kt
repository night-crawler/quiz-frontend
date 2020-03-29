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

external interface _BaseFieldArrayProps<P : RProps, FieldValue> : RProps {
    var name: String

    //    var component: Component<WrappedFieldArrayProps<FieldValue>, RState>
//    var component: dynamic
    var component: JsClass<Component<WrappedFieldArrayProps<FieldValue>, RState>>

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

/*
export interface GenericFieldArray<FieldValue = any, P = {}> extends Component<BaseFieldArrayProps<P, FieldValue>> {
    name: string;
    valid: boolean;
    getRenderedComponent(): Component<WrappedFieldArrayProps<FieldValue> & P>;
}
 */

external interface GenericFieldArray<FieldValue, P : RProps> : Component<_BaseFieldArrayProps<P, FieldValue>, RState> {
    var name: String
    var valid: Boolean
    fun getRenderedComponent(): Component<WrappedFieldArrayProps<FieldValue>, RState>
    override fun render(): ReactElement?
}

open external class FieldArray<P : RProps, FieldValue> :
    Component<_BaseFieldArrayProps<P, FieldValue>, RState>,
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
    operator fun get(index: Int): FieldValue
    fun getAll(): Array<FieldValue>
    fun removeAll()
    fun insert(index: Int, value: FieldValue)
    var name: String
    var length: Int
    fun <R> map(callback: FieldIterate<FieldValue, R>): Array<R>
    fun pop(): FieldValue
    fun push(value: FieldValue)
    fun remove(index: Int)
    fun shift(): FieldValue
    fun swap(indexA: Int, indexB: Int)
    fun move(from: Int, to: Int)
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
