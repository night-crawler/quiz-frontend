package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mButton
import fm.force.ui.component.difficultyScale.create.createDifficultyScaleForm
import fm.force.ui.component.difficultyscale.create.editDifficultyScaleForm
import fm.force.ui.component.difficultyscale.list.difficultyScaleList
import fm.force.ui.component.login.loginForm
import fm.force.ui.component.question.create.createQuestionForm
import fm.force.ui.component.question.create.editQuestionForm
import fm.force.ui.component.question.list.questionList
import fm.force.ui.component.quiz.create.createQuizForm
import fm.force.ui.component.quiz.create.editQuizForm
import fm.force.ui.component.quiz.list.quizList
import fm.force.ui.component.session.quizSessionPreview
import fm.force.ui.reducer.action.ChangeAppViewName
import kotlin.browser.window
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
    private fun deferredDispatch(action: RAction) {
        window.setTimeout(
            {
                props.dispatch(action)
            },
            0
        )
    }

    override fun RBuilder.render() {
        switch {
            route("/login") {
                deferredDispatch(ChangeAppViewName("Login"))
                loginForm()
            }

            renderQuestionRoutes()
            renderQuizRoutes()
            renderDifficultyScaleRoutes()
            renderQuizSessionRoutes()

            route("/", exact = true) {
                deferredDispatch(ChangeAppViewName("Home"))
                mButton("/")
            }
            route("*") {
                deferredDispatch(ChangeAppViewName("Not Found"))
                notFound()
            }
        }
    }

    private fun RBuilder.renderQuizSessionRoutes() {
        route("/sessions/:id/preview") {
            deferredDispatch(ChangeAppViewName("Start Quiz Session"))
            quizSessionPreview()
        }
    }

    private fun RBuilder.renderDifficultyScaleRoutes() {
        route("/difficulty-scales", exact = true) {
            deferredDispatch(ChangeAppViewName("Create Difficulty Scale"))
            difficultyScaleList()
        }
        route("/difficulty-scales/create", exact = true) {
            deferredDispatch(ChangeAppViewName("Create Difficulty Scale"))
            createDifficultyScaleForm()
        }
        route("/difficulty-scales/:id/edit") {
            deferredDispatch(ChangeAppViewName("Edit Difficulty Scale"))
            editDifficultyScaleForm()
        }
    }

    private fun RBuilder.renderQuestionRoutes() {
        route("/questions", exact = true) {
            deferredDispatch(ChangeAppViewName("Questions"))
            questionList()
        }
        route("/questions/create", exact = true) {
            deferredDispatch(ChangeAppViewName("Create Question"))
            createQuestionForm()
        }
        route("/questions/:id/edit") {
            deferredDispatch(ChangeAppViewName("Edit Question"))
            editQuestionForm()
        }
    }

    private fun RBuilder.renderQuizRoutes() {
        route("/quizzes", exact = true) {
            deferredDispatch(ChangeAppViewName("Quizzes"))
            quizList()
        }
        route("/quizzes/create", exact = true) {
            deferredDispatch(ChangeAppViewName("Create Quiz"))
            createQuizForm()
        }
        route("/quizzes/:id/edit", exact = true) {
            deferredDispatch(ChangeAppViewName("Edit Quiz"))
            editQuizForm()
        }
    }
}
