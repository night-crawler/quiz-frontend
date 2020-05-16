package fm.force.ui.component.import

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.form.mFormControl
import fm.force.quiz.common.dto.QuizImportDTO
import fm.force.quiz.common.dto.QuizImportType
import fm.force.ui.component.field.codeMirrorFieldComponent
import fm.force.ui.extension.CodeLanguage
import fm.force.ui.hook.UseState
import fm.force.ui.hook.callApi
import fm.force.ui.hook.useDispatch
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent
import react.router.connected.push
import styled.styledForm

val ImportQuizForm = functionalComponent<RProps> {
    var text by UseState("")
    val dispatch = useDispatch()

    styledForm {
        mFormControl(fullWidth = true) {
            codeMirrorFieldComponent(label = "Enter YAML", value = "", mode = CodeLanguage.YAML) {
                text = it
            }

            mButton(
                "Import",
                onClick = {
                    val quizImportDTO = QuizImportDTO(
                        text = text,
                        type = QuizImportType.YAML
                    )

                    callApi {
                        val quiz = importQuiz(quizImportDTO)
                        dispatch(push("/quizzes/${quiz.id}/preview"))
                    }
                }
            )
        }
    }
}

fun RBuilder.importQuizForm() = child(ImportQuizForm) { }
