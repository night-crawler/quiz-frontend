@file:JsModule("date-fns/esm")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package date.fns.esm

import Duration
import Interval
import Locale
import date.fns.`T$10`
import date.fns.`T$11`
import date.fns.`T$14`
import date.fns.`T$15`
import date.fns.`T$3`
import date.fns.`T$5`
import date.fns.`T$6`
import kotlin.js.Date

@JsName("default")
external fun add(date: Date, duration: Duration): Date

@JsName("default")
external fun add(date: Number, duration: Duration): Date

@JsName("default")
external fun addBusinessDays(date: Date, amount: Number): Date

@JsName("default")
external fun addBusinessDays(date: Number, amount: Number): Date

@JsName("default")
external fun addDays(date: Date, amount: Number): Date

@JsName("default")
external fun addDays(date: Number, amount: Number): Date

@JsName("default")
external fun addHours(date: Date, amount: Number): Date

@JsName("default")
external fun addHours(date: Number, amount: Number): Date

@JsName("default")
external fun addISOWeekYears(date: Date, amount: Number): Date

@JsName("default")
external fun addISOWeekYears(date: Number, amount: Number): Date

@JsName("default")
external fun addMilliseconds(date: Date, amount: Number): Date

@JsName("default")
external fun addMilliseconds(date: Number, amount: Number): Date

@JsName("default")
external fun addMinutes(date: Date, amount: Number): Date

@JsName("default")
external fun addMinutes(date: Number, amount: Number): Date

@JsName("default")
external fun addMonths(date: Date, amount: Number): Date

@JsName("default")
external fun addMonths(date: Number, amount: Number): Date

@JsName("default")
external fun addQuarters(date: Date, amount: Number): Date

@JsName("default")
external fun addQuarters(date: Number, amount: Number): Date

@JsName("default")
external fun addSeconds(date: Date, amount: Number): Date

@JsName("default")
external fun addSeconds(date: Number, amount: Number): Date

@JsName("default")
external fun addWeeks(date: Date, amount: Number): Date

@JsName("default")
external fun addWeeks(date: Number, amount: Number): Date

@JsName("default")
external fun addYears(date: Date, amount: Number): Date

@JsName("default")
external fun addYears(date: Number, amount: Number): Date

@JsName("default")
external fun areIntervalsOverlapping(
    intervalLeft: Interval,
    intervalRight: Interval,
    options: `T$3` = definedExternally
): Boolean

@JsName("default")
external fun closestIndexTo(dateToCompare: Date, datesArray: Array<dynamic /* Date | Number */>): Number

@JsName("default")
external fun closestIndexTo(dateToCompare: Number, datesArray: Array<dynamic /* Date | Number */>): Number

@JsName("default")
external fun closestTo(dateToCompare: Date, datesArray: Array<dynamic /* Date | Number */>): Date

@JsName("default")
external fun closestTo(dateToCompare: Number, datesArray: Array<dynamic /* Date | Number */>): Date

@JsName("default")
external fun compareAsc(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun compareAsc(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun compareAsc(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun compareAsc(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun compareDesc(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun compareDesc(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun compareDesc(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun compareDesc(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInBusinessDays(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInBusinessDays(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInBusinessDays(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInBusinessDays(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarDays(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarDays(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarDays(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarDays(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarISOWeeks(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarISOWeeks(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarISOWeeks(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarISOWeeks(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarISOWeekYears(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarISOWeekYears(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarISOWeekYears(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarISOWeekYears(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarMonths(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarMonths(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarMonths(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarMonths(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarQuarters(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarQuarters(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarQuarters(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarQuarters(dateLeft: Number, dateRight: Number): Number

external interface `T$24` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
}

@JsName("default")
external fun differenceInCalendarWeeks(dateLeft: Date, dateRight: Date, options: `T$24` = definedExternally): Number

@JsName("default")
external fun differenceInCalendarWeeks(dateLeft: Date, dateRight: Number, options: `T$24` = definedExternally): Number

@JsName("default")
external fun differenceInCalendarWeeks(dateLeft: Number, dateRight: Date, options: `T$24` = definedExternally): Number

@JsName("default")
external fun differenceInCalendarWeeks(dateLeft: Number, dateRight: Number, options: `T$24` = definedExternally): Number

@JsName("default")
external fun differenceInCalendarYears(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarYears(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInCalendarYears(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInCalendarYears(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInDays(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInDays(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInDays(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInDays(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInHours(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInHours(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInHours(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInHours(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInISOWeekYears(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInISOWeekYears(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInISOWeekYears(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInISOWeekYears(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInMilliseconds(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInMilliseconds(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInMilliseconds(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInMilliseconds(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInMinutes(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInMinutes(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInMinutes(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInMinutes(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInMonths(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInMonths(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInMonths(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInMonths(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInQuarters(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInQuarters(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInQuarters(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInQuarters(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInSeconds(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInSeconds(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInSeconds(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInSeconds(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInWeeks(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInWeeks(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInWeeks(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInWeeks(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun differenceInYears(dateLeft: Date, dateRight: Date): Number

@JsName("default")
external fun differenceInYears(dateLeft: Date, dateRight: Number): Number

@JsName("default")
external fun differenceInYears(dateLeft: Number, dateRight: Date): Number

@JsName("default")
external fun differenceInYears(dateLeft: Number, dateRight: Number): Number

@JsName("default")
external fun eachDayOfInterval(interval: Interval, options: `T$5` = definedExternally): Array<Date>

@JsName("default")
external fun eachMonthOfInterval(interval: Interval): Array<Date>

@JsName("default")
external fun eachWeekendOfInterval(interval: Interval): Array<Date>

@JsName("default")
external fun eachWeekendOfMonth(date: Date): Array<Date>

@JsName("default")
external fun eachWeekendOfMonth(date: Number): Array<Date>

@JsName("default")
external fun eachWeekendOfYear(date: Date): Array<Date>

@JsName("default")
external fun eachWeekendOfYear(date: Number): Array<Date>

@JsName("default")
external fun eachWeekOfInterval(interval: Interval, options: `T$24` = definedExternally): Array<Date>

@JsName("default")
external fun eachYearOfInterval(interval: Interval): Array<Date>

@JsName("default")
external fun endOfDay(date: Date): Date

@JsName("default")
external fun endOfDay(date: Number): Date

@JsName("default")
external fun endOfDecade(date: Date, options: `T$6` = definedExternally): Date

@JsName("default")
external fun endOfDecade(date: Number, options: `T$6` = definedExternally): Date

@JsName("default")
external fun endOfHour(date: Date): Date

@JsName("default")
external fun endOfHour(date: Number): Date

@JsName("default")
external fun endOfISOWeek(date: Date): Date

@JsName("default")
external fun endOfISOWeek(date: Number): Date

@JsName("default")
external fun endOfISOWeekYear(date: Date): Date

@JsName("default")
external fun endOfISOWeekYear(date: Number): Date

@JsName("default")
external fun endOfMinute(date: Date): Date

@JsName("default")
external fun endOfMinute(date: Number): Date

@JsName("default")
external fun endOfMonth(date: Date): Date

@JsName("default")
external fun endOfMonth(date: Number): Date

@JsName("default")
external fun endOfQuarter(date: Date): Date

@JsName("default")
external fun endOfQuarter(date: Number): Date

@JsName("default")
external fun endOfSecond(date: Date): Date

@JsName("default")
external fun endOfSecond(date: Number): Date

@JsName("default")
external fun endOfToday(): Date

@JsName("default")
external fun endOfTomorrow(): Date

@JsName("default")
external fun endOfWeek(date: Date, options: `T$24` = definedExternally): Date

@JsName("default")
external fun endOfWeek(date: Number, options: `T$24` = definedExternally): Date

@JsName("default")
external fun endOfYear(date: Date): Date

@JsName("default")
external fun endOfYear(date: Number): Date

@JsName("default")
external fun endOfYesterday(): Date

external interface `T$25` {
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

@JsName("default")
external fun format(date: Date, format: String, options: `T$25` = definedExternally): String

@JsName("default")
external fun format(date: Number, format: String, options: `T$25` = definedExternally): String

external interface `T$26` {
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

@JsName("default")
external fun formatDistance(date: Date, baseDate: Date, options: `T$26` = definedExternally): String

@JsName("default")
external fun formatDistance(date: Date, baseDate: Number, options: `T$26` = definedExternally): String

@JsName("default")
external fun formatDistance(date: Number, baseDate: Date, options: `T$26` = definedExternally): String

@JsName("default")
external fun formatDistance(date: Number, baseDate: Number, options: `T$26` = definedExternally): String

external interface `T$27` {
    var addSuffix: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var unit: String /* 'second' | 'minute' | 'hour' | 'day' | 'month' | 'year' */
    var roundingMethod: String /* 'floor' | 'ceil' | 'round' */
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

@JsName("default")
external fun formatDistanceStrict(date: Date, baseDate: Date, options: `T$27` = definedExternally): String

@JsName("default")
external fun formatDistanceStrict(date: Date, baseDate: Number, options: `T$27` = definedExternally): String

@JsName("default")
external fun formatDistanceStrict(date: Number, baseDate: Date, options: `T$27` = definedExternally): String

@JsName("default")
external fun formatDistanceStrict(date: Number, baseDate: Number, options: `T$27` = definedExternally): String

@JsName("default")
external fun formatDistanceToNow(date: Date, options: `T$26` = definedExternally): String

@JsName("default")
external fun formatDistanceToNow(date: Number, options: `T$26` = definedExternally): String

@JsName("default")
external fun formatDistanceToNowStrict(date: Date, options: `T$27` = definedExternally): String

@JsName("default")
external fun formatDistanceToNowStrict(date: Number, options: `T$27` = definedExternally): String

@JsName("default")
external fun formatISO(date: Date, options: `T$10` = definedExternally): String

@JsName("default")
external fun formatISO(date: Number, options: `T$10` = definedExternally): String

@JsName("default")
external fun formatISO9075(date: Date, options: `T$10` = definedExternally): String

@JsName("default")
external fun formatISO9075(date: Number, options: `T$10` = definedExternally): String

@JsName("default")
external fun formatRelative(date: Date, baseDate: Date, options: `T$24` = definedExternally): String

@JsName("default")
external fun formatRelative(date: Date, baseDate: Number, options: `T$24` = definedExternally): String

@JsName("default")
external fun formatRelative(date: Number, baseDate: Date, options: `T$24` = definedExternally): String

@JsName("default")
external fun formatRelative(date: Number, baseDate: Number, options: `T$24` = definedExternally): String

@JsName("default")
external fun formatRFC3339(date: Date, options: `T$11` = definedExternally): String

@JsName("default")
external fun formatRFC3339(date: Number, options: `T$11` = definedExternally): String

@JsName("default")
external fun formatRFC7231(date: Date): String

@JsName("default")
external fun formatRFC7231(date: Number): String

@JsName("default")
external fun fromUnixTime(unixTime: Number): Date

@JsName("default")
external fun getDate(date: Date): Number

@JsName("default")
external fun getDate(date: Number): Number

@JsName("default")
external fun getDay(date: Date): String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */

@JsName("default")
external fun getDay(date: Number): String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */

@JsName("default")
external fun getDayOfYear(date: Date): Number

@JsName("default")
external fun getDayOfYear(date: Number): Number

@JsName("default")
external fun getDaysInMonth(date: Date): Number

@JsName("default")
external fun getDaysInMonth(date: Number): Number

@JsName("default")
external fun getDaysInYear(date: Date): Number

@JsName("default")
external fun getDaysInYear(date: Number): Number

@JsName("default")
external fun getDecade(date: Date): Number

@JsName("default")
external fun getDecade(date: Number): Number

@JsName("default")
external fun getHours(date: Date): Number

@JsName("default")
external fun getHours(date: Number): Number

@JsName("default")
external fun getISODay(date: Date): Number

@JsName("default")
external fun getISODay(date: Number): Number

@JsName("default")
external fun getISOWeek(date: Date): Number

@JsName("default")
external fun getISOWeek(date: Number): Number

@JsName("default")
external fun getISOWeeksInYear(date: Date): Number

@JsName("default")
external fun getISOWeeksInYear(date: Number): Number

@JsName("default")
external fun getISOWeekYear(date: Date): Number

@JsName("default")
external fun getISOWeekYear(date: Number): Number

@JsName("default")
external fun getMilliseconds(date: Date): Number

@JsName("default")
external fun getMilliseconds(date: Number): Number

@JsName("default")
external fun getMinutes(date: Date): Number

@JsName("default")
external fun getMinutes(date: Number): Number

@JsName("default")
external fun getMonth(date: Date): Number

@JsName("default")
external fun getMonth(date: Number): Number

@JsName("default")
external fun getOverlappingDaysInIntervals(intervalLeft: Interval, intervalRight: Interval): Number

@JsName("default")
external fun getQuarter(date: Date): Number

@JsName("default")
external fun getQuarter(date: Number): Number

@JsName("default")
external fun getSeconds(date: Date): Number

@JsName("default")
external fun getSeconds(date: Number): Number

@JsName("default")
external fun getTime(date: Date): Number

@JsName("default")
external fun getTime(date: Number): Number

@JsName("default")
external fun getUnixTime(date: Date): Number

@JsName("default")
external fun getUnixTime(date: Number): Number

external interface `T$28` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var firstWeekContainsDate: String /* 1 | 2 | 3 | 4 | 5 | 6 | 7 */
}

@JsName("default")
external fun getWeek(date: Date, options: `T$28` = definedExternally): Number

@JsName("default")
external fun getWeek(date: Number, options: `T$28` = definedExternally): Number

@JsName("default")
external fun getWeekOfMonth(date: Date, options: `T$24` = definedExternally): Number

@JsName("default")
external fun getWeekOfMonth(date: Number, options: `T$24` = definedExternally): Number

@JsName("default")
external fun getWeeksInMonth(date: Date, options: `T$24` = definedExternally): Number

@JsName("default")
external fun getWeeksInMonth(date: Number, options: `T$24` = definedExternally): Number

@JsName("default")
external fun getWeekYear(date: Date, options: `T$28` = definedExternally): Number

@JsName("default")
external fun getWeekYear(date: Number, options: `T$28` = definedExternally): Number

@JsName("default")
external fun getYear(date: Date): Number

@JsName("default")
external fun getYear(date: Number): Number

@JsName("default")
external fun isAfter(date: Date, dateToCompare: Date): Boolean

@JsName("default")
external fun isAfter(date: Date, dateToCompare: Number): Boolean

@JsName("default")
external fun isAfter(date: Number, dateToCompare: Date): Boolean

@JsName("default")
external fun isAfter(date: Number, dateToCompare: Number): Boolean

@JsName("default")
external fun isBefore(date: Date, dateToCompare: Date): Boolean

@JsName("default")
external fun isBefore(date: Date, dateToCompare: Number): Boolean

@JsName("default")
external fun isBefore(date: Number, dateToCompare: Date): Boolean

@JsName("default")
external fun isBefore(date: Number, dateToCompare: Number): Boolean

@JsName("default")
external fun isDate(value: Any): Boolean

@JsName("default")
external fun isEqual(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isEqual(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isEqual(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isEqual(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isExists(year: Number, month: Number, day: Number): Boolean

@JsName("default")
external fun isFirstDayOfMonth(date: Date): Boolean

@JsName("default")
external fun isFirstDayOfMonth(date: Number): Boolean

@JsName("default")
external fun isFriday(date: Date): Boolean

@JsName("default")
external fun isFriday(date: Number): Boolean

@JsName("default")
external fun isFuture(date: Date): Boolean

@JsName("default")
external fun isFuture(date: Number): Boolean

@JsName("default")
external fun isLastDayOfMonth(date: Date): Boolean

@JsName("default")
external fun isLastDayOfMonth(date: Number): Boolean

@JsName("default")
external fun isLeapYear(date: Date): Boolean

@JsName("default")
external fun isLeapYear(date: Number): Boolean

@JsName("default")
external fun isMonday(date: Date): Boolean

@JsName("default")
external fun isMonday(date: Number): Boolean

@JsName("default")
external fun isPast(date: Date): Boolean

@JsName("default")
external fun isPast(date: Number): Boolean

@JsName("default")
external fun isSameDay(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameDay(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameDay(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameDay(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameHour(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameHour(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameHour(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameHour(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameISOWeek(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameISOWeek(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameISOWeek(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameISOWeek(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameISOWeekYear(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameISOWeekYear(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameISOWeekYear(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameISOWeekYear(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameMinute(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameMinute(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameMinute(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameMinute(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameMonth(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameMonth(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameMonth(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameMonth(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameQuarter(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameQuarter(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameQuarter(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameQuarter(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameSecond(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameSecond(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameSecond(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameSecond(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSameWeek(dateLeft: Date, dateRight: Date, options: `T$24` = definedExternally): Boolean

@JsName("default")
external fun isSameWeek(dateLeft: Date, dateRight: Number, options: `T$24` = definedExternally): Boolean

@JsName("default")
external fun isSameWeek(dateLeft: Number, dateRight: Date, options: `T$24` = definedExternally): Boolean

@JsName("default")
external fun isSameWeek(dateLeft: Number, dateRight: Number, options: `T$24` = definedExternally): Boolean

@JsName("default")
external fun isSameYear(dateLeft: Date, dateRight: Date): Boolean

@JsName("default")
external fun isSameYear(dateLeft: Date, dateRight: Number): Boolean

@JsName("default")
external fun isSameYear(dateLeft: Number, dateRight: Date): Boolean

@JsName("default")
external fun isSameYear(dateLeft: Number, dateRight: Number): Boolean

@JsName("default")
external fun isSaturday(date: Date): Boolean

@JsName("default")
external fun isSaturday(date: Number): Boolean

@JsName("default")
external fun isSunday(date: Date): Boolean

@JsName("default")
external fun isSunday(date: Number): Boolean

@JsName("default")
external fun isThisHour(date: Date): Boolean

@JsName("default")
external fun isThisHour(date: Number): Boolean

@JsName("default")
external fun isThisISOWeek(date: Date): Boolean

@JsName("default")
external fun isThisISOWeek(date: Number): Boolean

@JsName("default")
external fun isThisMinute(date: Date): Boolean

@JsName("default")
external fun isThisMinute(date: Number): Boolean

@JsName("default")
external fun isThisMonth(date: Date): Boolean

@JsName("default")
external fun isThisMonth(date: Number): Boolean

@JsName("default")
external fun isThisQuarter(date: Date): Boolean

@JsName("default")
external fun isThisQuarter(date: Number): Boolean

@JsName("default")
external fun isThisSecond(date: Date): Boolean

@JsName("default")
external fun isThisSecond(date: Number): Boolean

@JsName("default")
external fun isThisWeek(date: Date, options: `T$24` = definedExternally): Boolean

@JsName("default")
external fun isThisWeek(date: Number, options: `T$24` = definedExternally): Boolean

@JsName("default")
external fun isThisYear(date: Date): Boolean

@JsName("default")
external fun isThisYear(date: Number): Boolean

@JsName("default")
external fun isThursday(date: Date): Boolean

@JsName("default")
external fun isThursday(date: Number): Boolean

@JsName("default")
external fun isToday(date: Date): Boolean

@JsName("default")
external fun isToday(date: Number): Boolean

@JsName("default")
external fun isTomorrow(date: Date): Boolean

@JsName("default")
external fun isTomorrow(date: Number): Boolean

@JsName("default")
external fun isTuesday(date: Date): Boolean

@JsName("default")
external fun isTuesday(date: Number): Boolean

@JsName("default")
external fun isValid(date: Any): Boolean

@JsName("default")
external fun isWednesday(date: Date): Boolean

@JsName("default")
external fun isWednesday(date: Number): Boolean

@JsName("default")
external fun isWeekend(date: Date): Boolean

@JsName("default")
external fun isWeekend(date: Number): Boolean

@JsName("default")
external fun isWithinInterval(date: Date, interval: Interval): Boolean

@JsName("default")
external fun isWithinInterval(date: Number, interval: Interval): Boolean

@JsName("default")
external fun isYesterday(date: Date): Boolean

@JsName("default")
external fun isYesterday(date: Number): Boolean

@JsName("default")
external fun lastDayOfDecade(date: Date): Date

@JsName("default")
external fun lastDayOfDecade(date: Number): Date

@JsName("default")
external fun lastDayOfISOWeek(date: Date): Date

@JsName("default")
external fun lastDayOfISOWeek(date: Number): Date

@JsName("default")
external fun lastDayOfISOWeekYear(date: Date): Date

@JsName("default")
external fun lastDayOfISOWeekYear(date: Number): Date

@JsName("default")
external fun lastDayOfMonth(date: Date): Date

@JsName("default")
external fun lastDayOfMonth(date: Number): Date

@JsName("default")
external fun lastDayOfQuarter(date: Date, options: `T$6` = definedExternally): Date

@JsName("default")
external fun lastDayOfQuarter(date: Number, options: `T$6` = definedExternally): Date

@JsName("default")
external fun lastDayOfWeek(date: Date, options: `T$24` = definedExternally): Date

@JsName("default")
external fun lastDayOfWeek(date: Number, options: `T$24` = definedExternally): Date

@JsName("default")
external fun lastDayOfYear(date: Date): Date

@JsName("default")
external fun lastDayOfYear(date: Number): Date

@JsName("default")
external fun lightFormat(date: Date, format: String): String

@JsName("default")
external fun lightFormat(date: Number, format: String): String

@JsName("default")
external fun max(datesArray: Array<dynamic /* Date | Number */>): Date

@JsName("default")
external fun min(datesArray: Array<dynamic /* Date | Number */>): Date

external interface `T$29` {
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

@JsName("default")
external fun parse(
    dateString: String,
    formatString: String,
    referenceDate: Date,
    options: `T$29` = definedExternally
): Date

@JsName("default")
external fun parse(
    dateString: String,
    formatString: String,
    referenceDate: Number,
    options: `T$29` = definedExternally
): Date

@JsName("default")
external fun parseISO(argument: String, options: `T$6` = definedExternally): Date

@JsName("default")
external fun parseJSON(argument: String): Date

@JsName("default")
external fun parseJSON(argument: Number): Date

@JsName("default")
external fun parseJSON(argument: Date): Date

@JsName("default")
external fun roundToNearestMinutes(date: Date, options: `T$14` = definedExternally): Date

@JsName("default")
external fun roundToNearestMinutes(date: Number, options: `T$14` = definedExternally): Date

@JsName("default")
external fun set(date: Date, values: `T$15`): Date

@JsName("default")
external fun set(date: Number, values: `T$15`): Date

@JsName("default")
external fun setDate(date: Date, dayOfMonth: Number): Date

@JsName("default")
external fun setDate(date: Number, dayOfMonth: Number): Date

@JsName("default")
external fun setDay(date: Date, day: Number, options: `T$24` = definedExternally): Date

@JsName("default")
external fun setDay(date: Number, day: Number, options: `T$24` = definedExternally): Date

@JsName("default")
external fun setDayOfYear(date: Date, dayOfYear: Number): Date

@JsName("default")
external fun setDayOfYear(date: Number, dayOfYear: Number): Date

@JsName("default")
external fun setHours(date: Date, hours: Number): Date

@JsName("default")
external fun setHours(date: Number, hours: Number): Date

@JsName("default")
external fun setISODay(date: Date, day: Number): Date

@JsName("default")
external fun setISODay(date: Number, day: Number): Date

@JsName("default")
external fun setISOWeek(date: Date, isoWeek: Number): Date

@JsName("default")
external fun setISOWeek(date: Number, isoWeek: Number): Date

@JsName("default")
external fun setISOWeekYear(date: Date, isoWeekYear: Number): Date

@JsName("default")
external fun setISOWeekYear(date: Number, isoWeekYear: Number): Date

@JsName("default")
external fun setMilliseconds(date: Date, milliseconds: Number): Date

@JsName("default")
external fun setMilliseconds(date: Number, milliseconds: Number): Date

@JsName("default")
external fun setMinutes(date: Date, minutes: Number): Date

@JsName("default")
external fun setMinutes(date: Number, minutes: Number): Date

@JsName("default")
external fun setMonth(date: Date, month: Number): Date

@JsName("default")
external fun setMonth(date: Number, month: Number): Date

@JsName("default")
external fun setQuarter(date: Date, quarter: Number): Date

@JsName("default")
external fun setQuarter(date: Number, quarter: Number): Date

@JsName("default")
external fun setSeconds(date: Date, seconds: Number): Date

@JsName("default")
external fun setSeconds(date: Number, seconds: Number): Date

@JsName("default")
external fun setWeek(date: Date, week: Number, options: `T$28` = definedExternally): Date

@JsName("default")
external fun setWeek(date: Number, week: Number, options: `T$28` = definedExternally): Date

@JsName("default")
external fun setWeekYear(date: Date, weekYear: Number, options: `T$28` = definedExternally): Date

@JsName("default")
external fun setWeekYear(date: Number, weekYear: Number, options: `T$28` = definedExternally): Date

@JsName("default")
external fun setYear(date: Date, year: Number): Date

@JsName("default")
external fun setYear(date: Number, year: Number): Date

@JsName("default")
external fun startOfDay(date: Date): Date

@JsName("default")
external fun startOfDay(date: Number): Date

@JsName("default")
external fun startOfDecade(date: Date): Date

@JsName("default")
external fun startOfDecade(date: Number): Date

@JsName("default")
external fun startOfHour(date: Date): Date

@JsName("default")
external fun startOfHour(date: Number): Date

@JsName("default")
external fun startOfISOWeek(date: Date): Date

@JsName("default")
external fun startOfISOWeek(date: Number): Date

@JsName("default")
external fun startOfISOWeekYear(date: Date): Date

@JsName("default")
external fun startOfISOWeekYear(date: Number): Date

@JsName("default")
external fun startOfMinute(date: Date): Date

@JsName("default")
external fun startOfMinute(date: Number): Date

@JsName("default")
external fun startOfMonth(date: Date): Date

@JsName("default")
external fun startOfMonth(date: Number): Date

@JsName("default")
external fun startOfQuarter(date: Date): Date

@JsName("default")
external fun startOfQuarter(date: Number): Date

@JsName("default")
external fun startOfSecond(date: Date): Date

@JsName("default")
external fun startOfSecond(date: Number): Date

@JsName("default")
external fun startOfToday(): Date

@JsName("default")
external fun startOfTomorrow(): Date

@JsName("default")
external fun startOfWeek(date: Date, options: `T$24` = definedExternally): Date

@JsName("default")
external fun startOfWeek(date: Number, options: `T$24` = definedExternally): Date

@JsName("default")
external fun startOfWeekYear(date: Date, options: `T$28` = definedExternally): Date

@JsName("default")
external fun startOfWeekYear(date: Number, options: `T$28` = definedExternally): Date

@JsName("default")
external fun startOfYear(date: Date): Date

@JsName("default")
external fun startOfYear(date: Number): Date

@JsName("default")
external fun startOfYesterday(): Date

@JsName("default")
external fun sub(date: Date, duration: Duration): Date

@JsName("default")
external fun sub(date: Number, duration: Duration): Date

@JsName("default")
external fun subBusinessDays(date: Date, amount: Number): Date

@JsName("default")
external fun subBusinessDays(date: Number, amount: Number): Date

@JsName("default")
external fun subDays(date: Date, amount: Number): Date

@JsName("default")
external fun subDays(date: Number, amount: Number): Date

@JsName("default")
external fun subHours(date: Date, amount: Number): Date

@JsName("default")
external fun subHours(date: Number, amount: Number): Date

@JsName("default")
external fun subISOWeekYears(date: Date, amount: Number): Date

@JsName("default")
external fun subISOWeekYears(date: Number, amount: Number): Date

@JsName("default")
external fun subMilliseconds(date: Date, amount: Number): Date

@JsName("default")
external fun subMilliseconds(date: Number, amount: Number): Date

@JsName("default")
external fun subMinutes(date: Date, amount: Number): Date

@JsName("default")
external fun subMinutes(date: Number, amount: Number): Date

@JsName("default")
external fun subMonths(date: Date, amount: Number): Date

@JsName("default")
external fun subMonths(date: Number, amount: Number): Date

@JsName("default")
external fun subQuarters(date: Date, amount: Number): Date

@JsName("default")
external fun subQuarters(date: Number, amount: Number): Date

@JsName("default")
external fun subSeconds(date: Date, amount: Number): Date

@JsName("default")
external fun subSeconds(date: Number, amount: Number): Date

@JsName("default")
external fun subWeeks(date: Date, amount: Number): Date

@JsName("default")
external fun subWeeks(date: Number, amount: Number): Date

@JsName("default")
external fun subYears(date: Date, amount: Number): Date

@JsName("default")
external fun subYears(date: Number, amount: Number): Date

@JsName("default")
external fun toDate(argument: Date): Date

@JsName("default")
external fun toDate(argument: Number): Date

external var maxTime: Number

external var minTime: Number
