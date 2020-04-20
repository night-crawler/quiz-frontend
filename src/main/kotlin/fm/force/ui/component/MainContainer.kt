package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mButton
import fm.force.ui.component.login.loginForm
import fm.force.ui.component.question.create.createQuestionForm
import fm.force.ui.component.question.create.editQuestionForm
import fm.force.ui.component.question.list.questionList
import fm.force.ui.reducer.action.ChangeAppViewName
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.route
import react.router.dom.switch
import redux.RAction
import redux.WrapperAction

interface MainContainerProps : RProps {
    var locationPathname: String
    var dispatch: (RAction) -> WrapperAction
}

class MainContainer(props: MainContainerProps) : RComponent<MainContainerProps, RState>(props) {
    override fun RBuilder.render() {
        switch {
            route("/login") {
                props.dispatch(ChangeAppViewName("Login"))
                loginForm()
            }
            route("/questions", exact = true) {
                props.dispatch(ChangeAppViewName("Questions"))
                questionList()
            }
            route("/questions/create", exact = true) {
                props.dispatch(ChangeAppViewName("Create question"))
                createQuestionForm()
            }
            route("/questions/:id/edit") {
                props.dispatch(ChangeAppViewName("Edit question"))
                editQuestionForm()
            }
            route("/", exact = true) {
                props.dispatch(ChangeAppViewName("Home"))
                mButton("/")
            }
            route("*") {
                props.dispatch(ChangeAppViewName("Not Found"))
                notFound()
            }
        }
    }
}
