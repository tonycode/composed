package dev.tonycode.composed.mbank.model

import java.math.BigDecimal


data class AccountStats(

    val spentThisMonth: BigDecimal,

    /** last 5 daily spendings */
    val spentDaily: Array<BigDecimal>,

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccountStats

        if (spentThisMonth != other.spentThisMonth) return false
        return spentDaily.contentEquals(other.spentDaily)
    }

    override fun hashCode(): Int {
        var result = spentThisMonth.hashCode()
        result = 31 * result + spentDaily.contentHashCode()

        return result
    }

}
