package fm.force.util

external fun decodeURIComponent(encoded: String): String
external fun encodeURIComponent(raw: String): String

external fun decodeURI(encoded: String): String
external fun encodeURI(raw: String): String


class QueryBuilder(vararg init: Pair<String, Collection<Any?>>) {
    companion object {
        fun of(vararg init: Pair<String, Any>) = QueryBuilder(
            *init
                .map { (key, value) ->
                    val transformedValue = when(value) {
                        is Collection<*> -> value.toMutableSet()
                        else -> mutableSetOf(value)
                    }

                    key to transformedValue
                }
                .toTypedArray()
        )

        fun of(init: Map<String, Any>) = of(
            *init.entries.map { (key, value) -> key to value }.toTypedArray()
        )
    }

    private val queryMap = init.map { (key, value) ->
        key to value.map { it.toString() }.toMutableSet()
    }.toMap(mutableMapOf())

    fun append(key: String, value: String): QueryBuilder {
        queryMap.getOrPut(key, { mutableSetOf() }).add(value)
        return this
    }

    override fun toString(): String {
        val kvPairs = mutableListOf<String>()
        queryMap.forEach { (key, values) ->
            values.forEach { value ->
                kvPairs.add("$key=${encodeURIComponent(value)}")
            }
        }
        return kvPairs.joinToString("&")
    }
}
