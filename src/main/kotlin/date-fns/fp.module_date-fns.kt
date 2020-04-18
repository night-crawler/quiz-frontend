@file:JsModule("date-fns/fp")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package date.fns.fp

import CurriedFn1
import CurriedFn2
import CurriedFn3
import CurriedFn4
import Duration
import Interval
import Locale
import date.fns.`T$11`
import date.fns.`T$14`
import date.fns.`T$3`
import date.fns.`T$5`
import date.fns.`T$6`
import kotlin.js.Date

external var add: CurriedFn2<Duration, dynamic /* Date | Number */, Date>

external var addBusinessDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addHours: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addISOWeekYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addMilliseconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addMinutes: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addMonths: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addQuarters: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addSeconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addWeeks: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var addYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var areIntervalsOverlapping: CurriedFn2<Interval, Interval, Boolean>

external var areIntervalsOverlappingWithOptions: CurriedFn3<`T$3`, Interval, Interval, Boolean>

external var closestIndexTo: CurriedFn2<Array<dynamic /* Date | Number */>, dynamic /* Date | Number */, Number>

external var closestTo: CurriedFn2<Array<dynamic /* Date | Number */>, dynamic /* Date | Number */, Date>

external var compareAsc: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var compareDesc: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInBusinessDays: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarDays: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarISOWeeks: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarISOWeekYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarMonths: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarQuarters: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarWeeks: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external interface `T$16` {
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

external var differenceInCalendarWeeksWithOptions: CurriedFn3<`T$16`, dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInCalendarYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInDays: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInHours: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInISOWeekYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInMilliseconds: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInMinutes: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInMonths: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInQuarters: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInSeconds: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInWeeks: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var differenceInYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

external var eachDayOfInterval: CurriedFn1<Interval, Array<Date>>

external var eachDayOfIntervalWithOptions: CurriedFn2<`T$5`, Interval, Array<Date>>

external var eachMonthOfInterval: CurriedFn1<Interval, Array<Date>>

external var eachWeekendOfInterval: CurriedFn1<Interval, Array<Date>>

external var eachWeekendOfMonth: CurriedFn1<dynamic /* Date | Number */, Array<Date>>

external var eachWeekendOfYear: CurriedFn1<dynamic /* Date | Number */, Array<Date>>

external var eachWeekOfInterval: CurriedFn1<Interval, Array<Date>>

external var eachWeekOfIntervalWithOptions: CurriedFn2<`T$16`, Interval, Array<Date>>

external var eachYearOfInterval: CurriedFn1<Interval, Array<Date>>

external var endOfDay: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfDecade: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfDecadeWithOptions: CurriedFn2<`T$6`, dynamic /* Date | Number */, Date>

external var endOfHour: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfISOWeek: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfMinute: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfMonth: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfQuarter: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfSecond: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfWeek: CurriedFn1<dynamic /* Date | Number */, Date>

external var endOfWeekWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Date>

external var endOfYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var format: CurriedFn2<String, dynamic /* Date | Number */, String>

external var formatDistance: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, String>

external var formatDistanceStrict: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, String>

external interface `T$17` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var roundingMethod: String /* 'floor' | 'ceil' | 'round' */
    var unit: String /* 'second' | 'minute' | 'hour' | 'day' | 'month' | 'year' */
    var addSuffix: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external var formatDistanceStrictWithOptions: CurriedFn3<`T$17`, dynamic /* Date | Number */, dynamic /* Date | Number */, String>

external interface `T$18` {
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
    var addSuffix: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var includeSeconds: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external var formatDistanceWithOptions: CurriedFn3<`T$18`, dynamic /* Date | Number */, dynamic /* Date | Number */, String>

external var formatISO: CurriedFn1<dynamic /* Date | Number */, String>

external var formatISO9075: CurriedFn1<dynamic /* Date | Number */, String>

external interface `T$19` {
    var representation: String /* 'complete' | 'date' | 'time' */
    var format: String /* 'extended' | 'basic' */
}

external var formatISO9075WithOptions: CurriedFn2<`T$19`, dynamic /* Date | Number */, String>

external var formatISOWithOptions: CurriedFn2<`T$19`, dynamic /* Date | Number */, String>

external var formatRelative: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, String>

external var formatRelativeWithOptions: CurriedFn3<`T$16`, dynamic /* Date | Number */, dynamic /* Date | Number */, String>

external var formatRFC3339: CurriedFn1<dynamic /* Date | Number */, String>

external var formatRFC3339WithOptions: CurriedFn2<`T$11`, dynamic /* Date | Number */, String>

external var formatRFC7231: CurriedFn1<dynamic /* Date | Number */, String>

external interface `T$20` {
    var useAdditionalDayOfYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var useAdditionalWeekYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var firstWeekContainsDate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

external var formatWithOptions: CurriedFn3<`T$20`, String, dynamic /* Date | Number */, String>

external var fromUnixTime: CurriedFn1<Number, Date>

external var getDate: CurriedFn1<dynamic /* Date | Number */, Number>

external var getDay: CurriedFn1<dynamic /* Date | Number */, String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */>

external var getDayOfYear: CurriedFn1<dynamic /* Date | Number */, Number>

external var getDaysInMonth: CurriedFn1<dynamic /* Date | Number */, Number>

external var getDaysInYear: CurriedFn1<dynamic /* Date | Number */, Number>

external var getDecade: CurriedFn1<dynamic /* Date | Number */, Number>

external var getHours: CurriedFn1<dynamic /* Date | Number */, Number>

external var getISODay: CurriedFn1<dynamic /* Date | Number */, Number>

external var getISOWeek: CurriedFn1<dynamic /* Date | Number */, Number>

external var getISOWeeksInYear: CurriedFn1<dynamic /* Date | Number */, Number>

external var getISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Number>

external var getMilliseconds: CurriedFn1<dynamic /* Date | Number */, Number>

external var getMinutes: CurriedFn1<dynamic /* Date | Number */, Number>

external var getMonth: CurriedFn1<dynamic /* Date | Number */, Number>

external var getOverlappingDaysInIntervals: CurriedFn2<Interval, Interval, Number>

external var getQuarter: CurriedFn1<dynamic /* Date | Number */, Number>

external var getSeconds: CurriedFn1<dynamic /* Date | Number */, Number>

external var getTime: CurriedFn1<dynamic /* Date | Number */, Number>

external var getUnixTime: CurriedFn1<dynamic /* Date | Number */, Number>

external var getWeek: CurriedFn1<dynamic /* Date | Number */, Number>

external var getWeekOfMonth: CurriedFn1<dynamic /* Date | Number */, Number>

external var getWeekOfMonthWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Number>

external var getWeeksInMonth: CurriedFn1<dynamic /* Date | Number */, Number>

external var getWeeksInMonthWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Number>

external interface `T$21` {
    var firstWeekContainsDate: String /* 1 | 2 | 3 | 4 | 5 | 6 | 7 */
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

external var getWeekWithOptions: CurriedFn2<`T$21`, dynamic /* Date | Number */, Number>

external var getWeekYear: CurriedFn1<dynamic /* Date | Number */, Number>

external var getWeekYearWithOptions: CurriedFn2<`T$21`, dynamic /* Date | Number */, Number>

external var getYear: CurriedFn1<dynamic /* Date | Number */, Number>

external var isAfter: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isBefore: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isDate: CurriedFn1<Any, Boolean>

external var isEqual: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isExists: CurriedFn3<Number, Number, Number, Boolean>

external var isFirstDayOfMonth: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isFriday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isLastDayOfMonth: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isLeapYear: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isMonday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isSameDay: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameHour: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameISOWeek: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameISOWeekYear: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameMinute: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameMonth: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameQuarter: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameSecond: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameWeek: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameWeekWithOptions: CurriedFn3<`T$16`, dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSameYear: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

external var isSaturday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isSunday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isThursday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isTuesday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isValid: CurriedFn1<Any, Boolean>

external var isWednesday: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isWeekend: CurriedFn1<dynamic /* Date | Number */, Boolean>

external var isWithinInterval: CurriedFn2<Interval, dynamic /* Date | Number */, Boolean>

external var lastDayOfDecade: CurriedFn1<dynamic /* Date | Number */, Date>

external var lastDayOfISOWeek: CurriedFn1<dynamic /* Date | Number */, Date>

external var lastDayOfISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var lastDayOfMonth: CurriedFn1<dynamic /* Date | Number */, Date>

external var lastDayOfQuarter: CurriedFn1<dynamic /* Date | Number */, Date>

external var lastDayOfQuarterWithOptions: CurriedFn2<`T$6`, dynamic /* Date | Number */, Date>

external var lastDayOfWeek: CurriedFn1<dynamic /* Date | Number */, Date>

external var lastDayOfWeekWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Date>

external var lastDayOfYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var lightFormat: CurriedFn2<String, dynamic /* Date | Number */, String>

external var max: CurriedFn1<Array<dynamic /* Date | Number */>, Date>

external var min: CurriedFn1<Array<dynamic /* Date | Number */>, Date>

external var parse: CurriedFn3<dynamic /* Date | Number */, String, String, Date>

external var parseISO: CurriedFn1<String, Date>

external var parseISOWithOptions: CurriedFn2<`T$6`, String, Date>

external var parseJSON: CurriedFn1<dynamic /* String | Number | Date */, Date>

external interface `T$22` {
    var useAdditionalDayOfYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var useAdditionalWeekYearTokens: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var firstWeekContainsDate: String /* 1 | 2 | 3 | 4 | 5 | 6 | 7 */
    var weekStartsOn: String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */
    var locale: Locale?
        get() = definedExternally
        set(value) = definedExternally
}

external var parseWithOptions: CurriedFn4<`T$22`, dynamic /* Date | Number */, String, String, Date>

external var roundToNearestMinutes: CurriedFn1<dynamic /* Date | Number */, Date>

external var roundToNearestMinutesWithOptions: CurriedFn2<`T$14`, dynamic /* Date | Number */, Date>

external interface `T$23` {
    var milliseconds: Number?
        get() = definedExternally
        set(value) = definedExternally
    var seconds: Number?
        get() = definedExternally
        set(value) = definedExternally
    var minutes: Number?
        get() = definedExternally
        set(value) = definedExternally
    var hours: Number?
        get() = definedExternally
        set(value) = definedExternally
    var date: Number?
        get() = definedExternally
        set(value) = definedExternally
    var month: Number?
        get() = definedExternally
        set(value) = definedExternally
    var year: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external var set: CurriedFn2<`T$23`, dynamic /* Date | Number */, Date>

external var setDate: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setDay: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setDayOfYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setDayWithOptions: CurriedFn3<`T$16`, Number, dynamic /* Date | Number */, Date>

external var setHours: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setISODay: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setISOWeek: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setISOWeekYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setMilliseconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setMinutes: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setMonth: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setQuarter: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setSeconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setWeek: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setWeekWithOptions: CurriedFn3<`T$21`, Number, dynamic /* Date | Number */, Date>

external var setWeekYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var setWeekYearWithOptions: CurriedFn3<`T$21`, Number, dynamic /* Date | Number */, Date>

external var setYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var startOfDay: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfDecade: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfHour: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfISOWeek: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfMinute: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfMonth: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfQuarter: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfSecond: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfWeek: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfWeekWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Date>

external var startOfWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var startOfWeekYearWithOptions: CurriedFn2<`T$21`, dynamic /* Date | Number */, Date>

external var startOfYear: CurriedFn1<dynamic /* Date | Number */, Date>

external var sub: CurriedFn2<Duration, dynamic /* Date | Number */, Date>

external var subBusinessDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subHours: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subISOWeekYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subMilliseconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subMinutes: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subMonths: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subQuarters: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subSeconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subWeeks: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var subYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

external var toDate: CurriedFn1<dynamic /* Date | Number */, Date>

external var maxTime: Number

external var minTime: Number
