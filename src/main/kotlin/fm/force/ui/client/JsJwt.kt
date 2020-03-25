package fm.force.ui.client

import fm.force.ui.util.atob
import fm.force.ui.util.getValue
import kotlin.js.Date
import kotlin.js.Json


@Suppress("MemberVisibilityCanBePrivate")
class JsJwt private constructor(val header: JwtHeader, val claims: JwtClaims) {
    class JwtHeader(val json: Json) : Json by json {
        val alg: String? by json
        val typ: String? by json
        val cty: String? by json

        override fun toString(): String {
            return "JwtHeader(alg=$alg typ=$typ cty=$cty)"
        }

        override fun get(propertyName: String): Any? = json[propertyName]
    }

    class JwtClaims(val json: Json) : Json by json {
        val iss: String? by json
        val sub: String? by json
        val aud: String? by json
        val exp: Date? by lazy { json["exp"]?.let { Date(it.toString().toLong() * 1000) } }
        val nbf: Date? by lazy { json["nbf"]?.let { Date(it.toString().toLong() * 1000) } }
        val iat: Date? by lazy { json["iat"]?.let { Date(it.toString().toLong() * 1000) } }
        val jti: String? by json

        override fun toString(): String {
            return "JwtClaims(iss=$iss sub=$sub aud=$aud exp=$exp nbf=$nbf iat=$iat jti=$jti)"
        }

        // Otherwise, there will be TypeError: this.json.get is not a function
        override fun get(propertyName: String): Any? = json[propertyName]
    }

    companion object {
        fun decode(raw: String): JsJwt {
            val (rawHeader, rawClaims) = raw.split(".").subList(0, 2).map { atob(it) }

            val header = JwtHeader(JSON.parse(rawHeader))
            val claims = JwtClaims(JSON.parse(rawClaims))

            return JsJwt(header, claims)
        }
    }

    fun isValid(now: Date = Date()): Boolean {
        val nbf = this.claims.nbf ?: Date(0)
        val exp = this.claims.exp ?: Date(now.getTime() + 86400 * 1000)
        if (nbf <= now && exp >= now) {
            return true
        }
        return false
    }
}

operator fun Date.compareTo(date: Date): Int =
    (this.getTime() - date.getTime()).compareTo(0)
