package fm.force.ui.component.session

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
import fm.force.ui.component.helmet
import fm.force.ui.component.loadingCard
import fm.force.ui.hook.useClient
import fm.force.ui.util.RouterContext
import react.*
import react.dom.title


val QuizSessionPreview = functionalComponent<RProps> {
    val routerContext = useContext(RouterContext)
    val quizId = routerContext.location.pathname.split("/").asReversed()[1].toLong()

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

    mCard(raised = true) {
        mCardHeader(title = quiz.title) {
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
                        +quizQuestion.question.title
                    }
                }
            }
            mTypography(gutterBottom = true) {
                +"Difficulty Scale: ${quiz.difficultyScale?.name ?: "-"}"
            }
        }
        mCardActions {
            mButton("Start quiz", color = MColor.primary)
        }
    }

}

fun RBuilder.quizSessionPreview() = child(QuizSessionPreview) {}

