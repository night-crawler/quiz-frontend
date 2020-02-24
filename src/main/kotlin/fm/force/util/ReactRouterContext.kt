package fm.force.util

import react.RContext
import react.router.dom.RouteResultProps

@JsModule("react-router")
private external val ReactRouterModule: dynamic

@Suppress("UnsafeCastFromDynamic")
val RouterContext: RContext<RouteResultProps<*>> = ReactRouterModule.__RouterContext
