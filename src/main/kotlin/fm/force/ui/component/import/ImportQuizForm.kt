package fm.force.ui.component.import

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.QuizImportDTO
import fm.force.quiz.common.dto.QuizImportType
import fm.force.ui.ReduxStore
import fm.force.ui.client.FetchError
import fm.force.ui.component.field.codeMirrorFieldComponent
import fm.force.ui.component.field.renderSubmissionError
import fm.force.ui.extension.CodeLanguage
import fm.force.ui.hook.UseState
import fm.force.ui.hook.useDispatch
import fm.force.ui.reducer.action.SubmissionError
import fm.force.ui.reducer.action.of
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import react.router.connected.push
import styled.styledForm

val ImportQuizForm = functionalComponent<RProps> {
    var text by UseState("")
    var error by UseState<SubmissionError?>(null)
    val dispatch = useDispatch()

    styledForm {
        mFormControl(fullWidth = true) {
            codeMirrorFieldComponent(label = "Enter YAML", value = "", mode = CodeLanguage.YAML) {
                text = it
            }

            mButton("Import", onClick = {
                GlobalScope.launch {
                    QuizImportDTO(
                        text = text,
                        type = QuizImportType.YAML
                    ).let {
                        try {
                            val quiz = ReduxStore.DEFAULT.client.importQuiz(it)
                            dispatch(push("/quizzes/${quiz.id}/preview"))
                        } catch (exc: FetchError) {
                            error = SubmissionError.of(exc)
                        }
                    }
                }
            })
        }

        renderSubmissionError(error)
    }
}

fun RBuilder.importQuizForm() = child(ImportQuizForm) { }
