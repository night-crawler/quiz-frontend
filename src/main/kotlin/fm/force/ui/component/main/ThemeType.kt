package fm.force.ui.component.main

enum class ThemeType(val value: String) {
    DARK("dark"),
    LIGHT("light");

    companion object {
        private val map = values().associateBy(ThemeType::value)
        fun of(value: String) = map[value] ?: error("No value `$value` in ThemeType")
    }
}
