package fm.force.ui.reducer.state

data class AppPreferences(
    val themeType: String,
    val appTitle: String,
    val activeViewDisplayName: String,
    val responsiveDrawerOpen: Boolean
) {
    companion object
}

fun AppPreferences.Companion.of() =
    AppPreferences(
        themeType = "light",
        appTitle = "Quiz",
        activeViewDisplayName = "",
        responsiveDrawerOpen = false
    )
