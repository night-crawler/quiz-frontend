package mui.lab

interface UseAutocompleteSingleProps<T> : UseAutocompleteCommonProps<T> {
    var defaultValue: T?
    var multiple: Boolean?
    var value: T?
    var onChange: ((event: ChangeEvent<Any>, value: T? /* = null */, reason: String /* 'create-option' | 'select-option' | 'remove-option' | 'clear' | 'blur' */, details: AutocompleteChangeDetails<T>) -> Unit)?
}
