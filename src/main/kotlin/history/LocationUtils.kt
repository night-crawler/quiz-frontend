@file:JsModule("history")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package history

external fun <S> locationsAreEqual(lv: dynamic, rv: dynamic): Boolean

external fun <S> createLocation(
    path: dynamic,
    state: S = definedExternally,
    key: LocationKey = definedExternally,
    currentLocation: Location<S> = definedExternally
): Location<S>
