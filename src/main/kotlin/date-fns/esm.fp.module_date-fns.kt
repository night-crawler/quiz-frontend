@file:JsModule("date-fns/esm/fp")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package date.fns.esm.fp

import CurriedFn1
import CurriedFn2
import CurriedFn3
import CurriedFn4
import Duration
import Interval
import date.fns.`T$11`
import date.fns.`T$14`
import date.fns.`T$3`
import date.fns.`T$5`
import date.fns.`T$6`
import date.fns.fp.`T$16`
import date.fns.fp.`T$17`
import date.fns.fp.`T$18`
import date.fns.fp.`T$19`
import date.fns.fp.`T$20`
import date.fns.fp.`T$21`
import date.fns.fp.`T$22`
import date.fns.fp.`T$23`
import kotlin.js.Date

@JsName("default")
external var add: CurriedFn2<Duration, dynamic /* Date | Number */, Date>

@JsName("default")
external var addBusinessDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addHours: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addISOWeekYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addMilliseconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addMinutes: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addMonths: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addQuarters: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addSeconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addWeeks: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var addYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var areIntervalsOverlapping: CurriedFn2<Interval, Interval, Boolean>

@JsName("default")
external var areIntervalsOverlappingWithOptions: CurriedFn3<`T$3`, Interval, Interval, Boolean>

@JsName("default")
external var closestIndexTo: CurriedFn2<Array<dynamic /* Date | Number */>, dynamic /* Date | Number */, Number>

@JsName("default")
external var closestTo: CurriedFn2<Array<dynamic /* Date | Number */>, dynamic /* Date | Number */, Date>

@JsName("default")
external var compareAsc: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var compareDesc: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInBusinessDays: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarDays: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarISOWeeks: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarISOWeekYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarMonths: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarQuarters: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarWeeks: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarWeeksWithOptions: CurriedFn3<`T$16`, dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInCalendarYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInDays: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInHours: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInISOWeekYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInMilliseconds: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInMinutes: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInMonths: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInQuarters: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInSeconds: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInWeeks: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var differenceInYears: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Number>

@JsName("default")
external var eachDayOfInterval: CurriedFn1<Interval, Array<Date>>

@JsName("default")
external var eachDayOfIntervalWithOptions: CurriedFn2<`T$5`, Interval, Array<Date>>

@JsName("default")
external var eachMonthOfInterval: CurriedFn1<Interval, Array<Date>>

@JsName("default")
external var eachWeekendOfInterval: CurriedFn1<Interval, Array<Date>>

@JsName("default")
external var eachWeekendOfMonth: CurriedFn1<dynamic /* Date | Number */, Array<Date>>

@JsName("default")
external var eachWeekendOfYear: CurriedFn1<dynamic /* Date | Number */, Array<Date>>

@JsName("default")
external var eachWeekOfInterval: CurriedFn1<Interval, Array<Date>>

@JsName("default")
external var eachWeekOfIntervalWithOptions: CurriedFn2<`T$16`, Interval, Array<Date>>

@JsName("default")
external var eachYearOfInterval: CurriedFn1<Interval, Array<Date>>

@JsName("default")
external var endOfDay: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfDecade: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfDecadeWithOptions: CurriedFn2<`T$6`, dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfHour: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfISOWeek: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfMinute: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfMonth: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfQuarter: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfSecond: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfWeek: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfWeekWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Date>

@JsName("default")
external var endOfYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var format: CurriedFn2<String, dynamic /* Date | Number */, String>

@JsName("default")
external var formatDistance: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, String>

@JsName("default")
external var formatDistanceStrict: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, String>

@JsName("default")
external var formatDistanceStrictWithOptions: CurriedFn3<`T$17`, dynamic /* Date | Number */, dynamic /* Date | Number */, String>

@JsName("default")
external var formatDistanceWithOptions: CurriedFn3<`T$18`, dynamic /* Date | Number */, dynamic /* Date | Number */, String>

@JsName("default")
external var formatISO: CurriedFn1<dynamic /* Date | Number */, String>

@JsName("default")
external var formatISO9075: CurriedFn1<dynamic /* Date | Number */, String>

@JsName("default")
external var formatISO9075WithOptions: CurriedFn2<`T$19`, dynamic /* Date | Number */, String>

@JsName("default")
external var formatISOWithOptions: CurriedFn2<`T$19`, dynamic /* Date | Number */, String>

@JsName("default")
external var formatRelative: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, String>

@JsName("default")
external var formatRelativeWithOptions: CurriedFn3<`T$16`, dynamic /* Date | Number */, dynamic /* Date | Number */, String>

@JsName("default")
external var formatRFC3339: CurriedFn1<dynamic /* Date | Number */, String>

@JsName("default")
external var formatRFC3339WithOptions: CurriedFn2<`T$11`, dynamic /* Date | Number */, String>

@JsName("default")
external var formatRFC7231: CurriedFn1<dynamic /* Date | Number */, String>

@JsName("default")
external var formatWithOptions: CurriedFn3<`T$20`, String, dynamic /* Date | Number */, String>

@JsName("default")
external var fromUnixTime: CurriedFn1<Number, Date>

@JsName("default")
external var getDate: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getDay: CurriedFn1<dynamic /* Date | Number */, String /* 0 | 1 | 2 | 3 | 4 | 5 | 6 */>

@JsName("default")
external var getDayOfYear: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getDaysInMonth: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getDaysInYear: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getDecade: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getHours: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getISODay: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getISOWeek: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getISOWeeksInYear: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getMilliseconds: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getMinutes: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getMonth: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getOverlappingDaysInIntervals: CurriedFn2<Interval, Interval, Number>

@JsName("default")
external var getQuarter: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getSeconds: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getTime: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getUnixTime: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeek: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeekOfMonth: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeekOfMonthWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeeksInMonth: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeeksInMonthWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeekWithOptions: CurriedFn2<`T$21`, dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeekYear: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var getWeekYearWithOptions: CurriedFn2<`T$21`, dynamic /* Date | Number */, Number>

@JsName("default")
external var getYear: CurriedFn1<dynamic /* Date | Number */, Number>

@JsName("default")
external var isAfter: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isBefore: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isDate: CurriedFn1<Any, Boolean>

@JsName("default")
external var isEqual: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isExists: CurriedFn3<Number, Number, Number, Boolean>

@JsName("default")
external var isFirstDayOfMonth: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isFriday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isLastDayOfMonth: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isLeapYear: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isMonday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameDay: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameHour: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameISOWeek: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameISOWeekYear: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameMinute: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameMonth: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameQuarter: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameSecond: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameWeek: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameWeekWithOptions: CurriedFn3<`T$16`, dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSameYear: CurriedFn2<dynamic /* Date | Number */, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSaturday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isSunday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isThursday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isTuesday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isValid: CurriedFn1<Any, Boolean>

@JsName("default")
external var isWednesday: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isWeekend: CurriedFn1<dynamic /* Date | Number */, Boolean>

@JsName("default")
external var isWithinInterval: CurriedFn2<Interval, dynamic /* Date | Number */, Boolean>

@JsName("default")
external var lastDayOfDecade: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfISOWeek: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfMonth: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfQuarter: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfQuarterWithOptions: CurriedFn2<`T$6`, dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfWeek: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfWeekWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Date>

@JsName("default")
external var lastDayOfYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var lightFormat: CurriedFn2<String, dynamic /* Date | Number */, String>

@JsName("default")
external var max: CurriedFn1<Array<dynamic /* Date | Number */>, Date>

@JsName("default")
external var min: CurriedFn1<Array<dynamic /* Date | Number */>, Date>

@JsName("default")
external var parse: CurriedFn3<dynamic /* Date | Number */, String, String, Date>

@JsName("default")
external var parseISO: CurriedFn1<String, Date>

@JsName("default")
external var parseISOWithOptions: CurriedFn2<`T$6`, String, Date>

@JsName("default")
external var parseJSON: CurriedFn1<dynamic /* String | Number | Date */, Date>

@JsName("default")
external var parseWithOptions: CurriedFn4<`T$22`, dynamic /* Date | Number */, String, String, Date>

@JsName("default")
external var roundToNearestMinutes: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var roundToNearestMinutesWithOptions: CurriedFn2<`T$14`, dynamic /* Date | Number */, Date>

@JsName("default")
external var set: CurriedFn2<`T$23`, dynamic /* Date | Number */, Date>

@JsName("default")
external var setDate: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setDay: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setDayOfYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setDayWithOptions: CurriedFn3<`T$16`, Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setHours: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setISODay: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setISOWeek: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setISOWeekYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setMilliseconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setMinutes: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setMonth: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setQuarter: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setSeconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setWeek: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setWeekWithOptions: CurriedFn3<`T$21`, Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setWeekYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setWeekYearWithOptions: CurriedFn3<`T$21`, Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var setYear: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfDay: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfDecade: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfHour: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfISOWeek: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfISOWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfMinute: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfMonth: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfQuarter: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfSecond: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfWeek: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfWeekWithOptions: CurriedFn2<`T$16`, dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfWeekYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfWeekYearWithOptions: CurriedFn2<`T$21`, dynamic /* Date | Number */, Date>

@JsName("default")
external var startOfYear: CurriedFn1<dynamic /* Date | Number */, Date>

@JsName("default")
external var sub: CurriedFn2<Duration, dynamic /* Date | Number */, Date>

@JsName("default")
external var subBusinessDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subDays: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subHours: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subISOWeekYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subMilliseconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subMinutes: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subMonths: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subQuarters: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subSeconds: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subWeeks: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var subYears: CurriedFn2<Number, dynamic /* Date | Number */, Date>

@JsName("default")
external var toDate: CurriedFn1<dynamic /* Date | Number */, Date>

external var maxTime: Number

external var minTime: Number
