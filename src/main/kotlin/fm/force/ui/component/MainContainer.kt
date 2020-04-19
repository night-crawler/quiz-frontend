package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mButton
import fm.force.ui.component.login.loginForm
import fm.force.ui.component.question.create.createQuestionForm
import fm.force.ui.component.question.create.editQuestionForm
import fm.force.ui.component.question.list.questionList
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

            route("/login") {
                loginForm()
            }

            route("/questions", exact = true) {
                questionList()
            }

            route("/questions/create") {
                createQuestionForm()
            }

            route("/questions/:id/edit") {
                editQuestionForm()
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
