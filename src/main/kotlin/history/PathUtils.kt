@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package history

external fun addLeadingSlash(path: Path): Path

external fun stripLeadingSlash(path: Path): Path

external fun hasBasename(path: Path): Boolean

external fun stripBasename(path: Path, prefix: String): Path

external fun stripTrailingSlash(path: Path): Path

external fun <S> parsePath(path: Path): Location<S>

external fun <S> createPath(location: LocationDescriptorObject<S>): Path
