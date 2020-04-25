package fm.force.ui.component

import fm.force.ui.client.FetchError
import fm.force.ui.reducer.action.ShowSnack
import fm.force.ui.reducer.action.Snack
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.util.IconName
import redux.RAction
import redux.WrapperAction

fun defaultSubmitErrorHandler(dispatch: (RAction) -> WrapperAction): suspend (
    original: FetchError,
    transformed: SubmissionError
) -> WrapperAction {
    return { original, _ ->
        dispatch(
            ShowSnack(
                Snack(
                    title = "Submission error",
                    text = original.message ?: original.responseText,
                    iconName = IconName.ERROR,
                    timeout = null
                )
            )
        )
    }
}
