package fm.force.ui.util


typealias Escape = (String) -> String

private val defaultEscape: Escape = { it }

fun Any?.materialize(prefix: String = "", escape: Escape = defaultEscape): List<String> {
    val resultValues = mutableListOf<String>()

    if (prefix != encodeURIComponent(prefix)) {
        throw IllegalArgumentException("Prefix $prefix must be uri-compliant")
    }

    when (this) {
        is Collection<*> -> resultValues += this.materialize(prefix)
        is Map<*, *> -> resultValues += this.materialize(prefix)
        null -> resultValues.add("$prefix=")
        else -> resultValues.add("$prefix=${escape(toString())}")
    }
    return resultValues
}

fun Collection<*>.materialize(prefix: String = "", escape: Escape = defaultEscape): List<String> {
    val resultValues = mutableListOf<String>()
    if (isEmpty()) {
        resultValues += null.materialize(prefix)
    }
    forEachIndexed { index, any ->
        val completePrefix = listOf(prefix, "$index").filter { it != "" }.joinToString(".")
        resultValues += any.materialize(completePrefix, escape)
    }
    return resultValues
}

fun Map<*, *>.materialize(prefix: String = "", escape: Escape = defaultEscape): List<String> {
    val resultValues = mutableListOf<String>()
    entries.forEach { (mKey, mValue) ->
        if (mKey == null) throw IllegalStateException("Keys of nested objects must not be null; prefix=$prefix")
        if (mKey == "") throw IllegalStateException("Keys of nested objects must not be empty; prefix=$prefix")

        val completePrefix = listOf(prefix, mKey).filter { it != "" }.joinToString(".")
        resultValues += mValue.materialize(completePrefix, escape)
    }
    return resultValues
}
