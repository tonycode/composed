package dev.tonycode.composed.mbank.data

import dev.tonycode.composed.common.currency.domain.DEFAULT_CURRENCY
import dev.tonycode.composed.common.datetime.domain.hoursToMillis
import dev.tonycode.composed.common.datetime.domain.minutesToMillis
import dev.tonycode.composed.mbank.domain.entity.AccountStats
import dev.tonycode.composed.mbank.domain.entity.AccountSummary
import dev.tonycode.composed.mbank.domain.entity.Transaction
import dev.tonycode.composed.mbank.domain.entity.UserProfile
import java.math.BigDecimal

const val STUB_USER_ID = "user-1q2w3e"

private const val STUB_ACCOUNT_ID = "account-4rfv5tgb"

private const val STUB_CURRENCY = DEFAULT_CURRENCY

val stubUserProfile =
    UserProfile(
        name = "DarkPlayer",
        accountId = STUB_ACCOUNT_ID,
    )

val stubAccountSummary =
    AccountSummary(
        currency = STUB_CURRENCY,
        fundsAvailable = BigDecimal(12345.67),
    )

val stubAccountStats =
    AccountStats(
        spentThisMonth = BigDecimal(1234.56),
        spentDaily =
            arrayOf(
                BigDecimal(5),
                BigDecimal(42.25),
                BigDecimal(25.10),
                BigDecimal(9.00),
                BigDecimal(14.50),
            ),
    )

val stubTransactions =
    listOf(
        Transaction(
            performedAt = System.currentTimeMillis(), // now
            amount = BigDecimal(-42),
            isCleared = false,
            merchant = "Shopee",
            icon = "transaction_cart.png",
        ),
        Transaction(
            performedAt = (System.currentTimeMillis() - 24.hoursToMillis()), // 1 day before
            amount = BigDecimal(-9.10),
            isCleared = false,
            merchant = "Rossmann",
            icon = "transaction_card.png",
        ),
        Transaction(
            performedAt = (System.currentTimeMillis() - 24.hoursToMillis() - 30.minutesToMillis()), // 1 day before
            amount = BigDecimal(21.00),
            isCleared = true,
            merchant = "mTransfer",
            icon = "transaction_transfer.png",
        ),
        Transaction(
            performedAt = (System.currentTimeMillis() - (2 * 24).hoursToMillis()), // 2 days before
            amount = BigDecimal(-12.34),
            isCleared = true,
            merchant = "Zabka",
            icon = "transaction_cart.png",
        ),
    )
