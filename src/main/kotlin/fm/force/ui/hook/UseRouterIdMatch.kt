package fm.force.ui.hook

import fm.force.ui.util.RouterContext
import react.RProps
import react.useContext

interface IdMatchProps : RProps {
    val id: String
}

fun <T> useRouterMatchParams(): T {
    val routerContext = useContext(RouterContext)
    return routerContext.match.params.unsafeCast<T>()
}

fun useRouterMatchParamsId(): Long {
    val params = useRouterMatchParams<IdMatchProps>()
    return params.id.toLong()
}
