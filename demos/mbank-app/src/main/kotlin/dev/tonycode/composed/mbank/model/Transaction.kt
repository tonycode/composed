package dev.tonycode.composed.mbank.model

import dev.tonycode.composed.common.ui.fmtAsEpochMillis
import java.math.BigDecimal


data class Transaction(

    /** timestamp, UTC millis */
    val performedAt: Long,

    val amount: BigDecimal,

    val isCleared: Boolean,

    /** e.g. "Zabka" or "mTransfer" */
    val merchant: String,

    val icon: String,

) {

    val performedDay: String
        get() = performedAt.fmtAsEpochMillis(withTime = false)

}
