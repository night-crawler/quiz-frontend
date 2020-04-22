package fm.force.ui.extension

enum class CodeLanguage {
    KOTLIN, JAVA, JAVASCRIPT, PYTHON, XML, GENERAL;

    val codeMirrorModeName = "text/x-${name.toLowerCase()}"
}
