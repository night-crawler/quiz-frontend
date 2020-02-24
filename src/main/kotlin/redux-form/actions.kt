@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)
package redux.form

import react.RProps
import redux.Action

external interface FormAction : Action {
    var meta: Any?
        get() = definedExternally
        set(value) = definedExternally
    var payload: Any?
        get() = definedExternally
        set(value) = definedExternally
    var error: Any?
        get() = definedExternally
        set(value) = definedExternally
}

external fun arrayInsert(form: String, field: String, index: Number, value: Any): FormAction

external fun arrayMove(form: String, field: String, from: Number, to: Number): FormAction

external fun arrayPop(form: String, field: String): FormAction

external fun arrayPush(form: String, field: String, value: Any): FormAction

external fun arrayRemove(form: String, field: String, index: Number): FormAction

external fun arrayRemoveAll(form: String, field: String): FormAction

external fun arrayShift(form: String, field: String): FormAction

external fun arraySplice(form: String, field: String, index: Number, removeNum: Number, value: Any): FormAction

external fun arraySwap(form: String, field: String, indexA: Number, indexB: Number): FormAction

external fun arrayUnshift(form: String, field: String, value: Any): FormAction

external fun autofill(form: String, field: String, value: Any): FormAction

external fun blur(form: String, field: String, value: Any, touch: Boolean = definedExternally): FormAction

external fun change(
    form: String,
    field: String,
    value: Any,
    touch: Boolean = definedExternally,
    persistentSubmitErrors: Boolean = definedExternally
): FormAction

external fun destroy(vararg form: String): FormAction

external fun focus(form: String, field: String): FormAction

external interface InitializeOptions {
    var keepDirty: Boolean
    var keepSubmitSucceeded: Boolean
    var updateUnregisteredFields: Boolean
    var keepValues: Boolean
}

external interface InitializeOptionsPartial {
    var keepDirty: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var keepSubmitSucceeded: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var updateUnregisteredFields: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var keepValues: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun initialize(
    form: String,
    data: Any,
    keepDirty: Boolean = definedExternally,
    options: InitializeOptionsPartial = definedExternally
): FormAction


external fun initialize(form: String, data: Any, options: InitializeOptionsPartial = definedExternally): FormAction

external fun <P: RProps>registerField(form: String, name: String, type: Field/*<P>*/): FormAction
external fun <P: RProps, FT>registerField(form: String, name: String, type: FieldArray<P, FT>): FormAction

external fun reset(form: String): FormAction

external fun resetSection(form: String, vararg sections: String): FormAction

external fun startAsyncValidation(form: String): FormAction

/*
export interface ErrorOther<T = string> {
    _error?: T;
}

export type FormErrors<FormData = {}, T = string> = {
    [P in keyof FormData]?: ReactElement | T;
} & ErrorOther<T>;
 */

external fun stopAsyncValidation(form: String, errors: dynamic = definedExternally): FormAction

external fun setSubmitFailed(form: String, vararg fields: String): FormAction

external fun setSubmitSucceeded(form: String, vararg fields: String): FormAction

external fun startSubmit(form: String): FormAction

external fun stopSubmit(form: String, errors: dynamic = definedExternally): FormAction

external fun submit(form: String): FormAction

external fun clearSubmit(form: String): FormAction

external fun clearSubmitErrors(form: String): FormAction

external fun clearAsyncError(form: String, field: String): FormAction

external fun clearFields(
    form: String,
    keepTouched: Boolean,
    persistentSubmitErrors: Boolean,
    vararg fields: String
): FormAction

external fun touch(form: String, vararg fields: String): FormAction

external fun unregisterField(form: String, name: String): FormAction

external fun untouch(form: String, vararg fields: String): FormAction

external fun <T> updateSyncErrors(from: String, syncErrors: dynamic, error: T): FormAction

external fun <T> updateSyncWarnings(form: String, syncWarnings: FormWarnings<Any, T>, warning: T): FormAction
