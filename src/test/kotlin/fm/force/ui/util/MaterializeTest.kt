package fm.force.ui.util

import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class MaterializeTest {
    @Test
    fun testMaterialize() {
        val sampleMap = mapOf(
            "a" to listOf(listOf("sample")),
            "b" to listOf<String>(),
            "c" to mapOf("nest1" to "nest2"),
            "d" to listOf("d", mapOf("really" to "sorry")),
            "e" to null,
            "f" to "",
            "j" to "i.l.l.e.g.a.l==true",
            1 to 2
        )
        // ensure we have not lost anything
        sampleMap.materialize() shouldHaveAtLeastSize sampleMap.size

        // ensure encodeURIComponent does some real work
        val materialized = sampleMap.materialize(escape = ::encodeURIComponent).toSet()
        materialized shouldBe setOf(
            "a.0.0=sample",
            "b=",
            "c.nest1=nest2",
            "d.0=d",
            "d.1.really=sorry",
            "e=",
            "f=",
            "j=i.l.l.e.g.a.l%3D%3Dtrue",
            "1=2"
        )
    }
}
