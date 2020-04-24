package fm.force.ui.component

import com.ccfraser.muirwik.components.MTypographyAlign
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.mTypography
import react.RBuilder

fun RBuilder.noElements() {
    mCard {
        mCardContent {
            mTypography(align = MTypographyAlign.center) { +"No elements" }
        }
    }
}
