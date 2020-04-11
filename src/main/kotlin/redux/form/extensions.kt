package redux.form

// each search request triggers rendering of the underlying autocomplete component;
// default initial value for field is empty string, which must be replaced with []
// if this array reference changes, it causes full rerender of the component and it
// leads to unusable inputText component
val SENTINEL = js("[]")

fun <T> WrappedFieldInputProps.getArrayValue(): Array<T> {
    if (this.value == "") {
        return SENTINEL.unsafeCast<Array<T>>()
    }
    return this.value.unsafeCast<Array<T>>()
}
