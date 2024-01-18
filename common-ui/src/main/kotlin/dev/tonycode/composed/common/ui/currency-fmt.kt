package dev.tonycode.composed.common.ui

import java.math.BigDecimal
import java.math.RoundingMode


internal const val defaultCurrency: String = "PLN"

fun BigDecimal.fmtAsAmount(
    withSignForPositive: Boolean = false,
    withCurrency: Boolean = true,
    currency: String = defaultCurrency,
): String {
    val sb = StringBuilder()

    if (withSignForPositive) {
        sb.append(
            if (this.signum() > 0) "+" else ""
        )
    }

    sb.append(
        this.setScale(2, RoundingMode.HALF_EVEN).toString()
    )

    if (withCurrency) {
        sb.append(UnicodeChars.thinSpace).append(currency)
    }

    return sb.toString()
}
