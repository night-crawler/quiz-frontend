package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import date.fns.formatDistance
import fm.force.ui.component.iconMenu
import fm.force.ui.component.routeLink
import fm.force.ui.util.IconName
import kotlin.js.Date
import kotlinext.js.jsObject
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

interface RowProps : RProps {
    var index: Int
    var style: dynamic
}

class QuestionRow(props: RowProps) : RComponent<RowProps, RState>(props) {
    var thisRef: dynamic = null

    private fun forceListRecalculateHeights() {
        if (thisRef != null && thisRef.clientHeight != null) {
            val oldHeight = PaginatedQuestions.getHeight(props.index)
            val newHeight: Int = thisRef.clientHeight as Int
            if (oldHeight != newHeight) {
                PaginatedQuestions.setHeight(props.index, newHeight)
                PaginatedQuestions.infiniteListRef.resetAfterIndex(props.index, true)
            }
        }
    }

    override fun componentDidMount() = forceListRecalculateHeights()

    override fun componentDidUpdate(prevProps: RowProps, prevState: RState, snapshot: Any) =
        forceListRecalculateHeights()

    override fun RBuilder.render() {
        val question = PaginatedQuestions.getItem(props.index)
        if (question == null) {
            mCard {
                mCardContent {
                    mTypography(align = MTypographyAlign.center) { +"Loading" }
                }
            }
            return
        }

        mCard {
            ref {
                thisRef = it
            }
            attrs {
                val style = jsObject<dynamic> {
                    position = props.style.position
                    left = props.style.left
                    top = props.style.top
                    // don't touch height
                    // height: 0
                    width = props.style.width
                }

                this.asDynamic().style = style
            }
            mCardHeader(
                title = question.title,
                subHeader = "Created " + formatDistance(question.createdAt, Date()) + " ago"
            ) {
                attrs {
                    avatar = mAvatar(addAsChild = false) { +question.title.slice(0..1) }
                    action = iconMenu(IconName.MORE_VERT.iconMame) {
                        routeLink("/questions/${question.id}/delete") {
                            mMenuItemWithIcon(IconName.REMOVE.iconMame, "Delete", onClick = it.onClick)
                        }
                        routeLink("/questions/${question.id}/edit") {
                            mMenuItemWithIcon(IconName.EDIT.iconMame, "Edit", onClick = it.onClick)
                        }
                    }
                }
            }
            mCardContent {
                mTypography {
                    +question.text
                }
                renderQuestionAnswers(question)
            }
            mCardActions {
                question.tags.forEach {
                    mChip(it.name, size = MChipSize.small, variant = MChipVariant.outlined, color = MChipColor.primary)
                }
                question.topics.forEach {
                    mChip(
                        it.title,
                        size = MChipSize.small,
                        variant = MChipVariant.outlined,
                        color = MChipColor.secondary
                    )
                }
            }
        }
    }
}
