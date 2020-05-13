package fm.force.ui.component.main

import fm.force.ui.component.auth.loginForm
import fm.force.ui.component.auth.registerForm
import fm.force.ui.component.difficultyScale.create.createDifficultyScaleForm
import fm.force.ui.component.difficultyscale.create.editDifficultyScaleForm
import fm.force.ui.component.difficultyscale.list.difficultyScaleList
import fm.force.ui.component.import.importQuizForm
import fm.force.ui.component.question.create.createQuestionForm
import fm.force.ui.component.question.create.editQuestionForm
import fm.force.ui.component.quiz.create.createQuizForm
import fm.force.ui.component.quiz.create.editQuizForm
import fm.force.ui.component.quiz.list.quizList
import fm.force.ui.component.quiz.quizPreview
import fm.force.ui.component.session.quizSessionList
import fm.force.ui.component.session.sessionScores
import fm.force.ui.container.questionList
import fm.force.ui.container.quizComposer
import fm.force.ui.container.sessionUI
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
            renderAuthRoutes()

            renderQuestionRoutes()
            renderQuizRoutes()
            renderDifficultyScaleRoutes()
            renderQuizSessionRoutes()
            renderMisc()

            route("/", exact = true) {
                deferredDispatch(ChangeAppViewName("Home"))
                helpPage()
            }
            route("*") {
                deferredDispatch(ChangeAppViewName("Not Found"))
                notFound()
            }
        }
    }

    private fun RBuilder.renderMisc() {
        route("/import", exact = true) {
            deferredDispatch(ChangeAppViewName("Import Quiz"))
            importQuizForm()
        }
    }

    private fun RBuilder.renderAuthRoutes() {
        route("/login", exact = true) {
            deferredDispatch(ChangeAppViewName("Login"))
            loginForm()
        }

        route("/register", exact = true) {
            deferredDispatch(ChangeAppViewName("Register"))
            registerForm()
        }
    }

    private fun RBuilder.renderQuizSessionRoutes() {
        route("/sessions", exact = true) {
            deferredDispatch(ChangeAppViewName("Sessions"))
            quizSessionList()
        }

        route("/sessions/:id/report", exact = true) {
            deferredDispatch(ChangeAppViewName("Session scores"))
            sessionScores()
        }

        route("/sessions/:id/test", exact = true) {
            deferredDispatch(ChangeAppViewName("Test session"))
            sessionUI {}
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
            questionList {}
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
        route("/quizzes/:id/compose", exact = true) {
            deferredDispatch(ChangeAppViewName("Compose Quiz"))
            quizComposer {}
        }
        route("/quizzes/:id/preview") {
            deferredDispatch(ChangeAppViewName("Start Quiz Session"))
            quizPreview()
        }
    }
}
