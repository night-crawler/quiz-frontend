package fm.force.ui.client

import fm.force.ui.util.btoa
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.js.Date
import kotlin.test.Test
import kotlinext.js.js

class JsJwtTest {
    private val jwtSample = listOf(
        "eyJhbGciOiJIUzI1NiJ9",
        "eyJpc3MiOiJxdWl6Iiwic3ViIjoidXNlckBleGFtcGxlLmNvbSIsImlhdCI6MTU4NDYyODg5Myw" +
            "iZXhwIjoxNTg0NjI4OTUzLCJ1c2VySWQiOiI3MzgxNTI1MDU0MjMxMzY5MCIsInJvbGVzIjpbXX0",
        "t6A1pNyQCfFa_9GY7vt6N4yK4BVqS3xq7lUhzH9OIIA"
    ).joinToString(".")

    @Test
    fun testDecode() {
        JsJwt.decode(jwtSample)
    }

    @Test
    fun testToString() {
        val jwt = JsJwt.decode(jwtSample)
        jwt.claims.toString().shouldNotBeBlank()
        jwt.header.toString().shouldNotBeBlank()
    }

    @Test
    fun testDates() {
        val jwt = JsJwt.decode(jwtSample)
        jwt.claims.iat.shouldBeInstanceOf<Date>()
        jwt.claims["iat"].shouldBeInstanceOf<Int>()
    }

    @Test
    fun testValidity() {
        val nowSeconds = (Date().getTime() / 1000).toInt()

        JsJwt.decode(
            prepareJwtTimeSampleClaims(
                nbf = nowSeconds,
                iat = nowSeconds - 10,
                exp = nowSeconds + 1
            )
        ).isValid().shouldBeTrue()

        JsJwt.decode(
            prepareJwtTimeSampleClaims(
                nbf = nowSeconds + 1,
                iat = nowSeconds - 10,
                exp = nowSeconds + 1
            )
        ).isValid().shouldBeFalse()

        JsJwt.decode(
            prepareJwtTimeSampleClaims(
                nbf = nowSeconds - 10,
                iat = nowSeconds - 10,
                exp = nowSeconds - 5
            )
        ).isValid().shouldBeFalse()
    }

    private fun prepareJwtTimeSampleClaims(nbf: Int, iat: Int, exp: Int): String {
        val jsClaims = js {
            this.nbf = nbf
            this.iat = iat
            this.exp = exp
        }
        val claimsString = btoa(JSON.stringify(jsClaims))
        val headerString = "eyJhbGciOiJIUzI1NiJ9"
        return "$headerString.$claimsString.DoesNotMatter"
    }
}
