package fm.force.ui.component

import connected.react.router.connectedRouter
import fm.force.ui.ReduxStore
import fm.force.ui.container.appBar
import kotlin.test.Test
import react.RBuilder
import react.dom.test.renderIntoDocument
import react.redux.provider
import styled.styledDiv

class SmokeComponentsTest {
    @Test
    fun thingsShouldWork() {
        val reduxStore = ReduxStore.DEFAULT
        val a = RBuilder()
        val q = a.styledDiv {
            provider(reduxStore.store) {
                connectedRouter(reduxStore.history) {
                    appBar {}
                }
            }
        }

        val res = renderIntoDocument(q)
        console.log(res)
    }
}
