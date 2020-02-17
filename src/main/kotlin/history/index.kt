@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package history

external interface History<HistoryLocationState> : org.w3c.dom.History {
    var action: String /* 'PUSH' | 'POP' | 'REPLACE' */
    var location: Location<HistoryLocationState>
    fun push(path: Path, state: HistoryLocationState = definedExternally)
    fun push(location: LocationDescriptorObject<HistoryLocationState>)
    fun replace(path: Path, state: HistoryLocationState = definedExternally)
    fun replace(location: LocationDescriptorObject<HistoryLocationState>)
    fun go(n: Number)
    fun goBack()
    fun goForward()
    fun block(prompt: Boolean = definedExternally): UnregisterCallback
    fun block(prompt: String = definedExternally): UnregisterCallback
    fun block(prompt: TransitionPromptHook<HistoryLocationState> = definedExternally): UnregisterCallback
    fun listen(listener: LocationListener<HistoryLocationState>): UnregisterCallback
    fun createHref(location: LocationDescriptorObject<HistoryLocationState>): Href
    fun block(): UnregisterCallback
}

external interface Location<S> : org.w3c.dom.Location {
    var state: S?
    var key: LocationKey?
        get() = definedExternally
        set(value) = definedExternally
}
