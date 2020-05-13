package fm.force.ui.container

import fm.force.ui.component.field.ReduxAutocomplete
import fm.force.ui.component.field.ReduxAutocompleteProps
import fm.force.ui.reducer.state.QuizState
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

interface ConnectedReduxAutocompleteProps : RProps {
    var label: String
}

private interface ReduxAutocompleteStateProps<T> : RProps {
    var options: MutableList<T>
    var label: String
}

private interface ReduxAutocompleteDispatchProps : RProps

fun <T> createReduxAutocompleteComponent() =
    rConnect<
        QuizState, RAction, WrapperAction,
        ConnectedReduxAutocompleteProps,
        ReduxAutocompleteStateProps<T>,
        ReduxAutocompleteDispatchProps,
        ReduxAutocompleteProps<T>>(
        { state, props ->
            label = props.label
        },
        { dispatch, props ->
        }
    )(ReduxAutocomplete::class.js.unsafeCast<RClass<ReduxAutocompleteProps<T>>>())

// val tagsAutocomplete = createReduxAutocompleteComponent<TagDTO>()
