@file:Suppress("PackageDirectoryMismatch")

package redux.form

import org.w3c.dom.events.Event

typealias Normalizer = (value: Any, previousValue: Any, allValues: Any, previousAllValues: Any) -> Any

typealias Formatter = (value: Any, name: String) -> Any

typealias Parser = (value: Any, name: String) -> Any

typealias Validator = (value: Any, allValues: Any, props: Any, name: Any) -> Any

typealias EventHandler<Event> = (event: Event, name: String) -> Unit

typealias EventWithDataHandler<Event> = (event: Event, newValue: Any, previousValue: Any, name: String) -> Unit

typealias FieldIterate<FieldValue, R> = (name: String, index: Number, fields: FieldArrayFieldsProps<FieldValue>) -> R

typealias FormWarnings<FormData, T> = Any

typealias Omit<T, K> = Any

typealias Dispatch<A, R> = (A) -> R

typealias FormSubmitHandler<FormData, P, ErrorType> = (values: FormData, dispatch: Dispatch<Any, Any>, props: P) -> dynamic

typealias SyntheticEvent<T> = Event

typealias GetFormState = (state: Any) -> FormStateMap

typealias FormDecorator<FormData, P, Config, ErrorType> = (
//    component: ComponentType<P /* P & InjectedFormProps<FormData, P, ErrorType> */>
    component: Any
) -> DecoratedComponentClass<FormData, P /* P & Config */, ErrorType>

typealias DataSelector<FormData, State> = (formName: String, getFormState: GetFormState) -> (state: State) -> FormData

typealias ErrorSelector<FormData, State, ErrorType> = (formName: String, getFormState: GetFormState) -> (state: State) -> Any

typealias BooleanSelector<State> = (formName: String, getFormState: GetFormState) -> (state: State) -> Boolean

typealias NamesSelector<State> = (getFormState: GetFormState) -> (state: State) -> Array<String>

typealias FormOrFieldsBooleanSelector<State> = (formName: String, getFormState: GetFormState) -> (
    state: State,
    fields: String
) -> Boolean

open class SubmissionError(val errors: dynamic) : Exception()
