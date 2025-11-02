package dev.tonycode.composed.mbank.domain.usecase.account

import dev.tonycode.composed.mbank.domain.entity.Transaction

interface GetAccountTransactionsUsecase {
    suspend fun execute(
        accountId: String,
        from: Long? = null,
        to: Long? = null,
        limit: Int = 100,
    ): List<Transaction>
}
