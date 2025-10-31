package dev.tonycode.composed.common.currency.presentation

import com.google.common.truth.Truth.assertThat
import dev.tonycode.composed.common.currency.domain.DEFAULT_CURRENCY
import dev.tonycode.composed.common.strings.presentation.UnicodeChars
import org.junit.Test
import java.math.BigDecimal

class CurrencyFmtUnitTest {
    @Test
    fun `test fmtAsAmount() ext`() {
        // simple
        assertThat(BigDecimal(123.45).fmtAsAmount(withSignForPositive = true))
            .isEqualTo("+123.45${ UnicodeChars.THIN_SPACE }$DEFAULT_CURRENCY")

        assertThat(BigDecimal(123.45).fmtAsAmount())
            .isEqualTo("123.45${ UnicodeChars.THIN_SPACE }$DEFAULT_CURRENCY")

        assertThat(BigDecimal(123.45).fmtAsAmount(withSignForPositive = true, withCurrency = false))
            .isEqualTo("+123.45")

        assertThat(BigDecimal(123.45).fmtAsAmount(withCurrency = false))
            .isEqualTo("123.45")

        // zero
        assertThat(BigDecimal(0).fmtAsAmount())
            .isEqualTo("0.00${ UnicodeChars.THIN_SPACE }$DEFAULT_CURRENCY")

        // negative
        assertThat(BigDecimal(-12.34).fmtAsAmount())
            .isEqualTo("-12.34${ UnicodeChars.THIN_SPACE }$DEFAULT_CURRENCY")
    }
}
