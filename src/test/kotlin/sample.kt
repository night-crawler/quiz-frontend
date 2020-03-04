
import fm.force.util.jsApply
import kotlin.test.Test


interface Sample {
    var v: String
}

class AppTest {
    @Test
    fun testJsApply() {
        jsApply<Sample> { v = "1" }
    }
}
