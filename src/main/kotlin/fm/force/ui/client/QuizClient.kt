package fm.force.ui.client

import fm.force.quiz.common.dto.*
import fm.force.ui.client.dto.*
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.constant.DEFAULT_PAGE_SIZE
import fm.force.ui.util.QueryBuilder
import fm.force.ui.util.parseQueryString
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.w3c.fetch.Response

interface SearchCriteria

@Serializable
data class DefaultSearchCriteria(
    val query: String = "",
    val sort: String = "-createdAt",
    val page: Int = 1,
    val pageSize: Int = DEFAULT_PAGE_SIZE
) : SearchCriteria {
    companion object
}

fun DefaultSearchCriteria.toMap() = mapOf(
    "page" to page,
    "query" to query,
    "sort" to sort,
    "pageSize" to pageSize
)

fun DefaultSearchCriteria.toQueryString() = QueryBuilder.of(toMap()).toString()

fun DefaultSearchCriteria.Companion.fromQueryString(rawString: String): DefaultSearchCriteria {
//    val dyn = parseQueryString(rawString)
//    return DynamicObjectParser().parse(dyn)
    val s = JSON.stringify(parseQueryString(rawString))
    return Json.parse(serializer(), s)
}

open class QuizClient(
    scheme: String = "http",
    host: String = "localhost",
    port: Int? = null,
    private val fetchAdapter: FetchAdapter = AuthAwareFetchAdapter(WindowFetchAdapter())
) {
    private val baseUri = "$scheme://$host" + (port?.let { ":$port" } ?: "")
    private val jsonHeaders = mapOf(
        "Content-Type" to "application/json",
        "Accept" to "application/json"
    )
    private val refreshUri = prepareUri("auth/refresh")

    init {
        fetchAdapter.configure(
            AuthAwareAdapterConfiguration(
                refreshUri,
                listOf("$baseUri/auth/.+".toRegex())
            )
        )
    }

    private suspend inline fun <reified ResponseType : Any> Json.buildResponse(
        request: Request,
        response: Response
    ): ResponseType {
        val serializer = ResponseType::class.serializer()
        return parse(serializer, response.text().await())
    }

    fun prepareUri(vararg paths: String, params: Map<String, Any> = mapOf()) =
        QueryBuilder.of(params).appendTo(
            concatPaths(
                baseUri,
                *paths
            )
        )

    suspend fun findQuestions(criteria: DefaultSearchCriteria): PageWrapper<QuestionFullDTO> =
        fetchAdapter.fetch<PageDTO>(
            HttpMethod.GET, prepareUri("questions", params = criteria.toMap()), null,
            headers = jsonHeaders,
            buildResponse = { request, response -> buildResponse(request, response) }
        ).toTypedPage()

    suspend fun findDifficultyScales(criteria: DefaultSearchCriteria): PageWrapper<DifficultyScaleFullDTO> =
        fetchAdapter.fetch<PageDTO>(
            HttpMethod.GET, prepareUri("difficultyScales", params = criteria.toMap()), null,
            headers = jsonHeaders,
            buildResponse = { request, response -> buildResponse(request, response) }
        ).toTypedPage()

    suspend fun findQuizzes(searchCriteria: DefaultSearchCriteria): PageWrapper<QuizFullDTO> {
        return fetchAdapter.fetch<PageDTO>(
            HttpMethod.GET, prepareUri("quizzes", params = searchCriteria.toMap()), null,
            headers = jsonHeaders,
            buildResponse = { request, response -> buildResponse(request, response) }
        ).toTypedPage()
    }

    suspend fun deleteDifficultyScale(id: Long) = fetchAdapter.fetch(
        HttpMethod.DELETE, prepareUri("difficultyScales/$id"), null,
        headers = jsonHeaders,
        buildResponse = { _, _ -> Unit }
    )

    suspend fun deleteQuiz(id: Long) = fetchAdapter.fetch(
        HttpMethod.DELETE, prepareUri("quizzes/$id"), null,
        headers = jsonHeaders,
        buildResponse = { _, _ -> Unit }
    )

    suspend fun deleteQuestion(id: Long) = fetchAdapter.fetch(
        HttpMethod.DELETE, prepareUri("questions/$id"), null,
        headers = jsonHeaders,
        buildResponse = { _, _ -> Unit }
    )

    suspend fun patchQuestion(id: Long, patchDTO: QuestionPatchDTO) = fetchAdapter.fetch<QuestionFullDTO>(
        HttpMethod.PATCH, prepareUri("questions/$id"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun patchQuiz(id: Long, patchDTO: QuizPatchDTO) = fetchAdapter.fetch<QuizFullDTO>(
        HttpMethod.PATCH, prepareUri("quizzes/$id"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun patchDifficultyScale(id: Long, patchDTO: DifficultyScalePatchDTO) =
        fetchAdapter.fetch<DifficultyScaleFullDTO>(
            HttpMethod.PATCH, prepareUri("difficultyScales/$id"), patchDTO,
            headers = jsonHeaders,
            buildResponse = { request, response -> buildResponse(request, response) }
        )

    suspend fun createQuiz(patchDTO: QuizPatchDTO) = fetchAdapter.fetch<QuizFullDTO>(
        HttpMethod.POST, prepareUri("quizzes"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun createDifficultyScale(patchDTO: DifficultyScalePatchDTO) = fetchAdapter.fetch<DifficultyScaleFullDTO>(
        HttpMethod.POST, prepareUri("difficultyScales"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun createQuestion(patchDTO: QuestionPatchDTO) = fetchAdapter.fetch<QuestionFullDTO>(
        HttpMethod.POST, prepareUri("questions"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun createAnswer(patchDTO: AnswerPatchDTO) = fetchAdapter.fetch<AnswerFullDTO>(
        HttpMethod.POST, prepareUri("answers"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun patchAnswer(id: Long, patchDTO: AnswerPatchDTO) = fetchAdapter.fetch<AnswerFullDTO>(
        HttpMethod.PATCH, prepareUri("answers/$id"), patchDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun getOrCreateTopic(topic: TopicPatchDTO) = fetchAdapter.fetch<TopicFullDTO>(
        HttpMethod.POST, prepareUri("topics/getOrCreate"), topic,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun getOrCreateTag(tag: TagPatchDTO) = fetchAdapter.fetch<TagFullDTO>(
        HttpMethod.POST, prepareUri("tags/getOrCreate"), tag,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun getDifficultyScale(id: Long) = fetchAdapter.fetch<DifficultyScaleFullDTO>(
        HttpMethod.GET, prepareUri("difficultyScales/$id"), null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun getQuestion(id: Long) = fetchAdapter.fetch<QuestionFullDTO>(
        HttpMethod.GET, prepareUri("questions/$id"), null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun getQuiz(id: Long) = fetchAdapter.fetch<QuizFullDTO>(
        HttpMethod.GET, prepareUri("quizzes/$id"), null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun getRestrictedQuiz(id: Long) = fetchAdapter.fetch<QuizRestrictedDTO>(
        HttpMethod.GET, prepareUri("quizzes/$id/view"), null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun createTag(tag: TagPatchDTO) = fetchAdapter.fetch<TagFullDTO>(
        HttpMethod.POST, prepareUri("tags"), tag,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun startSession(quizId: Long) = fetchAdapter.fetch<QuizSessionFullDTO>(
        HttpMethod.POST, prepareUri("quizzes/$quizId/startSession"), null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun findTopics(text: String = ""): PageWrapper<TopicFullDTO> {
        val params = mapOf("query" to text, "sort" to "title")
        return fetchAdapter.fetch<PageDTO>(
            HttpMethod.GET, prepareUri("topics", params = params), null,
            headers = jsonHeaders,
            buildResponse = { request: Request, response: Response -> buildResponse(request, response) }
        ).toTypedPage()
    }

    suspend fun findTags(text: String = ""): PageWrapper<TagFullDTO> {
        val params = mapOf("query" to text, "sort" to "name")
        return fetchAdapter.fetch<PageDTO>(
            HttpMethod.GET, prepareUri("tags", params = params), null,
            headers = jsonHeaders,
            buildResponse = { request: Request, response: Response -> buildResponse(request, response) }
        ).toTypedPage()
    }

    suspend fun currentProfile() = fetchAdapter.fetch<UserFullDTO>(
        HttpMethod.GET, prepareUri("profiles/current"), null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun refresh() = fetchAdapter.fetch<JwtAccessTokenDTO>(
        HttpMethod.POST, refreshUri,
        null,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )

    suspend fun login(jwtResponseTokensDTO: LoginRequestDTO) = fetchAdapter.fetch<JwtResponseTokensDTO>(
        HttpMethod.POST, prepareUri("auth/login"),
        jwtResponseTokensDTO,
        headers = jsonHeaders,
        buildResponse = { request, response -> buildResponse(request, response) }
    )
}
