package fm.force.ui.component.field

import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.card.mCardActions
import fm.force.ui.util.Icon
import kotlinx.css.Direction
import kotlinx.css.direction
import react.RBuilder
import redux.form.WrappedFieldArrayProps
import styled.css

fun RBuilder.arrayFieldMoveButtons(reduxFormProps: WrappedFieldArrayProps<*>, index: Int) = mCardActions {
    css {
        direction = Direction.rtl
    }
    mIconButton(Icon.DELETE_OUTLINE.iconMame, onClick = { reduxFormProps.fields.remove(index) })
    if (index > 0) {
        mIconButton(
            Icon.KEYBOARD_ARROW_UP.iconMame,
            onClick = { reduxFormProps.fields.move(index, index - 1) }
        )
    }

    if (index < reduxFormProps.fields.length - 1) {
        mIconButton(
            Icon.KEYBOARD_ARROW_DOWN.iconMame,
            onClick = { reduxFormProps.fields.move(index, index + 1) }
        )
    }
}
