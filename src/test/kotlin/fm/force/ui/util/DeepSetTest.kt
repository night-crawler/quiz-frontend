package fm.force.ui.util

import kotlin.test.Test
import kotlin.test.assertEquals

class DeepSetTest {
    @Test
    fun createSampleArray() {
        val result = deepSet("[2]", 100500)
        console.log(JSON.stringify(result))
        assertEquals(100500, result[2])
    }

    @Test
    fun patchSampleArray() {
        val sample = js(
            """[
            {sample: 1},
            {sample: 2},
            {sample: 3, some: [0, 1]}
        ]"""
        )
        val result = deepSet("[1].sample", 100500, sample)
        console.log(JSON.stringify(result))
        assertEquals(100500, result[1].sample)

        // the rest must remain untouched
        assertEquals(1, result[0].sample)
        assertEquals(3, result[2].sample)

        deepSet("[2].some[3]", 100500, result)
        console.log(JSON.stringify(result))

        assertEquals(100500, result[2].some[3])
        assertEquals(0, result[2].some[0])
        assertEquals(1, result[2].some[1])
    }

    @Test
    fun createSampleObject() {
        val result = deepSet("sample", 100500)
        console.log(JSON.stringify(result))
        assertEquals(100500, result.sample)
    }

    @Test
    fun createNestedArray() {
        val result = deepSet("sample.deep.array[3].something", 100500)
        assertEquals(100500, result.sample.deep.array[3].something)
    }

    @Test
    fun createAndPatch() {
        val result = deepSet("sample.deep.array[3].[1].something", 100500)
        console.log(JSON.stringify(result))
        assertEquals(100500, result.sample.deep.array[3][1].something)

        deepSet("sample.deep.array[2].[1].nothing", true, result)
        assertEquals(true, result.sample.deep.array[2][1].nothing)

        // it must not erase anything
        assertEquals(100500, result.sample.deep.array[3][1].something)
    }

    @Test
    fun createTerminalArray() {
        val result = deepSet("sample[9]", 100500)
        console.log(JSON.stringify(result))
        assertEquals(100500, result.sample[9])
    }

    @Test
    fun deepUpdatePreservingExistingValues() {
        val sample = js("""{"dont": {"touch": {"me": [100500]}}}""")
        deepSet("dont.kill.me", 100500, sample)
        console.log(JSON.stringify(sample))
        assertEquals(100500, sample.dont.kill.me)
        assertEquals(100500, sample.dont.touch.me[0])

        deepSet("dont.frame[2].me", 100500, sample)
        assertEquals(100500, sample.dont.frame[2].me)
        assertEquals(100500, sample.dont.touch.me[0])

        deepSet("dont.touch.me[3].[2]", 100500, sample)
        console.log(JSON.stringify(sample))
        assertEquals(100500, sample.dont.touch.me[0])
        assertEquals(100500, sample.dont.touch.me[3][2])
    }
}
