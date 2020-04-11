package fm.force.ui.util

class QueryBuilder(vararg init: Pair<String, Collection<Any?>>) {
    companion object {
        fun of(vararg init: Pair<String, Any>) = QueryBuilder(
            *init
                .map { (key, value) ->
                    val setWrappedValue = when (value) {
                        is Collection<*> -> value.toMutableSet()
                        else -> mutableSetOf(value)
                    }

                    key to setWrappedValue
                }
                .toTypedArray()
        )

        fun of(init: Map<String, Any>) = of(
            *init.entries.map { (key, value) -> key to value }.toTypedArray()
        )
    }

    private val queryMap = init.map { (key, value) ->
        key to value.toMutableSet()
    }.toMap(mutableMapOf())

    fun add(key: String, value: Any?): QueryBuilder {
        queryMap.getOrPut(key, { mutableSetOf() }).add(value)
        return this
    }

    fun appendTo(uri: String): String {
        val repr = toString()
        if (repr.isNotBlank())
            return "$uri?$repr"
        return uri
    }

    override fun toString(): String {
        val kvPairs = mutableListOf<String>()
        queryMap.forEach { (key, values) ->
            if (values.isEmpty())
                kvPairs.add("$key=")
            else
                values.sortedBy { it.toString() }.forEach { value ->
                    kvPairs += value.materialize(key, escape = ::encodeURIComponent)
                }
        }
        return kvPairs.joinToString("&")
    }
}
