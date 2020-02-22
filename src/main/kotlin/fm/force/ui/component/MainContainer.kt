package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mButton
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.route
import react.router.dom.switch


interface MainContainerProps : RProps {
    var locationPathname: String
}

class MainContainer(props: MainContainerProps) : RComponent<MainContainerProps, RState>(props) {
    override fun RBuilder.render() {
        switch {
            route("/sample/:sub/222") {
                intro()
            }

            route("/sample", exact = true) {
                mButton("/sample")
            }
            route("/", exact = true) {
                mButton("/")
            }

            route("*") {
                notFound()
            }
        }
    }
}
