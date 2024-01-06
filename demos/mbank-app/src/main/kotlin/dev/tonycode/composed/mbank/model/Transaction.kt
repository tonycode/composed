package dev.tonycode.composed.mbank.model

import java.math.BigDecimal


data class Transaction(

    /** timestamp, UTC millis */
    val performedAt: Long,

    val amount: BigDecimal,

    val isCleared: Boolean,

    /** e.g. "Zabka" or "mTransfer" */
    val merchant: String,

    val icon: String,

)
