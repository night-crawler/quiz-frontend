package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.card.*
import com.ccfraser.muirwik.components.dialog.*
import com.ccfraser.muirwik.components.menu.mMenuItemWithIcon
import date.fns.formatDistance
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.ui.ReduxStore
import fm.force.ui.component.iconMenu
import fm.force.ui.component.routeLink
import fm.force.ui.util.IconName
import kotlinext.js.jsObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import org.w3c.dom.events.EventTarget
import react.*
import styled.StyledElementBuilder
import kotlin.browser.window
import kotlin.js.Date


interface QuestionRowProps : RProps {
    var index: Int
    var style: dynamic
}

class QuestionRow(props: QuestionRowProps) : RComponent<QuestionRowProps, RState>(props) {
    private var thisRef: dynamic = null
    private var deleteButtonRef: dynamic = null

    private fun forceListRecalculateHeights() {
        if (thisRef == null || thisRef.clientHeight == null) {
            // it's likely that this element is in cache and it's not visible
            return
        }

        // We don't display infinite list while loading, so there's no it's ref.
        // But this ref will not be set immediately, so we guess it must be rendered later
        if (!PaginatedQuestions.isInitialized) {
            window.setTimeout({
                forceListRecalculateHeights()
            }, 0)
            return
        }

        val oldHeight = PaginatedQuestions.getHeight(props.index)
        val newHeight: Int = thisRef.clientHeight as Int
        if (oldHeight != newHeight) {
            PaginatedQuestions.setHeight(props.index, newHeight)
            PaginatedQuestions.infiniteListRef!!.resetAfterIndex(props.index, true)
        }
    }

    override fun componentDidMount() = forceListRecalculateHeights()

    override fun componentDidUpdate(prevProps: QuestionRowProps, prevState: RState, snapshot: Any) =
        forceListRecalculateHeights()

    private fun shouldCloseMenu(eventTarget: EventTarget?): Boolean {
        if (eventTarget == deleteButtonRef) return false
        return true
    }

    override fun RBuilder.render() {
        val question = PaginatedQuestions.getItem(props.index)
        if (question == null) {
            loadingCard()
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
                    action = renderAction(question)
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

    private fun StyledElementBuilder<MCardHeaderProps>.renderAction(question: QuestionFullDTO) =
        iconMenu(IconName.MORE_VERT.iconMame, shouldClose = ::shouldCloseMenu) {
            confirmDeleteDialog(
                title = RBuilder().mDialogTitle("Delete question ${question.title}?"),
                content = RBuilder().mDialogContent {
                    mDialogContentText("This action is permanent")
                },
                onConfirm = { handleConfirmDelete(question) }
            ) { setIsOpen ->
                mMenuItemWithIcon(IconName.REMOVE.iconMame, "Delete", onClick = {
                    setIsOpen(true)
                }) {
                    ref {
                        deleteButtonRef = it
                    }
                }
            }
            routeLink("/questions/${question.id}/edit") {
                mMenuItemWithIcon(IconName.EDIT.iconMame, "Edit", onClick = it.onClick)
            }
        }

    private fun handleConfirmDelete(question: QuestionFullDTO) {
        GlobalScope.promise {
            ReduxStore.DEFAULT.client.deleteQuestion(question.id)
        }
    }
}
