@file:JsModule("date-fns")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package date.fns

import kotlin.js.Date

external fun add(date: Date, duration: Duration): Date

external fun add(date: Number, duration: Duration): Date

external fun addBusinessDays(date: Date, amount: Number): Date

external fun addBusinessDays(date: Number, amount: Number): Date

external fun addDays(date: Date, amount: Number): Date

external fun addDays(date: Number, amount: Number): Date

external fun addHours(date: Date, amount: Number): Date

external fun addHours(date: Number, amount: Number): Date

external fun addISOWeekYears(date: Date, amount: Number): Date

external fun addISOWeekYears(date: Number, amount: Number): Date

external fun addMilliseconds(date: Date, amount: Number): Date

external fun addMilliseconds(date: Number, amount: Number): Date

external fun addMinutes(date: Date, amount: Number): Date

external fun addMinutes(date: Number, amount: Number): Date

external fun addMonths(date: Date, amount: Number): Date

external fun addMonths(date: Number, amount: Number): Date

external fun addQuarters(date: Date, amount: Number): Date

external fun addQuarters(date: Number, amount: Number): Date

external fun addSeconds(date: Date, amount: Number): Date

external fun addSeconds(date: Number, amount: Number): Date

external fun addWeeks(date: Date, amount: Number): Date

external fun addWeeks(date: Number, amount: Number): Date

external fun addYears(date: Date, amount: Number): Date

external fun addYears(date: Number, amount: Number): Date

external interface `T$3` {
    var inclusive: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun areIntervalsOverlapping(
    intervalLeft: Interval,
    intervalRight: Interval,
    options: `T$3` = definedExternally
): Boolean

external fun closestIndexTo(dateToCompare: Date, datesArray: Array<dynamic /* Date | Number */>): Number

external fun closestIndexTo(dateToCompare: Number, datesArray: Array<dynamic /* Date | Number */>): Number

external fun closestTo(dateToCompare: Date, datesArray: Array<dynamic /* Date | Number */>): Date

external fun closestTo(dateToCompare: Number, datesArray: Array<dynamic /* Date | Number */>): Date

external fun compareAsc(dateLeft: Date, dateRight: Date): Number

external fun compareAsc(dateLeft: Date, dateRight: Number): Number

external fun compareAsc(dateLeft: Number, dateRight: Date): Number

external fun compareAsc(dateLeft: Number, dateRight: Number): Number

external fun compareDesc(dateLeft: Date, dateRight: Date): Number

external fun compareDesc(dateLeft: Date, dateRight: Number): Number

external fun compareDesc(dateLeft: Number, dateRight: Date): Number

external fun compareDesc(dateLeft: Number, dateRight: Number): Number

external fun differenceInBusinessDays(dateLeft: Date, dateRight: Date): Number

external fun differenceInBusinessDays(dateLeft: Date, dateRight: Number): Number

external fun differenceInBusinessDays(dateLeft: Number, dateRight: Date): Number

external fun differenceInBusinessDays(dateLeft: Number, dateRight: Number): Number

external fun differenceInCalendarDays(dateLeft: Date, dateRight: Date): Number

external fun differenceInCalendarDays(dateLeft: Date, dateRight: Number): Number

external fun differenceInCalendarDays(dateLeft: Number, dateRight: Date): Number

external fun differenceInCalendarDays(dateLeft: Number, dateRight: Number): Number

external fun differenceInCalendarISOWeeks(dateLeft: Date, dateRight: Date): Number

external fun differenceInCalendarISOWeeks(dateLeft: Date, dateRight: Number): Number

external fun differenceInCalendarISOWeeks(dateLeft: Number, dateRight: Date): Number

external fun differenceInCalendarISOWeeks(dateLeft: Number, dateRight: Number): Number

external fun differenceInCalendarISOWeekYears(dateLeft: Date, dateRight: Date): Number

external fun differenceInCalendarISOWeekYears(dateLeft: Date, dateRight: Number): Number

external fun differenceInCalendarISOWeekYears(dateLeft: Number, dateRight: Date): Number

external fun differenceInCalendarISOWeekYears(dateLeft: Number, dateRight: Number): Number

external fun differenceInCalendarMonths(dateLeft: Date, dateRight: Date): Number

external fun differenceInCalendarMonths(dateLeft: Date, dateRight: Number): Number

external fun differenceInCalendarMonths(dateLeft: Number, dateRight: Date): Number

external fun differenceInCalendarMonths(dateLeft: Number, dateRight: Number): Number

external fun differenceInCalendarQuarters(dateLeft: Date, dateRight: Date): Number

external fun differenceInCalendarQuarters(dateLeft: Date, dateRight: Number): Number

external fun differenceInCalendarQuarters(dateLeft: Number, dateRight: Date): Number

external fun differenceInCalendarQuarters(dateLeft: Number, dateRight: Number): Number

external interface `T$4` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
}

external fun differenceInCalendarWeeks(dateLeft: Date, dateRight: Date, options: `T$4` = definedExternally): Number

external fun differenceInCalendarWeeks(dateLeft: Date, dateRight: Number, options: `T$4` = definedExternally): Number

external fun differenceInCalendarWeeks(dateLeft: Number, dateRight: Date, options: `T$4` = definedExternally): Number

external fun differenceInCalendarWeeks(dateLeft: Number, dateRight: Number, options: `T$4` = definedExternally): Number

external fun differenceInCalendarYears(dateLeft: Date, dateRight: Date): Number

external fun differenceInCalendarYears(dateLeft: Date, dateRight: Number): Number

external fun differenceInCalendarYears(dateLeft: Number, dateRight: Date): Number

external fun differenceInCalendarYears(dateLeft: Number, dateRight: Number): Number

external fun differenceInDays(dateLeft: Date, dateRight: Date): Number

external fun differenceInDays(dateLeft: Date, dateRight: Number): Number

external fun differenceInDays(dateLeft: Number, dateRight: Date): Number

external fun differenceInDays(dateLeft: Number, dateRight: Number): Number

external fun differenceInHours(dateLeft: Date, dateRight: Date): Number

external fun differenceInHours(dateLeft: Date, dateRight: Number): Number

external fun differenceInHours(dateLeft: Number, dateRight: Date): Number

external fun differenceInHours(dateLeft: Number, dateRight: Number): Number

external fun differenceInISOWeekYears(dateLeft: Date, dateRight: Date): Number

external fun differenceInISOWeekYears(dateLeft: Date, dateRight: Number): Number

external fun differenceInISOWeekYears(dateLeft: Number, dateRight: Date): Number

external fun differenceInISOWeekYears(dateLeft: Number, dateRight: Number): Number

external fun differenceInMilliseconds(dateLeft: Date, dateRight: Date): Number

external fun differenceInMilliseconds(dateLeft: Date, dateRight: Number): Number

external fun differenceInMilliseconds(dateLeft: Number, dateRight: Date): Number

external fun differenceInMilliseconds(dateLeft: Number, dateRight: Number): Number

external fun differenceInMinutes(dateLeft: Date, dateRight: Date): Number

external fun differenceInMinutes(dateLeft: Date, dateRight: Number): Number

external fun differenceInMinutes(dateLeft: Number, dateRight: Date): Number

external fun differenceInMinutes(dateLeft: Number, dateRight: Number): Number

external fun differenceInMonths(dateLeft: Date, dateRight: Date): Number

external fun differenceInMonths(dateLeft: Date, dateRight: Number): Number

external fun differenceInMonths(dateLeft: Number, dateRight: Date): Number

external fun differenceInMonths(dateLeft: Number, dateRight: Number): Number

external fun differenceInQuarters(dateLeft: Date, dateRight: Date): Number

external fun differenceInQuarters(dateLeft: Date, dateRight: Number): Number

external fun differenceInQuarters(dateLeft: Number, dateRight: Date): Number

external fun differenceInQuarters(dateLeft: Number, dateRight: Number): Number

external fun differenceInSeconds(dateLeft: Date, dateRight: Date): Number

external fun differenceInSeconds(dateLeft: Date, dateRight: Number): Number

external fun differenceInSeconds(dateLeft: Number, dateRight: Date): Number

external fun differenceInSeconds(dateLeft: Number, dateRight: Number): Number

external fun differenceInWeeks(dateLeft: Date, dateRight: Date): Number

external fun differenceInWeeks(dateLeft: Date, dateRight: Number): Number

external fun differenceInWeeks(dateLeft: Number, dateRight: Date): Number

external fun differenceInWeeks(dateLeft: Number, dateRight: Number): Number

external fun differenceInYears(dateLeft: Date, dateRight: Date): Number

external fun differenceInYears(dateLeft: Date, dateRight: Number): Number

external fun differenceInYears(dateLeft: Number, dateRight: Date): Number

external fun differenceInYears(dateLeft: Number, dateRight: Number): Number

external interface `T$5` {
    var step: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external fun eachDayOfInterval(interval: Interval, options: `T$5` = definedExternally): Array<Date>

external fun eachMonthOfInterval(interval: Interval): Array<Date>

external fun eachWeekendOfInterval(interval: Interval): Array<Date>

external fun eachWeekendOfMonth(date: Date): Array<Date>

external fun eachWeekendOfMonth(date: Number): Array<Date>

external fun eachWeekendOfYear(date: Date): Array<Date>

external fun eachWeekendOfYear(date: Number): Array<Date>

external fun eachWeekOfInterval(interval: Interval, options: `T$4` = definedExternally): Array<Date>

external fun eachYearOfInterval(interval: Interval): Array<Date>

external fun endOfDay(date: Date): Date

external fun endOfDay(date: Number): Date

external interface `T$6` {
    var additionalDigits: String /* 0 | 1 | 2 */
}

external fun endOfDecade(date: Date, options: `T$6` = definedExternally): Date

external fun endOfDecade(date: Number, options: `T$6` = definedExternally): Date

external fun endOfHour(date: Date): Date

external fun endOfHour(date: Number): Date

external fun endOfISOWeek(date: Date): Date

external fun endOfISOWeek(date: Number): Date

external fun endOfISOWeekYear(date: Date): Date

external fun endOfISOWeekYear(date: Number): Date

external fun endOfMinute(date: Date): Date

external fun endOfMinute(date: Number): Date

external fun endOfMonth(date: Date): Date

external fun endOfMonth(date: Number): Date

external fun endOfQuarter(date: Date): Date

external fun endOfQuarter(date: Number): Date

external fun endOfSecond(date: Date): Date

external fun endOfSecond(date: Number): Date

external fun endOfToday(): Date

external fun endOfTomorrow(): Date

external fun endOfWeek(date: Date, options: `T$4` = definedExternally): Date

external fun endOfWeek(date: Number, options: `T$4` = definedExternally): Date

external fun endOfYear(date: Date): Date

external fun endOfYear(date: Number): Date

external fun endOfYesterday(): Date

external interface `T$7` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var firstWeekContainsDate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var useAdditionalWeekYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var useAdditionalDayOfYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun format(date: Date, format: String, options: `T$7` = definedExternally): String

external fun format(date: Number, format: String, options: `T$7` = definedExternally): String

external interface `T$8` {
    var includeSeconds: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var addSuffix: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

external fun formatDistance(date: Date, baseDate: Date, options: `T$8` = definedExternally): String

external fun formatDistance(date: Date, baseDate: Number, options: `T$8` = definedExternally): String

external fun formatDistance(date: Number, baseDate: Date, options: `T$8` = definedExternally): String

external fun formatDistance(date: Number, baseDate: Number, options: `T$8` = definedExternally): String

external interface `T$9` {
    var addSuffix: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var unit: String /* 'second' | 'minute' | 'hour' | 'day' | 'month' | 'year' */
    var roundingMethod: String /* 'floor' | 'ceil' | 'round' */
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

external fun formatDistanceStrict(date: Date, baseDate: Date, options: `T$9` = definedExternally): String

external fun formatDistanceStrict(date: Date, baseDate: Number, options: `T$9` = definedExternally): String

external fun formatDistanceStrict(date: Number, baseDate: Date, options: `T$9` = definedExternally): String

external fun formatDistanceStrict(date: Number, baseDate: Number, options: `T$9` = definedExternally): String

external fun formatDistanceToNow(date: Date, options: `T$8` = definedExternally): String

external fun formatDistanceToNow(date: Number, options: `T$8` = definedExternally): String

external fun formatDistanceToNowStrict(date: Date, options: `T$9` = definedExternally): String

external fun formatDistanceToNowStrict(date: Number, options: `T$9` = definedExternally): String

external interface `T$10` {
    var format: String /* 'extended' | 'basic' */
    var representation: String /* 'complete' | 'date' | 'time' */
}

external fun formatISO(date: Date, options: `T$10` = definedExternally): String

external fun formatISO(date: Number, options: `T$10` = definedExternally): String

external fun formatISO9075(date: Date, options: `T$10` = definedExternally): String

external fun formatISO9075(date: Number, options: `T$10` = definedExternally): String

external fun formatRelative(date: Date, baseDate: Date, options: `T$4` = definedExternally): String

external fun formatRelative(date: Date, baseDate: Number, options: `T$4` = definedExternally): String

external fun formatRelative(date: Number, baseDate: Date, options: `T$4` = definedExternally): String

external fun formatRelative(date: Number, baseDate: Number, options: `T$4` = definedExternally): String

external interface `T$11` {
    var fractionDigits: String /* 0 | 1 | 2 | 3 */
}

external fun formatRFC3339(date: Date, options: `T$11` = definedExternally): String

external fun formatRFC3339(date: Number, options: `T$11` = definedExternally): String

external fun formatRFC7231(date: Date): String

external fun formatRFC7231(date: Number): String

external fun fromUnixTime(unixTime: Number): Date

external fun getDate(date: Date): Number

external fun getDate(date: Number): Number

external fun getDay(date: Date): String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */

external fun getDay(date: Number): String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */

external fun getDayOfYear(date: Date): Number

external fun getDayOfYear(date: Number): Number

external fun getDaysInMonth(date: Date): Number

external fun getDaysInMonth(date: Number): Number

external fun getDaysInYear(date: Date): Number

external fun getDaysInYear(date: Number): Number

external fun getDecade(date: Date): Number

external fun getDecade(date: Number): Number

external fun getHours(date: Date): Number

external fun getHours(date: Number): Number

external fun getISODay(date: Date): Number

external fun getISODay(date: Number): Number

external fun getISOWeek(date: Date): Number

external fun getISOWeek(date: Number): Number

external fun getISOWeeksInYear(date: Date): Number

external fun getISOWeeksInYear(date: Number): Number

external fun getISOWeekYear(date: Date): Number

external fun getISOWeekYear(date: Number): Number

external fun getMilliseconds(date: Date): Number

external fun getMilliseconds(date: Number): Number

external fun getMinutes(date: Date): Number

external fun getMinutes(date: Number): Number

external fun getMonth(date: Date): Number

external fun getMonth(date: Number): Number

external fun getOverlappingDaysInIntervals(intervalLeft: Interval, intervalRight: Interval): Number

external fun getQuarter(date: Date): Number

external fun getQuarter(date: Number): Number

external fun getSeconds(date: Date): Number

external fun getSeconds(date: Number): Number

external fun getTime(date: Date): Number

external fun getTime(date: Number): Number

external fun getUnixTime(date: Date): Number

external fun getUnixTime(date: Number): Number

external interface `T$12` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var firstWeekContainsDate: String /* 1 | 2 | 3 | 4 | 5 | 6 | 7 */
}

external fun getWeek(date: Date, options: `T$12` = definedExternally): Number

external fun getWeek(date: Number, options: `T$12` = definedExternally): Number

external fun getWeekOfMonth(date: Date, options: `T$4` = definedExternally): Number

external fun getWeekOfMonth(date: Number, options: `T$4` = definedExternally): Number

external fun getWeeksInMonth(date: Date, options: `T$4` = definedExternally): Number

external fun getWeeksInMonth(date: Number, options: `T$4` = definedExternally): Number

external fun getWeekYear(date: Date, options: `T$12` = definedExternally): Number

external fun getWeekYear(date: Number, options: `T$12` = definedExternally): Number

external fun getYear(date: Date): Number

external fun getYear(date: Number): Number

external fun isAfter(date: Date, dateToCompare: Date): Boolean

external fun isAfter(date: Date, dateToCompare: Number): Boolean

external fun isAfter(date: Number, dateToCompare: Date): Boolean

external fun isAfter(date: Number, dateToCompare: Number): Boolean

external fun isBefore(date: Date, dateToCompare: Date): Boolean

external fun isBefore(date: Date, dateToCompare: Number): Boolean

external fun isBefore(date: Number, dateToCompare: Date): Boolean

external fun isBefore(date: Number, dateToCompare: Number): Boolean

external fun isDate(value: Any): Boolean

external fun isEqual(dateLeft: Date, dateRight: Date): Boolean

external fun isEqual(dateLeft: Date, dateRight: Number): Boolean

external fun isEqual(dateLeft: Number, dateRight: Date): Boolean

external fun isEqual(dateLeft: Number, dateRight: Number): Boolean

external fun isExists(year: Number, month: Number, day: Number): Boolean

external fun isFirstDayOfMonth(date: Date): Boolean

external fun isFirstDayOfMonth(date: Number): Boolean

external fun isFriday(date: Date): Boolean

external fun isFriday(date: Number): Boolean

external fun isFuture(date: Date): Boolean

external fun isFuture(date: Number): Boolean

external fun isLastDayOfMonth(date: Date): Boolean

external fun isLastDayOfMonth(date: Number): Boolean

external fun isLeapYear(date: Date): Boolean

external fun isLeapYear(date: Number): Boolean

external fun isMonday(date: Date): Boolean

external fun isMonday(date: Number): Boolean

external fun isPast(date: Date): Boolean

external fun isPast(date: Number): Boolean

external fun isSameDay(dateLeft: Date, dateRight: Date): Boolean

external fun isSameDay(dateLeft: Date, dateRight: Number): Boolean

external fun isSameDay(dateLeft: Number, dateRight: Date): Boolean

external fun isSameDay(dateLeft: Number, dateRight: Number): Boolean

external fun isSameHour(dateLeft: Date, dateRight: Date): Boolean

external fun isSameHour(dateLeft: Date, dateRight: Number): Boolean

external fun isSameHour(dateLeft: Number, dateRight: Date): Boolean

external fun isSameHour(dateLeft: Number, dateRight: Number): Boolean

external fun isSameISOWeek(dateLeft: Date, dateRight: Date): Boolean

external fun isSameISOWeek(dateLeft: Date, dateRight: Number): Boolean

external fun isSameISOWeek(dateLeft: Number, dateRight: Date): Boolean

external fun isSameISOWeek(dateLeft: Number, dateRight: Number): Boolean

external fun isSameISOWeekYear(dateLeft: Date, dateRight: Date): Boolean

external fun isSameISOWeekYear(dateLeft: Date, dateRight: Number): Boolean

external fun isSameISOWeekYear(dateLeft: Number, dateRight: Date): Boolean

external fun isSameISOWeekYear(dateLeft: Number, dateRight: Number): Boolean

external fun isSameMinute(dateLeft: Date, dateRight: Date): Boolean

external fun isSameMinute(dateLeft: Date, dateRight: Number): Boolean

external fun isSameMinute(dateLeft: Number, dateRight: Date): Boolean

external fun isSameMinute(dateLeft: Number, dateRight: Number): Boolean

external fun isSameMonth(dateLeft: Date, dateRight: Date): Boolean

external fun isSameMonth(dateLeft: Date, dateRight: Number): Boolean

external fun isSameMonth(dateLeft: Number, dateRight: Date): Boolean

external fun isSameMonth(dateLeft: Number, dateRight: Number): Boolean

external fun isSameQuarter(dateLeft: Date, dateRight: Date): Boolean

external fun isSameQuarter(dateLeft: Date, dateRight: Number): Boolean

external fun isSameQuarter(dateLeft: Number, dateRight: Date): Boolean

external fun isSameQuarter(dateLeft: Number, dateRight: Number): Boolean

external fun isSameSecond(dateLeft: Date, dateRight: Date): Boolean

external fun isSameSecond(dateLeft: Date, dateRight: Number): Boolean

external fun isSameSecond(dateLeft: Number, dateRight: Date): Boolean

external fun isSameSecond(dateLeft: Number, dateRight: Number): Boolean

external fun isSameWeek(dateLeft: Date, dateRight: Date, options: `T$4` = definedExternally): Boolean

external fun isSameWeek(dateLeft: Date, dateRight: Number, options: `T$4` = definedExternally): Boolean

external fun isSameWeek(dateLeft: Number, dateRight: Date, options: `T$4` = definedExternally): Boolean

external fun isSameWeek(dateLeft: Number, dateRight: Number, options: `T$4` = definedExternally): Boolean

external fun isSameYear(dateLeft: Date, dateRight: Date): Boolean

external fun isSameYear(dateLeft: Date, dateRight: Number): Boolean

external fun isSameYear(dateLeft: Number, dateRight: Date): Boolean

external fun isSameYear(dateLeft: Number, dateRight: Number): Boolean

external fun isSaturday(date: Date): Boolean

external fun isSaturday(date: Number): Boolean

external fun isSunday(date: Date): Boolean

external fun isSunday(date: Number): Boolean

external fun isThisHour(date: Date): Boolean

external fun isThisHour(date: Number): Boolean

external fun isThisISOWeek(date: Date): Boolean

external fun isThisISOWeek(date: Number): Boolean

external fun isThisMinute(date: Date): Boolean

external fun isThisMinute(date: Number): Boolean

external fun isThisMonth(date: Date): Boolean

external fun isThisMonth(date: Number): Boolean

external fun isThisQuarter(date: Date): Boolean

external fun isThisQuarter(date: Number): Boolean

external fun isThisSecond(date: Date): Boolean

external fun isThisSecond(date: Number): Boolean

external fun isThisWeek(date: Date, options: `T$4` = definedExternally): Boolean

external fun isThisWeek(date: Number, options: `T$4` = definedExternally): Boolean

external fun isThisYear(date: Date): Boolean

external fun isThisYear(date: Number): Boolean

external fun isThursday(date: Date): Boolean

external fun isThursday(date: Number): Boolean

external fun isToday(date: Date): Boolean

external fun isToday(date: Number): Boolean

external fun isTomorrow(date: Date): Boolean

external fun isTomorrow(date: Number): Boolean

external fun isTuesday(date: Date): Boolean

external fun isTuesday(date: Number): Boolean

external fun isValid(date: Any): Boolean

external fun isWednesday(date: Date): Boolean

external fun isWednesday(date: Number): Boolean

external fun isWeekend(date: Date): Boolean

external fun isWeekend(date: Number): Boolean

external fun isWithinInterval(date: Date, interval: Interval): Boolean

external fun isWithinInterval(date: Number, interval: Interval): Boolean

external fun isYesterday(date: Date): Boolean

external fun isYesterday(date: Number): Boolean

external fun lastDayOfDecade(date: Date): Date

external fun lastDayOfDecade(date: Number): Date

external fun lastDayOfISOWeek(date: Date): Date

external fun lastDayOfISOWeek(date: Number): Date

external fun lastDayOfISOWeekYear(date: Date): Date

external fun lastDayOfISOWeekYear(date: Number): Date

external fun lastDayOfMonth(date: Date): Date

external fun lastDayOfMonth(date: Number): Date

external fun lastDayOfQuarter(date: Date, options: `T$6` = definedExternally): Date

external fun lastDayOfQuarter(date: Number, options: `T$6` = definedExternally): Date

external fun lastDayOfWeek(date: Date, options: `T$4` = definedExternally): Date

external fun lastDayOfWeek(date: Number, options: `T$4` = definedExternally): Date

external fun lastDayOfYear(date: Date): Date

external fun lastDayOfYear(date: Number): Date

external fun lightFormat(date: Date, format: String): String

external fun lightFormat(date: Number, format: String): String

external fun max(datesArray: Array<dynamic /* Date | Number */>): Date

external fun min(datesArray: Array<dynamic /* Date | Number */>): Date

external interface `T$13` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var firstWeekContainsDate: String /* 1 | 2 | 3 | 4 | 5 | 6 | 7 */
    var useAdditionalWeekYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var useAdditionalDayOfYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external fun parse(
    dateString: String,
    formatString: String,
    referenceDate: Date,
    options: `T$13` = definedExternally
): Date

external fun parse(
    dateString: String,
    formatString: String,
    referenceDate: Number,
    options: `T$13` = definedExternally
): Date

external fun parseISO(argument: String, options: `T$6` = definedExternally): Date

external fun parseJSON(argument: String): Date

external fun parseJSON(argument: Number): Date

external fun parseJSON(argument: Date): Date

external interface `T$14` {
    var nearestTo: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external fun roundToNearestMinutes(date: Date, options: `T$14` = definedExternally): Date

external fun roundToNearestMinutes(date: Number, options: `T$14` = definedExternally): Date

external interface `T$15` {
    var year: Number?
        get() = definedExternally
        set(value) = definedExternally
    var month: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Number?
        get() = definedExternally
        set(value) = definedExternally
    var hours: Number?
        get() = definedExternally
        set(value) = definedExternally
    var minutes: Number?
        get() = definedExternally
        set(value) = definedExternally
    var seconds: Number?
        get() = definedExternally
        set(value) = definedExternally
    var milliseconds: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external fun set(date: Date, values: `T$15`): Date

external fun set(date: Number, values: `T$15`): Date

external fun setDate(date: Date, dayOfMonth: Number): Date

external fun setDate(date: Number, dayOfMonth: Number): Date

external fun setDay(date: Date, day: Number, options: `T$4` = definedExternally): Date

external fun setDay(date: Number, day: Number, options: `T$4` = definedExternally): Date

external fun setDayOfYear(date: Date, dayOfYear: Number): Date

external fun setDayOfYear(date: Number, dayOfYear: Number): Date

external fun setHours(date: Date, hours: Number): Date

external fun setHours(date: Number, hours: Number): Date

external fun setISODay(date: Date, day: Number): Date

external fun setISODay(date: Number, day: Number): Date

external fun setISOWeek(date: Date, isoWeek: Number): Date

external fun setISOWeek(date: Number, isoWeek: Number): Date

external fun setISOWeekYear(date: Date, isoWeekYear: Number): Date

external fun setISOWeekYear(date: Number, isoWeekYear: Number): Date

external fun setMilliseconds(date: Date, milliseconds: Number): Date

external fun setMilliseconds(date: Number, milliseconds: Number): Date

external fun setMinutes(date: Date, minutes: Number): Date

external fun setMinutes(date: Number, minutes: Number): Date

external fun setMonth(date: Date, month: Number): Date

external fun setMonth(date: Number, month: Number): Date

external fun setQuarter(date: Date, quarter: Number): Date

external fun setQuarter(date: Number, quarter: Number): Date

external fun setSeconds(date: Date, seconds: Number): Date

external fun setSeconds(date: Number, seconds: Number): Date

external fun setWeek(date: Date, week: Number, options: `T$12` = definedExternally): Date

external fun setWeek(date: Number, week: Number, options: `T$12` = definedExternally): Date

external fun setWeekYear(date: Date, weekYear: Number, options: `T$12` = definedExternally): Date

external fun setWeekYear(date: Number, weekYear: Number, options: `T$12` = definedExternally): Date

external fun setYear(date: Date, year: Number): Date

external fun setYear(date: Number, year: Number): Date

external fun startOfDay(date: Date): Date

external fun startOfDay(date: Number): Date

external fun startOfDecade(date: Date): Date

external fun startOfDecade(date: Number): Date

external fun startOfHour(date: Date): Date

external fun startOfHour(date: Number): Date

external fun startOfISOWeek(date: Date): Date

external fun startOfISOWeek(date: Number): Date

external fun startOfISOWeekYear(date: Date): Date

external fun startOfISOWeekYear(date: Number): Date

external fun startOfMinute(date: Date): Date

external fun startOfMinute(date: Number): Date

external fun startOfMonth(date: Date): Date

external fun startOfMonth(date: Number): Date

external fun startOfQuarter(date: Date): Date

external fun startOfQuarter(date: Number): Date

external fun startOfSecond(date: Date): Date

external fun startOfSecond(date: Number): Date

external fun startOfToday(): Date

external fun startOfTomorrow(): Date

external fun startOfWeek(date: Date, options: `T$4` = definedExternally): Date

external fun startOfWeek(date: Number, options: `T$4` = definedExternally): Date

external fun startOfWeekYear(date: Date, options: `T$12` = definedExternally): Date

external fun startOfWeekYear(date: Number, options: `T$12` = definedExternally): Date

external fun startOfYear(date: Date): Date

external fun startOfYear(date: Number): Date

external fun startOfYesterday(): Date

external fun sub(date: Date, duration: Duration): Date

external fun sub(date: Number, duration: Duration): Date

external fun subBusinessDays(date: Date, amount: Number): Date

external fun subBusinessDays(date: Number, amount: Number): Date

external fun subDays(date: Date, amount: Number): Date

external fun subDays(date: Number, amount: Number): Date

external fun subHours(date: Date, amount: Number): Date

external fun subHours(date: Number, amount: Number): Date

external fun subISOWeekYears(date: Date, amount: Number): Date

external fun subISOWeekYears(date: Number, amount: Number): Date

external fun subMilliseconds(date: Date, amount: Number): Date

external fun subMilliseconds(date: Number, amount: Number): Date

external fun subMinutes(date: Date, amount: Number): Date

external fun subMinutes(date: Number, amount: Number): Date

external fun subMonths(date: Date, amount: Number): Date

external fun subMonths(date: Number, amount: Number): Date

external fun subQuarters(date: Date, amount: Number): Date

external fun subQuarters(date: Number, amount: Number): Date

external fun subSeconds(date: Date, amount: Number): Date

external fun subSeconds(date: Number, amount: Number): Date

external fun subWeeks(date: Date, amount: Number): Date

external fun subWeeks(date: Number, amount: Number): Date

external fun subYears(date: Date, amount: Number): Date

external fun subYears(date: Number, amount: Number): Date

external fun toDate(argument: Date): Date

external fun toDate(argument: Number): Date

external var maxTime: Number

external var minTime: Number
