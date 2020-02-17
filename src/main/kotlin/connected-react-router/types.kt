@file:Suppress("PackageDirectoryMismatch")

package react.router.connected

import history.Location
import redux.RAction

data class RouterState<S>(
    var location: Location<S>,
    var action: String /* "POP" | "PUSH" | "REPLACE" */
)

data class LocationChangeAction<S>(
    var type: Any,
    var payload: RouterState<S>
) : RAction
