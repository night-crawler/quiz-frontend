@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

import kotlin.js.Promise
import react.Component
import react.RClass
import react.RProps
import react.RState

external interface SubmitHandler<FormData, P, ErrorType> {
    @nativeInvoke
    operator fun invoke(
        submit: FormSubmitHandler<FormData, P, ErrorType>,
        props: InjectedFormProps<FormData, P, ErrorType> = definedExternally,
        valid: Boolean = definedExternally,
        asyncValidate: Any = definedExternally,
        fields: Array<String> = definedExternally
    ): Any

    @nativeInvoke
    operator fun invoke(event: SyntheticEvent<Any>)
}

external interface ValidateCallback<FormData, P, ErrorType> {
    var values: FormData
    var nextProps: P /* P & InjectedFormProps<FormData, P, ErrorType> */
    var props: P /* P & InjectedFormProps<FormData, P, ErrorType> */
    var initialRender: Boolean
    var lastFieldValidatorKeys: Array<String>
    var fieldValidatorKeys: Array<String>
    var structure: Any
}

external interface AsyncValidateCallback<FormData, ErrorType> {
    var asyncErrors: Any?
        get() = definedExternally
        set(value) = definedExternally
    var initialized: Boolean
    var trigger: String /* "blur" | "submit" */
    var blurredField: String?
        get() = definedExternally
        set(value) = definedExternally
    var pristine: Boolean
    var syncValidationPasses: Boolean
}

external interface InjectedArrayProps {
    fun insert(field: String, index: Int, value: Any)
    fun move(field: String, from: Int, to: Int)
    fun pop(field: String)
    fun push(field: String, value: Any)
    fun remove(field: String, index: Int)
    fun removeAll(field: String)
    fun shift(field: String)
    fun splice(field: String, index: Int, removeNum: Int, value: Any)
    fun swap(field: String, indexA: Int, indexB: Int)
    fun unshift(field: String, value: Any)
}

external interface RegisteredField {
    var count: Int
    var name: String
    var type: String /* "Field" | "FieldArray" */
}

external interface `T$0` {
    @nativeGetter
    operator fun get(name: String): RegisteredField?

    @nativeSetter
    operator fun set(name: String, value: RegisteredField)
}

external interface InjectedFormProps<FormData, P, ErrorType> : RProps {
    var anyTouched: Boolean
    var array: InjectedArrayProps
    fun asyncValidate()
    var asyncValidating: dynamic /* String | Boolean */
        get() = definedExternally
        set(value) = definedExternally

    fun autofill(field: String, value: Any)
    fun blur(field: String, value: Any)
    fun change(field: String, value: Any)
    fun clearAsyncError(field: String)
    fun destroy()
    var dirty: Boolean
    var error: ErrorType
    var form: String
    var handleSubmit: SubmitHandler<FormData, P, ErrorType>
    fun initialize(data: FormData)
    var initialized: Boolean
    var initialValues: Any
    var invalid: Boolean
    var pristine: Boolean
    fun reset()
    var submitFailed: Boolean
    var submitSucceeded: Boolean
    var submitting: Boolean
    fun touch(vararg field: String)
    fun untouch(vararg field: String)
    var valid: Boolean
    var warning: Any
    var registeredFields: `T$0`
}

external interface FormInstance<FormData, P, ErrorType> : Component<P, RState> where P : RProps {
    var dirty: Boolean
    var invalid: Boolean
    var pristine: Boolean
    var registeredFields: Array<RegisteredFieldState>
    fun reset()
    fun resetSection(vararg sections: String)
    fun submit(): Promise<Any>
    var valid: Boolean
    var values: Any
    var wrappedInstance: Any
}

external interface DecoratedComponentClass<FormData, P : RProps, ErrorType> : RClass<P>

external interface ConfigProps<FormData, P, ErrorType> {
    var form: String?
        get() = definedExternally
        set(value) = definedExternally
    var asyncBlurFields: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var asyncChangeFields: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var asyncValidate: (
        (
            values: FormData,
            dispatch: Dispatch<Any, Any>,
            props: P /* P & InjectedFormProps<FormData, P, ErrorType> */,
            blurredField: String
        ) -> Promise<Any>
    )?
        get() = definedExternally
        set(value) = definedExternally
    var destroyOnUnmount: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var enableReinitialize: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var forceUnregisterOnUnmount: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var getFormState: GetFormState?
        get() = definedExternally
        set(value) = definedExternally
    var immutableProps: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var initialValues: Any?
        get() = definedExternally
        set(value) = definedExternally
    var keepDirtyOnReinitialize: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var updateUnregisteredFields: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var onChange: (
        (
            values: FormData,
            dispatch: Dispatch<Any, Any>,
            props: P /* P & InjectedFormProps<FormData, P, ErrorType> */,
            previousValues: FormData
        ) -> Unit
    )?
        get() = definedExternally
        set(value) = definedExternally

    //    var onSubmit: dynamic /* FormSubmitHandler<FormData, P /* P & InjectedFormProps<FormData, P, ErrorType> */, ErrorType> | SubmitHandler<FormData, P /* P & InjectedFormProps<FormData, P, ErrorType> */, ErrorType> */
    var onSubmit: FormSubmitHandler<FormData, P /* P & InjectedFormProps<FormData, P, ErrorType> */, ErrorType>?
        get() = definedExternally
        set(value) = definedExternally
    var onSubmitFail: (
        (
//        errors: FormErrors<FormData, ErrorType>?,
            errors: dynamic,
            dispatch: Dispatch<Any, Any>,
            submitError: Any,
            props: P /* P & InjectedFormProps<FormData, P, ErrorType> */
        ) -> Unit
    )?
        get() = definedExternally
        set(value) = definedExternally
    var onSubmitSuccess: (
        (
            result: Any,
            dispatch: Dispatch<Any, Any>,
            props: P /* P & InjectedFormProps<FormData, P, ErrorType> */
        ) -> Unit
    )?
        get() = definedExternally
        set(value) = definedExternally
    var propNamespace: String?
        get() = definedExternally
        set(value) = definedExternally
    var pure: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var shouldValidate: ((params: ValidateCallback<FormData, P, ErrorType>) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var shouldError: ((params: ValidateCallback<FormData, P, ErrorType>) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var shouldWarn: ((params: ValidateCallback<FormData, P, ErrorType>) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var shouldAsyncValidate: ((params: AsyncValidateCallback<FormData, ErrorType>) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var submitAsSideEffect: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var touchOnBlur: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var touchOnChange: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var persistentSubmitErrors: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var validate: (
        (
            values: FormData,
            props: P /* P & InjectedFormProps<FormData, P, ErrorType> */
//    ) -> FormErrors<FormData, ErrorType>)?
        ) -> dynamic
    )?
        get() = definedExternally
        set(value) = definedExternally
    var warn: (
        (
            values: FormData,
            props: P /* P & InjectedFormProps<FormData, P, ErrorType> */
        ) -> FormWarnings<FormData, Any>
    )?
        get() = definedExternally
        set(value) = definedExternally
}

external fun <FormData, P : RProps, ErrorType> reduxForm(
    config: ConfigProps<FormData, P, ErrorType>
): FormDecorator<FormData, P, ConfigProps<FormData, P, ErrorType>, ErrorType>
