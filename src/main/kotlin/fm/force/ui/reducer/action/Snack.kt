package fm.force.ui.reducer.action

import com.benasher44.uuid.uuid4
import com.ccfraser.muirwik.components.MSnackbarHorizAnchor
import com.ccfraser.muirwik.components.MSnackbarVertAnchor
import fm.force.ui.util.Icon
import redux.RAction
import redux.WrapperAction

enum class SnackType {
    INFO, WARNING, ERROR
}

data class Snack(
    val title: String,
    val text: String = "",
    val timeout: Int? = 4000,
    val type: SnackType = SnackType.INFO,
    val horizAnchor: MSnackbarHorizAnchor = MSnackbarHorizAnchor.center,
    val vertAnchor: MSnackbarVertAnchor = MSnackbarVertAnchor.bottom,
    val icon: Icon = Icon.CHECK,
    val key: String = uuid4().toString()
)

class CloseSnack(val key: String) : RAction
class ShowSnack(val snack: Snack) : RAction

fun Snack.dispatch(dispatch: (RAction) -> WrapperAction) = dispatch(ShowSnack(this))
