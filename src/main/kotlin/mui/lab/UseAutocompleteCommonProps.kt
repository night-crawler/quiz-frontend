package mui.lab

import react.RProps

interface UseAutocompleteCommonProps<T> : RProps {
    var autoComplete: Boolean?
    var autoHighlight: Boolean?
    var autoSelect: Boolean?
    var blurOnSelect: dynamic /* 'touch' | 'mouse' | Boolean */
    var clearOnEscape: Boolean?
    var componentName: String?
    var debug: Boolean?
    var disableClearable: Boolean?
    var disableCloseOnSelect: Boolean?
    var disabledItemsFocusable: Boolean?
    var disableListWrap: Boolean?
    var filterOptions: ((options: Array<T>, state: FilterOptionsState<T>) -> Array<T>)?
    var filterSelectedOptions: Boolean?
    var freeSolo: Boolean?
    var getOptionDisabled: ((option: T) -> Boolean)?
    var getOptionLabel: ((option: T) -> String)?
    var getOptionSelected: ((option: T, value: T) -> Boolean)?
    var groupBy: ((option: T) -> String)?
    var id: String
    var includeInputInList: Boolean?
    var inputValue: String?
    var onClose: ((event: ChangeEvent<Any>, reason: String /* 'toggleInput' | 'escape' | 'select-option' | 'blur' */) -> Unit)?
    var onInputChange: ((event: ChangeEvent<Any>, value: String, reason: String /* 'input' | 'reset' | 'clear' */) -> Unit)?
    var onOpen: ((event: ChangeEvent<Any>) -> Unit)?
    var open: Boolean?
    var openOnFocus: Boolean?
    var options: Array<T>
    var selectOnFocus: Boolean?
}
