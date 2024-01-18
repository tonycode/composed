package dev.tonycode.composed.common.ui

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


internal const val defaultCurrency: String = "PLN"

private val amountDecimalFormat by lazy {
    DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).apply {
        groupingSize = 3

        // "thin space"
        decimalFormatSymbols = decimalFormatSymbols.apply {
            groupingSeparator = UnicodeChars.hairSpace
        }
    }
}

fun BigDecimal.fmtAsAmount(
    withSignForPositive: Boolean = false,
    withCurrency: Boolean = true,
    currency: String = defaultCurrency,
): String {
    val sb = StringBuilder()

    // sign
    if (withSignForPositive) {
        sb.append(
            if (this.signum() > 0) "+" else ""
        )
    }

    // amount
    sb.append(
        amountDecimalFormat.format(
            this.setScale(2, RoundingMode.HALF_EVEN)
        )
    )

    // currency
    if (withCurrency) {
        sb.append(UnicodeChars.thinSpace).append(currency)
    }

    return sb.toString()
}
