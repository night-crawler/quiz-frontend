package fm.force.ui.component

import fm.force.util.RouterContext
import org.w3c.dom.events.Event
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState

interface LocationComponentProps : RProps {
    var path: String
    var cb: RBuilder.(PathInfo) -> Unit
}

data class PathInfo(
    val push: (String) -> Unit,
    val isActive: Boolean,
    val onClick: (Event) -> Unit
)

class RouteLink(props: LocationComponentProps) : RPureComponent<LocationComponentProps, RState>(props) {

    override fun RBuilder.render() {
        RouterContext.Consumer { context ->
            val pathInfo = PathInfo(
                push = context.history::push,
                isActive = context.location.pathname == props.path,
                onClick = { context.history.push(props.path) }
            )
            props.cb(this, pathInfo)
        }
    }
}

fun RBuilder.routeLink(path: String, cb: RBuilder.(PathInfo) -> Unit) = child(RouteLink::class) {
    attrs {
        this.cb = cb
        this.path = path
    }
}
