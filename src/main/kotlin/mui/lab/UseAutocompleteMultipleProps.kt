package mui.lab

interface UseAutocompleteMultipleProps<T> : UseAutocompleteCommonProps<T> {
    var defaultValue: Array<T>?
    var multiple: Boolean
    var value: Array<T>?
    var onChange: ((event: ChangeEvent<Any>, value: Array<T>, reason: String /* 'create-option' | 'select-option' | 'remove-option' | 'clear' | 'blur' */, details: AutocompleteChangeDetails<T>) -> Unit)?
}
