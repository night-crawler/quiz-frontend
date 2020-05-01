package fm.force.ui.component.session

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.MIconColor
import com.ccfraser.muirwik.components.button.*
import com.ccfraser.muirwik.components.card.MCardActionsProps
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.mAvatar
import com.ccfraser.muirwik.components.mIcon
import fm.force.quiz.common.dto.QuizSessionFullDTO
import fm.force.ui.util.IconName
import fm.force.ui.util.ago
import kotlinx.css.marginBottom
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledElementBuilder
import styled.css

interface SessionRowProps : RProps {
    var session: QuizSessionFullDTO
    var onComplete: (Any) -> Unit
    var onContinue: (Any) -> Unit
    var onCancel: (Any) -> Unit
}

class SessionRow(props: SessionRowProps) : RComponent<SessionRowProps, RState>(props) {
    override fun RBuilder.render() {
        val session = props.session
        val subHeader = listOfNotNull(
            session.cancelledAt?.let { ago(it, "Cancelled") },
            session.completedAt?.let { ago(it, "Completed") },
            session.validTill
                .takeIf { session.isAnswerable && !session.isExpired }
                ?.let { ago(it, "Expires in", suffix = "") },
            "Expired".takeIf { session.isExpired },
            ago(session.createdAt)
        ).joinToString(", ")

        mCard(raised = true) {
            css {
                marginBottom = 5.px
            }
            mCardHeader(
                title = props.session.quizTitle ?: "-- DELETED --",
                subHeader = subHeader
            ) {
                attrs {
                    avatar = mAvatar(addAsChild = false) { +(session.quizTitle?.slice(0..1) ?: "--") }
                }
            }
            mCardActions {
                renderSessionControlButtons()
            }
        }
    }

    private fun StyledElementBuilder<MCardActionsProps>.renderSessionControlButtons() {
        mButtonGroup(variant = MButtonGroupVariant.contained, size = MButtonSize.large, fullWidth = true) {
            mButton(
                caption = "",
                color = MColor.primary,
                variant = MButtonVariant.outlined,
                onClick = props.onContinue
            ) {
                if (props.session.isCancelled || props.session.isCompleted) {
                    mIcon(IconName.REMOVE_RED_EYE.iconMame, MIconColor.inherit)
                } else {
                    mIcon(IconName.NAVIGATE_NEXT.iconMame, MIconColor.inherit)
                    mIcon(IconName.NAVIGATE_NEXT.iconMame, MIconColor.inherit)
                    mIcon(IconName.NAVIGATE_NEXT.iconMame, MIconColor.inherit)
                }
            }

            if (!props.session.isCancelled && !props.session.isCompleted) {
                mButton(
                    caption = "Cancel",
                    color = MColor.primary,
                    variant = MButtonVariant.outlined,
                    onClick = props.onCancel
                ) {
                    attrs { endIcon = RBuilder().mIcon(IconName.CANCEL.iconMame, MIconColor.inherit) }
                }
                mButton(
                    caption = "Complete",
                    color = MColor.primary,
                    variant = MButtonVariant.outlined,
                    onClick = props.onComplete
                ) {
                    attrs { endIcon = RBuilder().mIcon(IconName.CANCEL_SCHEDULE_SEND.iconMame, MIconColor.inherit) }
                }
            }
        }
    }
}
