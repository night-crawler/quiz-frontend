package fm.force.ui.extension

import com.ccfraser.muirwik.components.styles.Theme

val Theme.codeMirrorTheme get() = when (palette.type) {
    "light" -> "idea"
    "dark" -> "darcula"
    else -> "idea"
}
