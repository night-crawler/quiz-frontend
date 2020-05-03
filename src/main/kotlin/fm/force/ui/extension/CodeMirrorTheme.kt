package fm.force.ui.extension

import com.ccfraser.muirwik.components.styles.Theme
import react.syntax.highlighter.style.prism.darculaPrismStyle
import react.syntax.highlighter.style.prism.prismColorsStyle


val Theme.codeMirrorTheme
    get() = when (palette.type) {
        "light" -> "idea"
        "dark" -> "darcula"
        else -> "idea"
    }

val Theme.syntaxHighlightStyle
    get() = when (palette.type) {
        "light" -> prismColorsStyle
        "dark" -> darculaPrismStyle
        else -> prismColorsStyle
    }
