@file:JsModule("history")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package history

external interface MemoryHistory<HistoryLocationState> : History<HistoryLocationState> {
    var index: Number
    var entries: Array<Location<HistoryLocationState>>
    fun canGo(n: Number): Boolean
}

external fun <S> createMemoryHistory(options: MemoryHistoryBuildOptions = definedExternally): MemoryHistory<S>
