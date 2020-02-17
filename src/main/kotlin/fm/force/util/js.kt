package fm.force.util

inline fun <T> jsApply(init: dynamic, cb: T.() -> Unit): T {
    cb(init.unsafeCast<T>())
    return init.unsafeCast<T>()
}

inline fun <T> jsLet(init: dynamic, cb: (T) -> Unit): T {
    cb(init.unsafeCast<T>())
    return init.unsafeCast<T>()
}
