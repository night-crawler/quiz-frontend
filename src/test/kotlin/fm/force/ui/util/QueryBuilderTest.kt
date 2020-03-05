package fm.force.ui.util

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class QueryBuilderTest {

    @Test
    fun testToString() {
        QueryBuilder.of().toString() shouldBe ""
        QueryBuilder.of(mapOf("a" to 2)).toString() shouldBe "a=2"
        QueryBuilder.of("a" to 2).toString() shouldBe "a=2"
        QueryBuilder.of("a" to mapOf("b" to "c")).toString() shouldBe "a.b=c"

        QueryBuilder.of("a" to listOf("v1", "v2", "v2")).toString() shouldBe "a=v1&a=v2"

        // the second line will overwrite the first one
        QueryBuilder.of(
            "a" to listOf(mapOf("aa" to "aa", "aaa" to "aaa")),
            "a" to listOf(mapOf("bb" to "bb", "bbb" to "bbb"))
        ).toString() shouldBe "a.bb=bb&a.bbb=bbb"
    }
}
