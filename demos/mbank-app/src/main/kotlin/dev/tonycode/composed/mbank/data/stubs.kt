package dev.tonycode.composed.mbank.data

import dev.tonycode.composed.common.ui.hoursToMillis
import dev.tonycode.composed.common.ui.minutesToMillis
import dev.tonycode.composed.mbank.model.AccountStats
import dev.tonycode.composed.mbank.model.AccountSummary
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.model.UserProfile
import java.math.BigDecimal


val stubUserId = "user-1q2w3e"

private val stubAccountId = "account-4rfv5tgb"

private val stubCurrency = "PLN"

val stubUserProfile = UserProfile(
    name = "DarkPlayer",
    accountId = stubAccountId,
)

val stubAccountSummary = AccountSummary(
    currency = stubCurrency,
    fundsAvailable = BigDecimal(21.37),
)

val stubAccountStats = AccountStats(
    spentThisMonth = BigDecimal(420.69),
    spentDaily = arrayOf(
        BigDecimal(5),
        BigDecimal(42.25),
        BigDecimal(25.10),
        BigDecimal(9.00),
        BigDecimal(14.50),
    ),
)

val stubTransactions = listOf(
    Transaction(
        performedAt = System.currentTimeMillis(),  // now
        amount = BigDecimal(-42),
        isCleared = false,
        merchant = "Shopee",
        icon = "transaction_cart.png",
    ),
    Transaction(
        performedAt = (System.currentTimeMillis() - 24.hoursToMillis()),  // 1 day before
        amount = BigDecimal(-9.10),
        isCleared = false,
        merchant = "Rossmann",
        icon = "transaction_card.png",
    ),
    Transaction(
        performedAt = (System.currentTimeMillis() - 24.hoursToMillis() - 30.minutesToMillis()),  // 1 day before
        amount = BigDecimal(21.00),
        isCleared = true,
        merchant = "mTransfer",
        icon = "transaction_transfer.png",
    ),
    Transaction(
        performedAt = (System.currentTimeMillis() - (2*24).hoursToMillis()),  // 2 days before
        amount = BigDecimal(-12.34),
        isCleared = true,
        merchant = "Zabka",
        icon = "transaction_cart.png",
    ),
)
