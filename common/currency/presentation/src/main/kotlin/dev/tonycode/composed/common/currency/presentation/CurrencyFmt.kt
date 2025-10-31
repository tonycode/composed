package dev.tonycode.composed.common.currency.presentation

import dev.tonycode.composed.common.currency.domain.DEFAULT_CURRENCY
import dev.tonycode.composed.common.strings.presentation.UnicodeChars
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

private val amountDecimalFormat by lazy {
    DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).apply {
        groupingSize = 3

        // "thin space"
        decimalFormatSymbols =
            decimalFormatSymbols.apply {
                groupingSeparator = UnicodeChars.HAIR_SPACE
            }
    }
}

fun BigDecimal.fmtAsAmount(
    withSignForPositive: Boolean = false,
    withCurrency: Boolean = true,
    currency: String = DEFAULT_CURRENCY,
): String {
    val sb = StringBuilder()

    // sign
    if (withSignForPositive) {
        sb.append(
            if (this.signum() > 0) "+" else "",
        )
    }

    // amount
    sb.append(
        amountDecimalFormat.format(
            this.setScale(2, RoundingMode.HALF_EVEN),
        ),
    )

    // currency
    if (withCurrency) {
        sb.append(UnicodeChars.THIN_SPACE).append(currency)
    }

    return sb.toString()
}
