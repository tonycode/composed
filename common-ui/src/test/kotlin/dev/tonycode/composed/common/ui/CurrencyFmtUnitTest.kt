package dev.tonycode.composed.common.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.math.BigDecimal


class CurrencyFmtUnitTest {

    @Test
    fun testFmtAsAmount() {
        // simple

        assertThat(BigDecimal(123.45).fmtAsAmount())
            .isEqualTo("+123.45${ UnicodeChars.thinSpace }$defaultCurrency")

        assertThat(BigDecimal(123.45).fmtAsAmount(withSignForPositive = false))
            .isEqualTo("123.45${ UnicodeChars.thinSpace }$defaultCurrency")

        assertThat(BigDecimal(123.45).fmtAsAmount(withCurrency = false))
            .isEqualTo("+123.45")

        assertThat(BigDecimal(123.45).fmtAsAmount(withSignForPositive = false, withCurrency = false))
            .isEqualTo("123.45")

        // zero

        assertThat(BigDecimal(0).fmtAsAmount())
            .isEqualTo("0.00${ UnicodeChars.thinSpace }$defaultCurrency")

        // negative

        assertThat(BigDecimal(-12.34).fmtAsAmount())
            .isEqualTo("-12.34${ UnicodeChars.thinSpace }$defaultCurrency")
    }

}
