@file:Suppress("PackageDirectoryMismatch")

package connected.react.router

import history.History
import react.RBuilder
import react.router.connected.ConnectedRouter

fun <S> RBuilder.connectedRouter(historyInstance: History<S>, children: RBuilder.() -> Unit) =
    child(ConnectedRouter::class) {
        attrs {
            this.history = historyInstance
        }
        children()
    }
