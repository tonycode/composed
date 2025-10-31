package dev.tonycode.composed.mbank.usecase.account

import dev.tonycode.composed.mbank.data.stubAccountStats
import dev.tonycode.composed.mbank.model.AccountStats
import dev.tonycode.composed.mbank.usecase.UseCase

class GetAccountStatsUsecase(
    @Suppress("unused") val accountId: String,
) : UseCase<AccountStats> {
    override suspend fun execute(): AccountStats {
        imitateDelay(2500L)
        return stubAccountStats
    }
}
