package fm.force.ui.component.question.list

import com.ccfraser.muirwik.components.MColor
import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.dialog.mDialog
import com.ccfraser.muirwik.components.dialog.mDialogActions
import react.*
import react.dom.div

interface ConfirmDeleteDialogProps : RProps {
    var handler: RBuilder.(setter: (Boolean) -> Unit) -> Unit
    var onConfirm: (() -> Unit)?
    var onCancel: (() -> Unit)?
    var title: ReactElement
    var content: ReactElement
    var refCallback: ((value: dynamic) -> Unit)?
}

class ConfirmDeleteDialog(props: ConfirmDeleteDialogProps) : RComponent<ConfirmDeleteDialogProps, RState>(props) {
    private var isOpen = false
    private fun setIsOpen(value: Boolean) = setState { isOpen = value }

    override fun RBuilder.render() {
        div {
            ref {
                props.refCallback?.invoke(it)
            }
            props.handler(this, ::setIsOpen)
            mDialog(
                isOpen,
                onClose = { _, _ ->
                    setIsOpen(false)
                }
            ) {
                ref {
                    props.refCallback?.invoke(it)
                }
                childList.add(props.title)
                childList.add(props.content)

                mDialogActions {
                    mButton(
                        "Cancel", MColor.secondary,
                        onClick = {
                            setIsOpen(false)
                            props.onCancel?.invoke()
                        }
                    )
                    mButton(
                        "Confirm", MColor.primary,
                        onClick = {
                            props.onConfirm?.invoke()
                            setIsOpen(false)
                        }
                    )
                }
            }
        }
    }
}

fun RBuilder.confirmDeleteDialog(
    title: ReactElement,
    content: ReactElement,
    onCancel: (() -> Unit)? = null,
    onConfirm: (() -> Unit)? = null,
    dialogRef: ((value: dynamic) -> Unit)? = null,
    nestedHandler: RBuilder.(RSetState<Boolean>) -> Unit
) = child(ConfirmDeleteDialog::class) {
    attrs {
        this.handler = nestedHandler
        this.onCancel = onCancel
        this.onConfirm = onConfirm
        this.title = title
        this.content = content
        this.refCallback = dialogRef
    }
}
