package dev.tonycode.composed.mbank.usecase.account

import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.usecase.UseCase

@Suppress("unused")
class GetAccountTransactions(
    val accountId: String,
    val from: Long? = null,
    val to: Long? = null,
    val limit: Int,
) : UseCase<List<Transaction>> {
    override suspend fun execute(): List<Transaction> {
        imitateDelay(3500L)
        return stubTransactions.take(limit)
    }
}
