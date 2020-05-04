package fm.force.ui.constant

val NODE_ENV = js("""process.env.NODE_ENV""").unsafeCast<String>()
val isProduction = NODE_ENV == "production"

const val SITE_HOST = "quiz.force.fm"
const val SITE_NAME = "Quiz"

const val API_HOST_PRODUCTION = "https://quiz-api.force.fm"
const val API_HOST_DEVELOPMENT = "http://localhost:8181"

val API_HOST = when(isProduction) {
    true -> API_HOST_PRODUCTION
    else -> API_HOST_DEVELOPMENT
}

const val DEFAULT_TITLE_TEMPLATE = "Quiz | %s"
const val DEFAULT_PAGE_SIZE = 20

