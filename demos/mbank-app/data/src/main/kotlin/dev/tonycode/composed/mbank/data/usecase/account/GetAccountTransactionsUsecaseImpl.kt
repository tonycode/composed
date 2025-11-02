package dev.tonycode.composed.mbank.data.usecase.account

import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.domain.entity.Transaction
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountTransactionsUsecase
import kotlinx.coroutines.delay

class GetAccountTransactionsUsecaseImpl : GetAccountTransactionsUsecase {
    override suspend fun execute(
        accountId: String,
        from: Long?,
        to: Long?,
        limit: Int,
    ): List<Transaction> {
        delay(3500L)
        return stubTransactions.take(limit)
    }
}
