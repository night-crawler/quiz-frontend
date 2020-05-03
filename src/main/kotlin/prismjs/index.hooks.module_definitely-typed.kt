@file:JsQualifier("hooks")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package prismjs

external interface HookEnvironmentMap {
    operator fun get(key: String): Any?
    operator fun set(key: String, value: Any?)
    var complete: ElementHighlightedEnvironment
//    var wrap: U /* U & Required<Any> */
    var wrap: dynamic
}

external interface RegisteredHooks {
    @nativeGetter
    operator fun get(hook: String): Array<HookCallback>?

    @nativeSetter
    operator fun set(hook: String, value: Array<HookCallback>)
}

external var all: RegisteredHooks

external fun <K : Any> add(name: K, callback: (env: Any) -> Unit)

external fun add(name: String, callback: HookCallback)

external fun <K : Any> run(name: K, env: Any)

external fun run(name: String, env: Environment)
