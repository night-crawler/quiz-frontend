package fm.force.ui.component.main

import com.ccfraser.muirwik.components.mDivider
import com.ccfraser.muirwik.components.themeContext
import com.ccfraser.muirwik.components.toolbarJsCssToPartialCss
import react.RBuilder
import styled.StyleSheet
import styled.css
import styled.styledDiv

fun RBuilder.spacer() {
    themeContext.Consumer { theme ->
        val themeStyles = object : StyleSheet("ComponentStyles", isStatic = true) {
            val toolbar by css {
                toolbarJsCssToPartialCss(theme.mixins.toolbar)
            }
        }

        // This puts in a spacer to get below the AppBar.
        styledDiv {
            css(themeStyles.toolbar)
        }
        mDivider { }
    }
}
