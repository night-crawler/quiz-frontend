package fm.force.ui

import fm.force.ui.client.QuizClient
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class QuizClientTest {
    @Test
    fun testPrepareUri() {
        val cliWithPort = QuizClient("http", "example.com", 1)
        cliWithPort.prepareUri("/") shouldBe "http://example.com:1"
        cliWithPort.prepareUri(
            "sample", "1", params = mapOf("param" to 2)
        ) shouldBe "http://example.com:1/sample/1?param=2"

        val cliWithoutPort = QuizClient("http", "example.com", null)
        cliWithoutPort.prepareUri("/") shouldBe "http://example.com"
        cliWithoutPort.prepareUri(
            "sample", "1", params = mapOf("param" to 2)
        ) shouldBe "http://example.com/sample/1?param=2"

    }
}
