package fm.force.ui.util

import kotlin.js.Json
import kotlin.reflect.KProperty
import org.w3c.dom.Node
import org.w3c.dom.NodeList

inline fun <T> jsApply(init: dynamic = js("{}"), cb: T.() -> Unit): T {
    cb(init.unsafeCast<T>())
    return init.unsafeCast<T>()
}

inline fun <T> jsLet(init: dynamic = js("{}"), cb: (T) -> Unit): T {
    cb(init.unsafeCast<T>())
    return init.unsafeCast<T>()
}

class DynamicIterator<T>(private val obj: dynamic) : Iterator<T> {
    private var pointer: Int = 0
    private val length: Int = obj.length as Int
    override fun hasNext(): Boolean = pointer < length
    override fun next(): T = obj[pointer++].unsafeCast<T>()
}

fun NodeList.iterator() =
    DynamicIterator<Node>(this)

fun <K, V> Map<K, V>.toJson(): Json {
    val res = js("{}")
    for ((k, v) in this) {
        res[k.toString()] = v
    }
    return res.unsafeCast<Json>()
}

fun <K, V> Map<K, V>.toPairs() = entries.map { (k, v) -> k to v }.toTypedArray()

external fun atob(encoded: String): String
external fun btoa(raw: String): String

operator fun <T> Json.getValue(thisRef: Any?, property: KProperty<*>): T? {
    return this[property.name] as T?
}
