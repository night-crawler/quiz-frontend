package fm.force.ui.hook

import kotlinext.js.Object
import react.rawUseState
import react.useCallback

fun useForceUpdate(): () -> Unit {
    val (_, dispatch) = rawUseState(Object.create(null))
    return useCallback(
        {
            dispatch(Object.create(null))
        },
        arrayOf(dispatch)
    )
}
