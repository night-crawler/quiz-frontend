package fm.force.ui.component.session

import com.ccfraser.muirwik.components.table.*
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionScoresDTO
import fm.force.ui.component.main.loadingCard
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useRouterMatchParamsId
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent

val SessionScores = functionalComponent<RProps> {
    val sessionId = useRouterMatchParamsId()
    val session = useClient {
        getSession(sessionId)
    }
    val scores = useClient {
        getSessionScores(sessionId)
    }

    if (session == null || scores == null) {
        loadingCard()
        return@functionalComponent
    }

    val qwe = scores.tagScores.groupBy { it.tag }

    qwe.entries.forEach { (k,v ) ->
        console.log(k, v.size)
    }

    child(SessionScoresTable) {
        attrs {
            this.session = session
            this.scores = scores
        }
    }

}

fun RBuilder.sessionScores() = child(SessionScores) {
}

interface SessionScoresTableProps : RProps {
    var session: QuizSessionFullDTO
    var scores: QuizSessionScoresDTO
}
val SessionScoresTable = functionalComponent<SessionScoresTableProps> { props ->
    mTable {
        mTableHead {
            mTableRow {
                mTableCell { +"qwe" }
            }
        }

        mTableBody {
            props.scores.tagScores.forEach {
                mTableRow {

                }
            }
        }
    }
}
