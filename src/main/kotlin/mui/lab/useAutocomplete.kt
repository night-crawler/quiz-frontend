@file:JsModule("mui.lab")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package mui.lab

import org.w3c.dom.HTMLElement

external interface CreateFilterOptionsConfig<T> {
    var ignoreAccents: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var ignoreCase: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var matchFrom: String /* 'any' | 'start' */
    var stringify: ((option: T) -> String)?
        get() = definedExternally
        set(value) = definedExternally
    var trim: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var limit: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface FilterOptionsState<T> {
    var inputValue: String
    var getOptionLabel: (option: T) -> String
}

external fun <T> createFilterOptions(config: CreateFilterOptionsConfig<T> = definedExternally): (
    options: Array<T>,
    state: FilterOptionsState<T>
) -> Array<T>

external interface AutocompleteChangeDetails<T> {
    var option: T
}

external interface `T$81`<T> {
    var option: T
    var index: Number
}

external interface `T$82`<T> {
    var getRootProps: () -> Any
    var getInputProps: () -> Any
    var getInputLabelProps: () -> Any
    var getClearProps: () -> Any
    var getPopupIndicatorProps: () -> Any
    var getTagProps: (__0: `T$1`) -> Any
    var getListboxProps: () -> Any
    var getOptionProps: (__0: `T$81`<T>) -> Any
    var id: String
    var inputValue: String
    var value: Any
    var dirty: Boolean
    var popupOpen: Boolean
    var focused: Boolean
    var anchorEl: HTMLElement?
        get() = definedExternally
        set(value) = definedExternally
    var setAnchorEl: () -> Unit
    var focusedTag: Number
    var groupedOptions: Array<T>
}

@JsName("default")
external fun <T> useAutocomplete(props: UseAutocompleteSingleProps<T>): `T$82`<T>

@JsName("default")
external fun <T> useAutocomplete(props: UseAutocompleteMultipleProps<T>): `T$82`<T>
