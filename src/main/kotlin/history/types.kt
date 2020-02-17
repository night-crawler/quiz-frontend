package history

data class BrowserHistoryBuildOptions(
    var basename: String? = undefined,
    var forceRefresh: Boolean? = undefined,
    var getUserConfirmation: Any? = undefined,
    var keyLength: Number? = undefined
)

data class HashHistoryBuildOptions(
    var basename: String? = undefined,
    var hashType: String = "hashbang", /* 'hashbang' | 'noslash' | 'slash' */
    var getUserConfirmation: Any? = undefined
)

data class LocationDescriptorObject<S>(
    var pathname: Pathname? = undefined,
    var search: Search? = undefined,
    var state: S? = undefined,
    var hash: Hash? = undefined,
    var key: LocationKey? = undefined
)

data class MemoryHistoryBuildOptions(
    var getUserConfirmation: Any? = undefined,
    var initialEntries: Array<String>? = undefined,
    var initialIndex: Number? = undefined,
    var keyLength: Number? = undefined
)

typealias LocationListener<S> = (location: Location<S>, action: String /* 'PUSH' | 'POP' | 'REPLACE' */) -> Unit
typealias LocationState = Any?
typealias Path = String
typealias Pathname = String
typealias Search = String
typealias TransitionHook<S> = (location: Location<S>, callback: (result: Any) -> Unit) -> Any
typealias TransitionPromptHook<S> = (location: Location<S>, action: String /* 'PUSH' | 'POP' | 'REPLACE' */) -> dynamic
typealias Hash = String
typealias Href = String
typealias UnregisterCallback = () -> Unit
typealias LocationKey = String
