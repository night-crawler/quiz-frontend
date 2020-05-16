package fm.force.ui.component.quiz

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.MTypographyColor
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.mTypography
import fm.force.quiz.common.dto.QuizRestrictedDTO
import fm.force.ui.component.main.helmet
import fm.force.ui.component.main.loadingCard
import fm.force.ui.hook.callApi
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useDispatch
import fm.force.ui.util.RouterContext
import kotlinx.css.LinearDimension
import kotlinx.css.width
import org.w3c.dom.events.Event
import react.*
import react.dom.title
import react.router.connected.push
import styled.css

interface QuizPreviewProps : RProps {
    var quizRestrictedDTO: QuizRestrictedDTO
    var onStartSession: (e: Event) -> Unit
}

val QuizPreview = functionalComponent<RProps> {
    val routerContext = useContext(RouterContext)
    val quizId = routerContext.location.pathname.split("/").asReversed()[1].toLong()
    val dispatch = useDispatch()

    val quiz = useClient {
        getRestrictedQuiz(quizId)
    }

    helmet {
        title { +"Start Quiz Session" }
    }

    if (quiz == null) {
        loadingCard()
        return@functionalComponent
    }

    @Suppress("UNUSED_PARAMETER")
    fun handleStartSession(e: Event) {
        callApi {
            startSession(quiz.id).let {
                dispatch(push(("/sessions/${it.id}/test")))
            }
        }
    }

    child(QuizPreviewCard) {
        attrs {
            quizRestrictedDTO = quiz
            onStartSession = ::handleStartSession
        }
    }
}

private val QuizPreviewCard = functionalComponent<QuizPreviewProps> { props ->
    val quiz = props.quizRestrictedDTO

    mCard(raised = true) {
        mCardHeader(title = quiz.title, subHeader = "Difficulty Scale: ${quiz.difficultyScale?.name ?: "-"}") {
            attrs {
                avatar = mAvatar(addAsChild = false) { +quiz.title.slice(0..1) }
            }
        }
        mCardContent {
            mTypography(color = MTypographyColor.textPrimary) {
                +"Questions:"
            }
            mList(dense = true) {
                quiz.quizQuestions.forEach { quizQuestion ->
                    mListItem {
                        +"#${quizQuestion.seq + 1} ${quizQuestion.question.title}"
                    }
                }
            }
        }
        mCardActions {
            mButton("Start quiz", color = MColor.default, onClick = props.onStartSession) {
                css {
                    width = LinearDimension("100%")
                }
            }
        }
    }
}

fun RBuilder.quizPreview() = child(QuizPreview) {}
