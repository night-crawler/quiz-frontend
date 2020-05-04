@file:JsModule("connected-react-router")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION",
    "PackageDirectoryMismatch"
)

package react.router.connected

import history.History
import history.LocationDescriptorObject
import history.LocationState
import history.Path
import react.Component
import react.RProps
import react.RState
import react.ReactElement
import redux.Middleware
import redux.RAction
import redux.Reducer

external interface ConnectedRouterProps : RProps {
    var history: History<*>
}

external var LOCATION_CHANGE: String /* '@@router/LOCATION_CHANGE' */

external var CALL_HISTORY_METHOD: String /* '@@router/CALL_HISTORY_METHOD' */

external interface CallHistoryMethodAction {
    var type: Any
    var payload: LocationActionPayload
}

external fun push(
    path: Path,
    state: LocationState = definedExternally
): LocationChangeAction<*> /* LocationChangeAction | CallHistoryMethodAction */

external fun <S> push(location: LocationDescriptorObject<S>): dynamic /* LocationChangeAction | CallHistoryMethodAction */

external fun replace(
    path: Path,
    state: LocationState = definedExternally
): LocationChangeAction<*> /* LocationChangeAction | CallHistoryMethodAction */

external fun <S> replace(location: LocationDescriptorObject<S>): dynamic /* LocationChangeAction | CallHistoryMethodAction */

external fun go(n: Number): dynamic /* LocationChangeAction | CallHistoryMethodAction */

external fun goBack(): dynamic /* LocationChangeAction | CallHistoryMethodAction */

external fun goForward(): dynamic /* LocationChangeAction | CallHistoryMethodAction */

external object routerActions {
    var push: Any
    var replace: Any
    var go: Any
    var goBack: Any
    var goForward: Any
}

external interface LocationActionPayload {
    var method: String
    var args: Array<Any>?
        get() = definedExternally
        set(value) = definedExternally
}

external class ConnectedRouter : Component<ConnectedRouterProps, RState> {
    override fun render(): ReactElement?
}

external fun <S> connectRouter(history: History<S>): Reducer<RouterState<S>, RAction>

external fun <HS, S, A1, R1, A2, R2> routerMiddleware(history: History<HS>): Middleware<S, A1, R1, A2, R2>
