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

val Any?.jsLength get() = this.asDynamic().length

val Any?.isFalsy get() = this == undefined || jsLength == 0 || jsLength == undefined

class DynamicIterator<T>(private val obj: dynamic) : Iterator<T> {
    private var pointer: Int = 0
    private val length: Int = when (obj) {
        is Collection<*> -> obj.size
        else -> obj.length as Int
    }

    override fun hasNext(): Boolean = pointer < length
    override fun next(): T = obj[pointer++].unsafeCast<T>()
}

fun <T> Any.dynamicIterator() = DynamicIterator<T>(this)

fun NodeList.iterator() =
    DynamicIterator<Node>(this)

fun Map<*, *>.toJson(isRecursive: Boolean = true): Json {
    val res = js("{}")
    for ((key, value) in this) {
        res[key.toString()] = when {
            isRecursive && value is Collection<*> -> value.toJson(isRecursive = isRecursive)
            isRecursive && value is Map<*, *> -> value.toJson(isRecursive = isRecursive)
            else -> value
        }
    }
    return res.unsafeCast<Json>()
}

fun Collection<*>.toJson(isRecursive: Boolean = true): Json {
    val res = js("[]")
    this.mapIndexed { index, value ->
        res[index] = when {
            isRecursive && value is Collection<*> -> value.toJson(isRecursive = isRecursive)
            isRecursive && value is Map<*, *> -> value.toJson(isRecursive = isRecursive)
            else -> value
        }
    }
    return res.unsafeCast<Json>()
}

fun <K, V> Map<K, V>.toPairs() = entries.map { (k, v) -> k to v }.toTypedArray()

external fun atob(encoded: String): String
external fun btoa(raw: String): String

operator fun <T> Json.getValue(thisRef: Any?, property: KProperty<*>): T? {
    return this[property.name] as T?
}

external fun decodeURIComponent(encoded: String): String
external fun encodeURIComponent(raw: String): String
external fun decodeURI(encoded: String): String
external fun encodeURI(raw: String): String
