package fm.force.ui.client

import fm.force.quiz.common.dto.*
import fm.force.ui.client.dto.*
import fm.force.ui.client.dto.UserFullDTO
import fm.force.ui.util.QueryBuilder
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.w3c.fetch.Response

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

    suspend fun findQuestions(
        page: Int,
        pageSize: Int = 25,
        query: String,
        sort: String
    ): PageWrapper<QuestionFullDTO> {
        val params = mapOf(
            "page" to page,
            "query" to query,
            "sort" to sort,
            "pageSize" to pageSize
        )
        return fetchAdapter.fetch<PageDTO>(
            HttpMethod.GET, prepareUri("questions", params = params), null,
            headers = jsonHeaders,
            buildResponse = { request, response -> buildResponse(request, response) }
        ).toTypedPage()
    }

    suspend fun deleteQuestion(id: Long) = fetchAdapter.fetch(
        HttpMethod.DELETE, prepareUri("questions/$id"), null,
        headers = jsonHeaders,
        buildResponse = { _, _ -> Unit }
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

    suspend fun createTag(tag: TagPatchDTO) = fetchAdapter.fetch<TagFullDTO>(
        HttpMethod.POST, prepareUri("tags"), tag,
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
