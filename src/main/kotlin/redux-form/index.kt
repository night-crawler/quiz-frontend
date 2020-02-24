@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

external interface ErrorOther<T> {
    var _error: T?
        get() = definedExternally
        set(value) = definedExternally
}

external interface WarningOther<T> {
    var _warning: T?
        get() = definedExternally
        set(value) = definedExternally
}

external interface RegisteredFieldState {
    var name: String
    var type: String /* "Field" | "FieldArray" */
}
