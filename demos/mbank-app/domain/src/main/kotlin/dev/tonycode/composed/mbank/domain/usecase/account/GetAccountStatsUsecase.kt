package dev.tonycode.composed.mbank.domain.usecase.account

import dev.tonycode.composed.mbank.domain.entity.AccountStats

interface GetAccountStatsUsecase {
    suspend fun execute(accountId: String): AccountStats
}
