package fm.force.ui.effect

import react.useEffectWithCleanup
import react.useState
import kotlin.browser.window

fun <T> useDebounce(value: T, delay: Int): T {
    val (debouncedValue, setDebouncedValue) = useState(value)
    useEffectWithCleanup(listOf(value)) {
        val handle = window.setTimeout({
            setDebouncedValue(value)
        }, delay)
        return@useEffectWithCleanup { window.clearTimeout(handle) }
    }

    return debouncedValue
}
