@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package prismjs

typealias HookCallback = (env: Environment) -> Unit

typealias HookTypes = Any

typealias ElementEnvironment = Any

typealias ElementHighlightedEnvironment = ElementEnvironment

typealias TokenizeEnvironment = Any
