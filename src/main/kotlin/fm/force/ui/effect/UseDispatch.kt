package fm.force.ui.effect

import fm.force.ui.ReduxStore

fun useDispatch() = ReduxStore.DEFAULT.store::dispatch
