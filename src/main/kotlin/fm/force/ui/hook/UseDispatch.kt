package fm.force.ui.hook

import fm.force.ui.ReduxStore

fun useDispatch() = ReduxStore.DEFAULT.store::dispatch
