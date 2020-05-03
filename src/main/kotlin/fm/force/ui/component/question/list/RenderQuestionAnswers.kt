package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.MTypographyColor
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemSecondaryAction
import com.ccfraser.muirwik.components.mIcon
import com.ccfraser.muirwik.components.mTypography
import com.ccfraser.muirwik.components.themeContext
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.util.IconName
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import react.RBuilder
import styled.StyleSheet
import styled.css

fun RBuilder.renderQuestionAnswers(question: QuestionFullDTO) {
    themeContext.Consumer { theme ->
        val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
            val list by css {
                backgroundColor = Color(theme.palette.background.paper)
            }
        }
        val correctAnswerIds = question.correctAnswers.map { it.id }.toSet()

        mTypography("Answers", color = MTypographyColor.textSecondary)

        mList(dense = true, disablePadding = true) {
            css(themeStyles.list)
            question.answers.forEach { answer ->
                mListItem {
                    +answer.text
                    mListItemSecondaryAction {
                        if (answer.id in correctAnswerIds)
                            mIcon(IconName.CHECK.iconMame)
                        else
                            mIcon(IconName.REMOVE_CIRCLE_OUTLINE.iconMame)
                    }
                }
            }
        }
    }
}
