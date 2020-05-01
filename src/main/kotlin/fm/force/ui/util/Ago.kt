package fm.force.ui.util

import date.fns.formatDistance
import kotlin.js.Date

fun ago(dt: Date, prefix: String = "Created", suffix: String = "ago") = listOf(
    prefix,
    formatDistance(dt, Date()),
    suffix
).filterNot { it.isEmpty() }.joinToString(" ")
