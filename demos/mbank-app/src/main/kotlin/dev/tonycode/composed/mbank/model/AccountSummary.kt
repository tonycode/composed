package dev.tonycode.composed.mbank.model

import java.math.BigDecimal

data class AccountSummary(
    val currency: String,
    val fundsAvailable: BigDecimal,
)
