package fm.force.ui.component.session

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.table.*
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.quiz.common.dto.QuizSessionScoresDTO
import fm.force.quiz.common.dto.ScoreRestrictedDTO
import fm.force.ui.component.main.loadingCard
import fm.force.ui.hook.useClient
import fm.force.ui.hook.useRouterMatchParamsId
import react.RBuilder
import react.RProps
import react.child
import react.functionalComponent

val QuizSessionScores = functionalComponent<RProps> {
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

    child(QuizSessionScoresTable) {
        attrs {
            this.session = session
            this.scores = scores
        }
    }

}

fun RBuilder.sessionScores() = child(QuizSessionScores) {
}

interface SessionScoresTableProps : RProps {
    var session: QuizSessionFullDTO
    var scores: QuizSessionScoresDTO
}

data class ComputedScore(
    val total: Long,
    val correct: Long,
    val incorrect: Long
) {
    val correctPct = correct.toDouble() / total * 100
    val incorrectPct = incorrect.toDouble() / total * 100

    companion object
}

fun ComputedScore.Companion.of(scores: Collection<ScoreRestrictedDTO>) =
    ComputedScore(
        total = scores.map { it.count }.sum(),
        correct = scores.filter { it.isCorrect }.map { it.count }.sum(),
        incorrect = scores.filter { !it.isCorrect }.map { it.count }.sum()
    )

fun <T> ComputedScore.Companion.ofCollection(collection: Collection<ScoreRestrictedDTO>, keySelector: (t: ScoreRestrictedDTO) -> T ) =
    collection.groupBy(keySelector)
        .entries.map { (tag, scores) ->
            tag to ComputedScore.of(scores)
        }
        .sortedBy { (_, score) -> -score.total * score.correct }

val QuizSessionScoresTable = functionalComponent<SessionScoresTableProps> { props ->
    val tagScores = ComputedScore.ofCollection(props.scores.tagScores) { it.tag }
    val topicScores = ComputedScore.ofCollection(props.scores.topicScores) { it.topic }

    mTable {
        mTableHead {
            mTableRow {
                mTableCell(colSpan = 2) {
                    mTypography(variant = MTypographyVariant.h6, align = MTypographyAlign.center) {
                        +"Topics"
                    }
                }
            }
        }

        mTableBody {
            topicScores.forEach { (topic, computedScore) ->
                renderComputedScoreRow(
                    topic?.title ?: "-- deleted --",
                    computedScore
                )
            }
        }

        mTableHead {
            mTableRow {
                mTableCell(colSpan = 2) {
                    mTypography(variant = MTypographyVariant.h6, align = MTypographyAlign.center) {
                        +"Tags"
                    }
                }
            }
        }
        mTableBody {
            tagScores.forEach { (tag, computedScore) ->
                renderComputedScoreRow(
                    tag?.name ?: "-- deleted --",
                    computedScore
                )
            }
        }
    }
}

fun RBuilder.renderComputedScoreRow(name: String, computedScore: ComputedScore) {
    mTableRow {
        mTableCell {
            +name
        }
        mTableCell {
            mTypography(variant = MTypographyVariant.caption, color = MTypographyColor.textPrimary) {
                + "Correct: ${computedScore.correct} of ${computedScore.total}"
                mLinearProgress(
                    value = computedScore.correctPct,
                    variant = MLinearProgressVariant.determinate,
                    color = MLinearProgressColor.primary
                )
            }
            mTypography(variant = MTypographyVariant.caption, color = MTypographyColor.textSecondary) {
                + "Incorrect: ${computedScore.incorrect} of ${computedScore.total}"
                mLinearProgress(
                    value = computedScore.incorrectPct,
                    variant = MLinearProgressVariant.determinate,
                    color = MLinearProgressColor.secondary
                )
            }
        }
    }
}
