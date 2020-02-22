package fm.force.util

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
