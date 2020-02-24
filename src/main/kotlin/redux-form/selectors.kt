@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

external var getFormValues: DataSelector<Any, Any>

external var getFormInitialValues: DataSelector<Any, Any>

external var getFormSyncErrors: ErrorSelector<Any, Any, Any>

external var getFormMeta: DataSelector<Any, Any>

external var getFormAsyncErrors: ErrorSelector<Any, Any, Any>

external var getFormSyncWarnings: ErrorSelector<Any, Any, Any>

external var getFormSubmitErrors: ErrorSelector<Any, Any, Any>

external var getFormError: ErrorSelector<Any, Any, Any>

external var getFormNames: NamesSelector<Any>

external var isDirty: FormOrFieldsBooleanSelector<Any>

external var isPristine: FormOrFieldsBooleanSelector<Any>

external var isValid: BooleanSelector<Any>

external var isInvalid: BooleanSelector<Any>

external var isSubmitting: BooleanSelector<Any>

external var isAsyncValidating: BooleanSelector<Any>

external var hasSubmitSucceeded: BooleanSelector<Any>

external var hasSubmitFailed: BooleanSelector<Any>
