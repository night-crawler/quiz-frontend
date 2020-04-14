package fm.force.ui.util

fun deepSet(path: String, value: Any?, destination: dynamic = js("{}")): dynamic {
    // we always start processing the beginning of the sequence
    val parts = path.split(".", limit = 2)
    val currentKey = parts[0]

    // it means we're handling a key that stores an array of elements
    if ("[" in currentKey) {
        val realKeyName = currentKey.split("[")[0]
        val index = currentKey.split("[", "]").last { it.isNotEmpty() }.toInt()

        // We should try to take an existing element by its realKeyName and not to erase anything.
        // If realKeyName is empty, then it's an array-in-array case
        val jsArray = when {
            realKeyName.isNotBlank() && destination[realKeyName] != undefined -> destination[realKeyName]
            // we can gen an array in destination when the path starts with it: [1].sample
            destination.constructor.name == "Array" -> destination
            else -> js("[]")
        }

        // size == 1 means we are handling the terminal sequence
        if (parts.size == 1) {
            jsArray[index] = value
        } else {
            // if we have an array of objects, we must preserve the existing object's keys too
            val existing = if (jsArray[index] != undefined && jsArray[index].constructor.name == "Object") {
                jsArray[index]
            } else js("{}")
            jsArray[index] = deepSet(parts[1], value, existing)
        }

        // this is an array-in-array case, so it must not pollute destination by setting anything by ""
        if (realKeyName == "") {
            // do an early exit without updating of a destination, because the key we have is useless
            return jsArray
        } else {
            destination[realKeyName] = jsArray
        }

    } else {
        val existing = if (destination[currentKey] != undefined) {
            destination[currentKey]
        } else js("{}")

        if (parts.size == 1) {
            destination[currentKey] = value
        } else {
            destination[currentKey] = deepSet(parts[1], value, existing)
        }
    }
    return destination
}
