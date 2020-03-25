package fm.force.ui.reducer

data class AppPreferences(
    val themeType: String,
    val appTitle: String,
    val activeViewDisplayName: String,
    val responsiveDrawerOpen: Boolean
) {
    companion object
}
