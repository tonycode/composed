package dev.tonycode.composed.mbank.domain.entity

import java.math.BigDecimal

data class AccountSummary(
    val currency: String,
    val fundsAvailable: BigDecimal,
)
