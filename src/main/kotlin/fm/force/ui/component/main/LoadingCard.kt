package fm.force.ui.component.main

import com.ccfraser.muirwik.components.MTypographyAlign
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.mTypography
import react.RBuilder

fun RBuilder.loadingCard() {
    mCard {
        mCardContent {
            mTypography(align = MTypographyAlign.center) { +"Loading" }
        }
    }
}
