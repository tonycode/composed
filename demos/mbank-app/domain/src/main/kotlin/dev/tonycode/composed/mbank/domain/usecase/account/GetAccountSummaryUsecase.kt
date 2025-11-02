package dev.tonycode.composed.mbank.domain.usecase.account

import dev.tonycode.composed.mbank.domain.entity.AccountSummary

interface GetAccountSummaryUsecase {
    suspend fun execute(accountId: String): AccountSummary
}
