@file:JsQualifier("util")
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")
package prismjs
import kotlin.js.*

external interface Identifier {
    var value: Number
}

external fun encode(tokens: String): dynamic /* String | Token | Array<dynamic /* String | Token */> */

external fun encode(tokens: Token): dynamic /* String | Token | Array<dynamic /* String | Token */> */

external fun encode(tokens: Array<dynamic /* String | Token */>): dynamic /* String | Token | Array<dynamic /* String | Token */> */

external fun type(o: Nothing?): String /* "Null" */

external fun type(o: Nothing?): String /* "Undefined" */

external fun type(o: Boolean): String /* "Boolean" */

external fun type(o: Boolean): String /* "Boolean" */

external fun type(o: Number): String /* "Number" */

external fun type(o: Number): String /* "Number" */

external fun type(o: String): String /* "String" */

external fun type(o: String): String /* "String" */

external fun type(o: Function<*>): String /* "Function" */

external fun type(o: RegExp): String /* "RegExp" */

external fun type(o: Array<Any>): String /* "Array" */

external fun type(o: Any): String

external fun objId(obj: Any): Identifier

external fun <T> clone(o: T): T
