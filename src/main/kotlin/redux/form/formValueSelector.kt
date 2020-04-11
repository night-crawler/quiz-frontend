@file:JsModule("redux-form")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)

package redux.form

external fun <State> formValueSelector(
    form: String,
    getFormState: (state: State) -> FormStateMap = definedExternally
): (state: State, field: String) -> Any
