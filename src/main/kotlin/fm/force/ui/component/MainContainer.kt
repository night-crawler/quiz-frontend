package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mButton
import fm.force.ui.component.login.loginForm
import fm.force.ui.component.question.create.createQuestionForm
import fm.force.ui.component.question.create.editQuestionForm
import fm.force.ui.component.question.list.questionList
import fm.force.ui.component.quiz.create.createQuizForm
import fm.force.ui.component.quiz.create.editQuizForm
import fm.force.ui.component.quiz.list.quizList
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

            renderQuestionRoutes()
            renderQuizRoutes()

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

    private fun RBuilder.renderQuestionRoutes() {
        route("/questions", exact = true) {
            props.dispatch(ChangeAppViewName("Questions"))
            questionList()
        }
        route("/questions/create", exact = true) {
            props.dispatch(ChangeAppViewName("Create Question"))
            createQuestionForm()
        }
        route("/questions/:id/edit") {
            props.dispatch(ChangeAppViewName("Edit Question"))
            editQuestionForm()
        }
    }

    private fun RBuilder.renderQuizRoutes() {
        route("/quizzes", exact = true) {
            props.dispatch(ChangeAppViewName("Quizzes"))
            quizList()
        }
        route("/quizzes/create", exact = true) {
            props.dispatch(ChangeAppViewName("Create Quiz"))
            createQuizForm()
        }
        route("/quizzes/:id/edit", exact = true) {
            props.dispatch(ChangeAppViewName("Edit Quiz"))
            editQuizForm()
        }
    }
}
