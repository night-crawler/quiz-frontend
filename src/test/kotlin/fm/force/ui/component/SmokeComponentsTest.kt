package fm.force.ui.component

import fm.force.ui.ReduxStore
import connected.react.router.connectedRouter
import fm.force.ui.container.appBar
import react.RBuilder
import react.dom.test.renderIntoDocument
import react.redux.provider
import styled.styledDiv
import kotlin.test.Test


class SmokeComponentsTest {
    @Test
    fun thingsShouldWork() {
        val reduxStore = ReduxStore.default()
        val a = RBuilder()
        val q = a.styledDiv {
            provider(reduxStore.store) {
                connectedRouter(reduxStore.history) {
                    appBar() {}
                }
            }
        }

        val res = renderIntoDocument(q)
        console.log(res)
    }
}
