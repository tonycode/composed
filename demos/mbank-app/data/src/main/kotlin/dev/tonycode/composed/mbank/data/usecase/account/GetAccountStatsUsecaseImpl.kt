package dev.tonycode.composed.mbank.data.usecase.account

import dev.tonycode.composed.mbank.data.stubAccountStats
import dev.tonycode.composed.mbank.domain.entity.AccountStats
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountStatsUsecase
import kotlinx.coroutines.delay

class GetAccountStatsUsecaseImpl : GetAccountStatsUsecase {
    override suspend fun execute(accountId: String): AccountStats {
        delay(2500L)
        return stubAccountStats
    }
}
