package fm.force.ui

import fm.force.ui.reducer.State
import fm.force.util.QueryBuilder
import fm.force.util.Thunk
import kotlinx.serialization.Serializable
import kotlinx.serialization.list
import redux.RAction
import redux.WrapperAction

class SetThemeType(val themeType: String) : RAction

class DrawerOpenToggle(val isOpen: Boolean) : RAction

class LoginStart() : RAction
class LoginSuccess(val text: String) : RAction
class LoginFinish() : RAction



@Serializable
data class LoginResponseDTO(
    val refreshToken: String,
    val accessToken: String
)


class QuizClient(
    private val scheme: String = "http",
    private val host: String = "localhost",
    private val port: Int = 3001
) {
    private val baseUri = "$scheme://$host:$port"

    private inline fun fetch(path: String, params: Map<String, Any>) {

        LoginResponseDTO.serializer().list
    }
}

val quizClient = QuizClient()

class SampleThunk : Thunk<State, RAction, WrapperAction, Any> {
    override suspend fun run(
        originalAction: RAction,
        dispatch: (RAction) -> WrapperAction,
        getState: () -> State,
        extra: Any
    ) {

        dispatch(LoginStart())
//        val response = quizClient.login(LoginDTO("qwe", "bla"))
//        console.log(response)

//        val ttt =

        val qb = QueryBuilder()
        qb.append("Lol", "SCUM==,TRUE?!")
        console.log(qb.toString())

        val qwe = QueryBuilder.of("qwe" to "trash")


        dispatch(LoginSuccess(text = "lalala"))
    }
}
