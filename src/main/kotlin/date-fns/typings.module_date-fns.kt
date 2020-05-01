@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

import date.fns.*
import date.fns.esm.*
import kotlin.js.Date

external interface CurriedFn1<A, R> {
    @nativeInvoke
    operator fun invoke(a: A): R
}

external interface CurriedFn2<A, B, R> {
    @nativeInvoke
    operator fun invoke(a: A): CurriedFn1<B, R>

    @nativeInvoke
    operator fun invoke(a: A, b: B): R
}

external interface CurriedFn3<A, B, C, R> {
    @nativeInvoke
    operator fun invoke(a: A): CurriedFn2<B, C, R>

    @nativeInvoke
    operator fun invoke(a: A, b: B): CurriedFn1<C, R>

    @nativeInvoke
    operator fun invoke(a: A, b: B, c: C): R
}

external interface CurriedFn4<A, B, C, D, R> {
    @nativeInvoke
    operator fun invoke(a: A): CurriedFn3<B, C, D, R>

    @nativeInvoke
    operator fun invoke(a: A, b: B): CurriedFn2<C, D, R>

    @nativeInvoke
    operator fun invoke(a: A, b: B, c: C): CurriedFn1<D, R>

    @nativeInvoke
    operator fun invoke(a: A, b: B, c: C, d: D): R
}

external interface Interval {
    var start: dynamic /* Date | Number */
        get() = definedExternally
        set(value) = definedExternally
    var end: dynamic /* Date | Number */
        get() = definedExternally
        set(value) = definedExternally
}

typealias IntervalAliased = Interval

external interface `T$0` {
    var ordinalNumber: (args: Any) -> Any
    var era: (args: Any) -> Any
    var quarter: (args: Any) -> Any
    var month: (args: Any) -> Any
    var day: (args: Any) -> Any
    var dayPeriod: (args: Any) -> Any
}

external interface `T$1` {
    var date: (args: Any) -> Any
    var time: (args: Any) -> Any
    var dateTime: (args: Any) -> Any
}

external interface `T$2` {
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var firstWeekContainsDate: String /* 1 | 2 | 3 | 4 | 5 | 6 | 7 */
}

external interface Locale {
    var code: String?
        get() = definedExternally
        set(value) = definedExternally
    var formatDistance: ((args: Any) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var formatRelative: ((args: Any) -> Any)?
        get() = definedExternally
        set(value) = definedExternally
    var localize: `T$0`?
        get() = definedExternally
        set(value) = definedExternally
    var formatLong: `T$1`?
        get() = definedExternally
        set(value) = definedExternally
    var match: `T$0`?
        get() = definedExternally
        set(value) = definedExternally
    var options: `T$2`?
        get() = definedExternally
        set(value) = definedExternally
}

typealias LocaleAliased = Locale

external interface Duration {
    var years: Number?
        get() = definedExternally
        set(value) = definedExternally
    var months: Number?
        get() = definedExternally
        set(value) = definedExternally
    var weeks: Number?
        get() = definedExternally
        set(value) = definedExternally
    var days: Number?
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
}

typealias DurationAliased = Duration

external interface dateFns {
    fun add(date: Date, duration: Duration): Date
    fun add(date: Number, duration: Duration): Date
    fun addBusinessDays(date: Date, amount: Number): Date
    fun addBusinessDays(date: Number, amount: Number): Date
    fun addDays(date: Date, amount: Number): Date
    fun addDays(date: Number, amount: Number): Date
    fun addHours(date: Date, amount: Number): Date
    fun addHours(date: Number, amount: Number): Date
    fun addISOWeekYears(date: Date, amount: Number): Date
    fun addISOWeekYears(date: Number, amount: Number): Date
    fun addMilliseconds(date: Date, amount: Number): Date
    fun addMilliseconds(date: Number, amount: Number): Date
    fun addMinutes(date: Date, amount: Number): Date
    fun addMinutes(date: Number, amount: Number): Date
    fun addMonths(date: Date, amount: Number): Date
    fun addMonths(date: Number, amount: Number): Date
    fun addQuarters(date: Date, amount: Number): Date
    fun addQuarters(date: Number, amount: Number): Date
    fun addSeconds(date: Date, amount: Number): Date
    fun addSeconds(date: Number, amount: Number): Date
    fun addWeeks(date: Date, amount: Number): Date
    fun addWeeks(date: Number, amount: Number): Date
    fun addYears(date: Date, amount: Number): Date
    fun addYears(date: Number, amount: Number): Date
    fun areIntervalsOverlapping(
        intervalLeft: Interval,
        intervalRight: Interval,
        options: `T$3` = definedExternally
    ): Boolean

    fun closestIndexTo(dateToCompare: Date, datesArray: Array<dynamic /* Date | Number */>): Number
    fun closestIndexTo(dateToCompare: Number, datesArray: Array<dynamic /* Date | Number */>): Number
    fun closestTo(dateToCompare: Date, datesArray: Array<dynamic /* Date | Number */>): Date
    fun closestTo(dateToCompare: Number, datesArray: Array<dynamic /* Date | Number */>): Date
    fun compareAsc(dateLeft: Date, dateRight: Date): Number
    fun compareAsc(dateLeft: Date, dateRight: Number): Number
    fun compareAsc(dateLeft: Number, dateRight: Date): Number
    fun compareAsc(dateLeft: Number, dateRight: Number): Number
    fun compareDesc(dateLeft: Date, dateRight: Date): Number
    fun compareDesc(dateLeft: Date, dateRight: Number): Number
    fun compareDesc(dateLeft: Number, dateRight: Date): Number
    fun compareDesc(dateLeft: Number, dateRight: Number): Number
    fun differenceInBusinessDays(dateLeft: Date, dateRight: Date): Number
    fun differenceInBusinessDays(dateLeft: Date, dateRight: Number): Number
    fun differenceInBusinessDays(dateLeft: Number, dateRight: Date): Number
    fun differenceInBusinessDays(dateLeft: Number, dateRight: Number): Number
    fun differenceInCalendarDays(dateLeft: Date, dateRight: Date): Number
    fun differenceInCalendarDays(dateLeft: Date, dateRight: Number): Number
    fun differenceInCalendarDays(dateLeft: Number, dateRight: Date): Number
    fun differenceInCalendarDays(dateLeft: Number, dateRight: Number): Number
    fun differenceInCalendarISOWeeks(dateLeft: Date, dateRight: Date): Number
    fun differenceInCalendarISOWeeks(dateLeft: Date, dateRight: Number): Number
    fun differenceInCalendarISOWeeks(dateLeft: Number, dateRight: Date): Number
    fun differenceInCalendarISOWeeks(dateLeft: Number, dateRight: Number): Number
    fun differenceInCalendarISOWeekYears(dateLeft: Date, dateRight: Date): Number
    fun differenceInCalendarISOWeekYears(dateLeft: Date, dateRight: Number): Number
    fun differenceInCalendarISOWeekYears(dateLeft: Number, dateRight: Date): Number
    fun differenceInCalendarISOWeekYears(dateLeft: Number, dateRight: Number): Number
    fun differenceInCalendarMonths(dateLeft: Date, dateRight: Date): Number
    fun differenceInCalendarMonths(dateLeft: Date, dateRight: Number): Number
    fun differenceInCalendarMonths(dateLeft: Number, dateRight: Date): Number
    fun differenceInCalendarMonths(dateLeft: Number, dateRight: Number): Number
    fun differenceInCalendarQuarters(dateLeft: Date, dateRight: Date): Number
    fun differenceInCalendarQuarters(dateLeft: Date, dateRight: Number): Number
    fun differenceInCalendarQuarters(dateLeft: Number, dateRight: Date): Number
    fun differenceInCalendarQuarters(dateLeft: Number, dateRight: Number): Number
    fun differenceInCalendarWeeks(dateLeft: Date, dateRight: Date, options: `T$24` = definedExternally): Number
    fun differenceInCalendarWeeks(dateLeft: Date, dateRight: Number, options: `T$24` = definedExternally): Number
    fun differenceInCalendarWeeks(dateLeft: Number, dateRight: Date, options: `T$24` = definedExternally): Number
    fun differenceInCalendarWeeks(dateLeft: Number, dateRight: Number, options: `T$24` = definedExternally): Number
    fun differenceInCalendarYears(dateLeft: Date, dateRight: Date): Number
    fun differenceInCalendarYears(dateLeft: Date, dateRight: Number): Number
    fun differenceInCalendarYears(dateLeft: Number, dateRight: Date): Number
    fun differenceInCalendarYears(dateLeft: Number, dateRight: Number): Number
    fun differenceInDays(dateLeft: Date, dateRight: Date): Number
    fun differenceInDays(dateLeft: Date, dateRight: Number): Number
    fun differenceInDays(dateLeft: Number, dateRight: Date): Number
    fun differenceInDays(dateLeft: Number, dateRight: Number): Number
    fun differenceInHours(dateLeft: Date, dateRight: Date): Number
    fun differenceInHours(dateLeft: Date, dateRight: Number): Number
    fun differenceInHours(dateLeft: Number, dateRight: Date): Number
    fun differenceInHours(dateLeft: Number, dateRight: Number): Number
    fun differenceInISOWeekYears(dateLeft: Date, dateRight: Date): Number
    fun differenceInISOWeekYears(dateLeft: Date, dateRight: Number): Number
    fun differenceInISOWeekYears(dateLeft: Number, dateRight: Date): Number
    fun differenceInISOWeekYears(dateLeft: Number, dateRight: Number): Number
    fun differenceInMilliseconds(dateLeft: Date, dateRight: Date): Number
    fun differenceInMilliseconds(dateLeft: Date, dateRight: Number): Number
    fun differenceInMilliseconds(dateLeft: Number, dateRight: Date): Number
    fun differenceInMilliseconds(dateLeft: Number, dateRight: Number): Number
    fun differenceInMinutes(dateLeft: Date, dateRight: Date): Number
    fun differenceInMinutes(dateLeft: Date, dateRight: Number): Number
    fun differenceInMinutes(dateLeft: Number, dateRight: Date): Number
    fun differenceInMinutes(dateLeft: Number, dateRight: Number): Number
    fun differenceInMonths(dateLeft: Date, dateRight: Date): Number
    fun differenceInMonths(dateLeft: Date, dateRight: Number): Number
    fun differenceInMonths(dateLeft: Number, dateRight: Date): Number
    fun differenceInMonths(dateLeft: Number, dateRight: Number): Number
    fun differenceInQuarters(dateLeft: Date, dateRight: Date): Number
    fun differenceInQuarters(dateLeft: Date, dateRight: Number): Number
    fun differenceInQuarters(dateLeft: Number, dateRight: Date): Number
    fun differenceInQuarters(dateLeft: Number, dateRight: Number): Number
    fun differenceInSeconds(dateLeft: Date, dateRight: Date): Number
    fun differenceInSeconds(dateLeft: Date, dateRight: Number): Number
    fun differenceInSeconds(dateLeft: Number, dateRight: Date): Number
    fun differenceInSeconds(dateLeft: Number, dateRight: Number): Number
    fun differenceInWeeks(dateLeft: Date, dateRight: Date): Number
    fun differenceInWeeks(dateLeft: Date, dateRight: Number): Number
    fun differenceInWeeks(dateLeft: Number, dateRight: Date): Number
    fun differenceInWeeks(dateLeft: Number, dateRight: Number): Number
    fun differenceInYears(dateLeft: Date, dateRight: Date): Number
    fun differenceInYears(dateLeft: Date, dateRight: Number): Number
    fun differenceInYears(dateLeft: Number, dateRight: Date): Number
    fun differenceInYears(dateLeft: Number, dateRight: Number): Number
    fun eachDayOfInterval(interval: Interval, options: `T$5` = definedExternally): Array<Date>
    fun eachMonthOfInterval(interval: Interval): Array<Date>
    fun eachWeekendOfInterval(interval: Interval): Array<Date>
    fun eachWeekendOfMonth(date: Date): Array<Date>
    fun eachWeekendOfMonth(date: Number): Array<Date>
    fun eachWeekendOfYear(date: Date): Array<Date>
    fun eachWeekendOfYear(date: Number): Array<Date>
    fun eachWeekOfInterval(interval: Interval, options: `T$24` = definedExternally): Array<Date>
    fun eachYearOfInterval(interval: Interval): Array<Date>
    fun endOfDay(date: Date): Date
    fun endOfDay(date: Number): Date
    fun endOfDecade(date: Date, options: `T$6` = definedExternally): Date
    fun endOfDecade(date: Number, options: `T$6` = definedExternally): Date
    fun endOfHour(date: Date): Date
    fun endOfHour(date: Number): Date
    fun endOfISOWeek(date: Date): Date
    fun endOfISOWeek(date: Number): Date
    fun endOfISOWeekYear(date: Date): Date
    fun endOfISOWeekYear(date: Number): Date
    fun endOfMinute(date: Date): Date
    fun endOfMinute(date: Number): Date
    fun endOfMonth(date: Date): Date
    fun endOfMonth(date: Number): Date
    fun endOfQuarter(date: Date): Date
    fun endOfQuarter(date: Number): Date
    fun endOfSecond(date: Date): Date
    fun endOfSecond(date: Number): Date
    fun endOfToday(): Date
    fun endOfTomorrow(): Date
    fun endOfWeek(date: Date, options: `T$24` = definedExternally): Date
    fun endOfWeek(date: Number, options: `T$24` = definedExternally): Date
    fun endOfYear(date: Date): Date
    fun endOfYear(date: Number): Date
    fun endOfYesterday(): Date
    fun format(date: Date, format: String, options: `T$25` = definedExternally): String
    fun format(date: Number, format: String, options: `T$25` = definedExternally): String
    fun formatDistance(date: Date, baseDate: Date, options: `T$26` = definedExternally): String
    fun formatDistance(date: Date, baseDate: Number, options: `T$26` = definedExternally): String
    fun formatDistance(date: Number, baseDate: Date, options: `T$26` = definedExternally): String
    fun formatDistance(date: Number, baseDate: Number, options: `T$26` = definedExternally): String
    fun formatDistanceStrict(date: Date, baseDate: Date, options: `T$27` = definedExternally): String
    fun formatDistanceStrict(date: Date, baseDate: Number, options: `T$27` = definedExternally): String
    fun formatDistanceStrict(date: Number, baseDate: Date, options: `T$27` = definedExternally): String
    fun formatDistanceStrict(date: Number, baseDate: Number, options: `T$27` = definedExternally): String
    fun formatDistanceToNow(date: Date, options: `T$26` = definedExternally): String
    fun formatDistanceToNow(date: Number, options: `T$26` = definedExternally): String
    fun formatDistanceToNowStrict(date: Date, options: `T$27` = definedExternally): String
    fun formatDistanceToNowStrict(date: Number, options: `T$27` = definedExternally): String
    fun formatISO(date: Date, options: `T$10` = definedExternally): String
    fun formatISO(date: Number, options: `T$10` = definedExternally): String
    fun formatISO9075(date: Date, options: `T$10` = definedExternally): String
    fun formatISO9075(date: Number, options: `T$10` = definedExternally): String
    fun formatRelative(date: Date, baseDate: Date, options: `T$24` = definedExternally): String
    fun formatRelative(date: Date, baseDate: Number, options: `T$24` = definedExternally): String
    fun formatRelative(date: Number, baseDate: Date, options: `T$24` = definedExternally): String
    fun formatRelative(date: Number, baseDate: Number, options: `T$24` = definedExternally): String
    fun formatRFC3339(date: Date, options: `T$11` = definedExternally): String
    fun formatRFC3339(date: Number, options: `T$11` = definedExternally): String
    fun formatRFC7231(date: Date): String
    fun formatRFC7231(date: Number): String
    fun fromUnixTime(unixTime: Number): Date
    fun getDate(date: Date): Number
    fun getDate(date: Number): Number
    fun getDay(date: Date): String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    fun getDay(date: Number): String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    fun getDayOfYear(date: Date): Number
    fun getDayOfYear(date: Number): Number
    fun getDaysInMonth(date: Date): Number
    fun getDaysInMonth(date: Number): Number
    fun getDaysInYear(date: Date): Number
    fun getDaysInYear(date: Number): Number
    fun getDecade(date: Date): Number
    fun getDecade(date: Number): Number
    fun getHours(date: Date): Number
    fun getHours(date: Number): Number
    fun getISODay(date: Date): Number
    fun getISODay(date: Number): Number
    fun getISOWeek(date: Date): Number
    fun getISOWeek(date: Number): Number
    fun getISOWeeksInYear(date: Date): Number
    fun getISOWeeksInYear(date: Number): Number
    fun getISOWeekYear(date: Date): Number
    fun getISOWeekYear(date: Number): Number
    fun getMilliseconds(date: Date): Number
    fun getMilliseconds(date: Number): Number
    fun getMinutes(date: Date): Number
    fun getMinutes(date: Number): Number
    fun getMonth(date: Date): Number
    fun getMonth(date: Number): Number
    fun getOverlappingDaysInIntervals(intervalLeft: Interval, intervalRight: Interval): Number
    fun getQuarter(date: Date): Number
    fun getQuarter(date: Number): Number
    fun getSeconds(date: Date): Number
    fun getSeconds(date: Number): Number
    fun getTime(date: Date): Number
    fun getTime(date: Number): Number
    fun getUnixTime(date: Date): Number
    fun getUnixTime(date: Number): Number
    fun getWeek(date: Date, options: `T$28` = definedExternally): Number
    fun getWeek(date: Number, options: `T$28` = definedExternally): Number
    fun getWeekOfMonth(date: Date, options: `T$24` = definedExternally): Number
    fun getWeekOfMonth(date: Number, options: `T$24` = definedExternally): Number
    fun getWeeksInMonth(date: Date, options: `T$24` = definedExternally): Number
    fun getWeeksInMonth(date: Number, options: `T$24` = definedExternally): Number
    fun getWeekYear(date: Date, options: `T$28` = definedExternally): Number
    fun getWeekYear(date: Number, options: `T$28` = definedExternally): Number
    fun getYear(date: Date): Number
    fun getYear(date: Number): Number
    fun isAfter(date: Date, dateToCompare: Date): Boolean
    fun isAfter(date: Date, dateToCompare: Number): Boolean
    fun isAfter(date: Number, dateToCompare: Date): Boolean
    fun isAfter(date: Number, dateToCompare: Number): Boolean
    fun isBefore(date: Date, dateToCompare: Date): Boolean
    fun isBefore(date: Date, dateToCompare: Number): Boolean
    fun isBefore(date: Number, dateToCompare: Date): Boolean
    fun isBefore(date: Number, dateToCompare: Number): Boolean
    fun isDate(value: Any): Boolean
    fun isEqual(dateLeft: Date, dateRight: Date): Boolean
    fun isEqual(dateLeft: Date, dateRight: Number): Boolean
    fun isEqual(dateLeft: Number, dateRight: Date): Boolean
    fun isEqual(dateLeft: Number, dateRight: Number): Boolean
    fun isExists(year: Number, month: Number, day: Number): Boolean
    fun isFirstDayOfMonth(date: Date): Boolean
    fun isFirstDayOfMonth(date: Number): Boolean
    fun isFriday(date: Date): Boolean
    fun isFriday(date: Number): Boolean
    fun isFuture(date: Date): Boolean
    fun isFuture(date: Number): Boolean
    fun isLastDayOfMonth(date: Date): Boolean
    fun isLastDayOfMonth(date: Number): Boolean
    fun isLeapYear(date: Date): Boolean
    fun isLeapYear(date: Number): Boolean
    fun isMonday(date: Date): Boolean
    fun isMonday(date: Number): Boolean
    fun isPast(date: Date): Boolean
    fun isPast(date: Number): Boolean
    fun isSameDay(dateLeft: Date, dateRight: Date): Boolean
    fun isSameDay(dateLeft: Date, dateRight: Number): Boolean
    fun isSameDay(dateLeft: Number, dateRight: Date): Boolean
    fun isSameDay(dateLeft: Number, dateRight: Number): Boolean
    fun isSameHour(dateLeft: Date, dateRight: Date): Boolean
    fun isSameHour(dateLeft: Date, dateRight: Number): Boolean
    fun isSameHour(dateLeft: Number, dateRight: Date): Boolean
    fun isSameHour(dateLeft: Number, dateRight: Number): Boolean
    fun isSameISOWeek(dateLeft: Date, dateRight: Date): Boolean
    fun isSameISOWeek(dateLeft: Date, dateRight: Number): Boolean
    fun isSameISOWeek(dateLeft: Number, dateRight: Date): Boolean
    fun isSameISOWeek(dateLeft: Number, dateRight: Number): Boolean
    fun isSameISOWeekYear(dateLeft: Date, dateRight: Date): Boolean
    fun isSameISOWeekYear(dateLeft: Date, dateRight: Number): Boolean
    fun isSameISOWeekYear(dateLeft: Number, dateRight: Date): Boolean
    fun isSameISOWeekYear(dateLeft: Number, dateRight: Number): Boolean
    fun isSameMinute(dateLeft: Date, dateRight: Date): Boolean
    fun isSameMinute(dateLeft: Date, dateRight: Number): Boolean
    fun isSameMinute(dateLeft: Number, dateRight: Date): Boolean
    fun isSameMinute(dateLeft: Number, dateRight: Number): Boolean
    fun isSameMonth(dateLeft: Date, dateRight: Date): Boolean
    fun isSameMonth(dateLeft: Date, dateRight: Number): Boolean
    fun isSameMonth(dateLeft: Number, dateRight: Date): Boolean
    fun isSameMonth(dateLeft: Number, dateRight: Number): Boolean
    fun isSameQuarter(dateLeft: Date, dateRight: Date): Boolean
    fun isSameQuarter(dateLeft: Date, dateRight: Number): Boolean
    fun isSameQuarter(dateLeft: Number, dateRight: Date): Boolean
    fun isSameQuarter(dateLeft: Number, dateRight: Number): Boolean
    fun isSameSecond(dateLeft: Date, dateRight: Date): Boolean
    fun isSameSecond(dateLeft: Date, dateRight: Number): Boolean
    fun isSameSecond(dateLeft: Number, dateRight: Date): Boolean
    fun isSameSecond(dateLeft: Number, dateRight: Number): Boolean
    fun isSameWeek(dateLeft: Date, dateRight: Date, options: `T$24` = definedExternally): Boolean
    fun isSameWeek(dateLeft: Date, dateRight: Number, options: `T$24` = definedExternally): Boolean
    fun isSameWeek(dateLeft: Number, dateRight: Date, options: `T$24` = definedExternally): Boolean
    fun isSameWeek(dateLeft: Number, dateRight: Number, options: `T$24` = definedExternally): Boolean
    fun isSameYear(dateLeft: Date, dateRight: Date): Boolean
    fun isSameYear(dateLeft: Date, dateRight: Number): Boolean
    fun isSameYear(dateLeft: Number, dateRight: Date): Boolean
    fun isSameYear(dateLeft: Number, dateRight: Number): Boolean
    fun isSaturday(date: Date): Boolean
    fun isSaturday(date: Number): Boolean
    fun isSunday(date: Date): Boolean
    fun isSunday(date: Number): Boolean
    fun isThisHour(date: Date): Boolean
    fun isThisHour(date: Number): Boolean
    fun isThisISOWeek(date: Date): Boolean
    fun isThisISOWeek(date: Number): Boolean
    fun isThisMinute(date: Date): Boolean
    fun isThisMinute(date: Number): Boolean
    fun isThisMonth(date: Date): Boolean
    fun isThisMonth(date: Number): Boolean
    fun isThisQuarter(date: Date): Boolean
    fun isThisQuarter(date: Number): Boolean
    fun isThisSecond(date: Date): Boolean
    fun isThisSecond(date: Number): Boolean
    fun isThisWeek(date: Date, options: `T$24` = definedExternally): Boolean
    fun isThisWeek(date: Number, options: `T$24` = definedExternally): Boolean
    fun isThisYear(date: Date): Boolean
    fun isThisYear(date: Number): Boolean
    fun isThursday(date: Date): Boolean
    fun isThursday(date: Number): Boolean
    fun isToday(date: Date): Boolean
    fun isToday(date: Number): Boolean
    fun isTomorrow(date: Date): Boolean
    fun isTomorrow(date: Number): Boolean
    fun isTuesday(date: Date): Boolean
    fun isTuesday(date: Number): Boolean
    fun isValid(date: Any): Boolean
    fun isWednesday(date: Date): Boolean
    fun isWednesday(date: Number): Boolean
    fun isWeekend(date: Date): Boolean
    fun isWeekend(date: Number): Boolean
    fun isWithinInterval(date: Date, interval: Interval): Boolean
    fun isWithinInterval(date: Number, interval: Interval): Boolean
    fun isYesterday(date: Date): Boolean
    fun isYesterday(date: Number): Boolean
    fun lastDayOfDecade(date: Date): Date
    fun lastDayOfDecade(date: Number): Date
    fun lastDayOfISOWeek(date: Date): Date
    fun lastDayOfISOWeek(date: Number): Date
    fun lastDayOfISOWeekYear(date: Date): Date
    fun lastDayOfISOWeekYear(date: Number): Date
    fun lastDayOfMonth(date: Date): Date
    fun lastDayOfMonth(date: Number): Date
    fun lastDayOfQuarter(date: Date, options: `T$6` = definedExternally): Date
    fun lastDayOfQuarter(date: Number, options: `T$6` = definedExternally): Date
    fun lastDayOfWeek(date: Date, options: `T$24` = definedExternally): Date
    fun lastDayOfWeek(date: Number, options: `T$24` = definedExternally): Date
    fun lastDayOfYear(date: Date): Date
    fun lastDayOfYear(date: Number): Date
    fun lightFormat(date: Date, format: String): String
    fun lightFormat(date: Number, format: String): String
    fun max(datesArray: Array<dynamic /* Date | Number */>): Date
    fun min(datesArray: Array<dynamic /* Date | Number */>): Date
    fun parse(dateString: String, formatString: String, referenceDate: Date, options: `T$29` = definedExternally): Date
    fun parse(
        dateString: String,
        formatString: String,
        referenceDate: Number,
        options: `T$29` = definedExternally
    ): Date

    fun parseISO(argument: String, options: `T$6` = definedExternally): Date
    fun parseJSON(argument: String): Date
    fun parseJSON(argument: Number): Date
    fun parseJSON(argument: Date): Date
    fun roundToNearestMinutes(date: Date, options: `T$14` = definedExternally): Date
    fun roundToNearestMinutes(date: Number, options: `T$14` = definedExternally): Date
    fun set(date: Date, values: `T$15`): Date
    fun set(date: Number, values: `T$15`): Date
    fun setDate(date: Date, dayOfMonth: Number): Date
    fun setDate(date: Number, dayOfMonth: Number): Date
    fun setDay(date: Date, day: Number, options: `T$24` = definedExternally): Date
    fun setDay(date: Number, day: Number, options: `T$24` = definedExternally): Date
    fun setDayOfYear(date: Date, dayOfYear: Number): Date
    fun setDayOfYear(date: Number, dayOfYear: Number): Date
    fun setHours(date: Date, hours: Number): Date
    fun setHours(date: Number, hours: Number): Date
    fun setISODay(date: Date, day: Number): Date
    fun setISODay(date: Number, day: Number): Date
    fun setISOWeek(date: Date, isoWeek: Number): Date
    fun setISOWeek(date: Number, isoWeek: Number): Date
    fun setISOWeekYear(date: Date, isoWeekYear: Number): Date
    fun setISOWeekYear(date: Number, isoWeekYear: Number): Date
    fun setMilliseconds(date: Date, milliseconds: Number): Date
    fun setMilliseconds(date: Number, milliseconds: Number): Date
    fun setMinutes(date: Date, minutes: Number): Date
    fun setMinutes(date: Number, minutes: Number): Date
    fun setMonth(date: Date, month: Number): Date
    fun setMonth(date: Number, month: Number): Date
    fun setQuarter(date: Date, quarter: Number): Date
    fun setQuarter(date: Number, quarter: Number): Date
    fun setSeconds(date: Date, seconds: Number): Date
    fun setSeconds(date: Number, seconds: Number): Date
    fun setWeek(date: Date, week: Number, options: `T$28` = definedExternally): Date
    fun setWeek(date: Number, week: Number, options: `T$28` = definedExternally): Date
    fun setWeekYear(date: Date, weekYear: Number, options: `T$28` = definedExternally): Date
    fun setWeekYear(date: Number, weekYear: Number, options: `T$28` = definedExternally): Date
    fun setYear(date: Date, year: Number): Date
    fun setYear(date: Number, year: Number): Date
    fun startOfDay(date: Date): Date
    fun startOfDay(date: Number): Date
    fun startOfDecade(date: Date): Date
    fun startOfDecade(date: Number): Date
    fun startOfHour(date: Date): Date
    fun startOfHour(date: Number): Date
    fun startOfISOWeek(date: Date): Date
    fun startOfISOWeek(date: Number): Date
    fun startOfISOWeekYear(date: Date): Date
    fun startOfISOWeekYear(date: Number): Date
    fun startOfMinute(date: Date): Date
    fun startOfMinute(date: Number): Date
    fun startOfMonth(date: Date): Date
    fun startOfMonth(date: Number): Date
    fun startOfQuarter(date: Date): Date
    fun startOfQuarter(date: Number): Date
    fun startOfSecond(date: Date): Date
    fun startOfSecond(date: Number): Date
    fun startOfToday(): Date
    fun startOfTomorrow(): Date
    fun startOfWeek(date: Date, options: `T$24` = definedExternally): Date
    fun startOfWeek(date: Number, options: `T$24` = definedExternally): Date
    fun startOfWeekYear(date: Date, options: `T$28` = definedExternally): Date
    fun startOfWeekYear(date: Number, options: `T$28` = definedExternally): Date
    fun startOfYear(date: Date): Date
    fun startOfYear(date: Number): Date
    fun startOfYesterday(): Date
    fun sub(date: Date, duration: Duration): Date
    fun sub(date: Number, duration: Duration): Date
    fun subBusinessDays(date: Date, amount: Number): Date
    fun subBusinessDays(date: Number, amount: Number): Date
    fun subDays(date: Date, amount: Number): Date
    fun subDays(date: Number, amount: Number): Date
    fun subHours(date: Date, amount: Number): Date
    fun subHours(date: Number, amount: Number): Date
    fun subISOWeekYears(date: Date, amount: Number): Date
    fun subISOWeekYears(date: Number, amount: Number): Date
    fun subMilliseconds(date: Date, amount: Number): Date
    fun subMilliseconds(date: Number, amount: Number): Date
    fun subMinutes(date: Date, amount: Number): Date
    fun subMinutes(date: Number, amount: Number): Date
    fun subMonths(date: Date, amount: Number): Date
    fun subMonths(date: Number, amount: Number): Date
    fun subQuarters(date: Date, amount: Number): Date
    fun subQuarters(date: Number, amount: Number): Date
    fun subSeconds(date: Date, amount: Number): Date
    fun subSeconds(date: Number, amount: Number): Date
    fun subWeeks(date: Date, amount: Number): Date
    fun subWeeks(date: Number, amount: Number): Date
    fun subYears(date: Date, amount: Number): Date
    fun subYears(date: Number, amount: Number): Date
    fun toDate(argument: Date): Date
    fun toDate(argument: Number): Date
    var maxTime: Number
    var minTime: Number
}
