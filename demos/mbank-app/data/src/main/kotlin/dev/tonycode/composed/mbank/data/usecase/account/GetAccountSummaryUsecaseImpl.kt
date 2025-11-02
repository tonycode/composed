package dev.tonycode.composed.mbank.data.usecase.account

import dev.tonycode.composed.mbank.data.stubAccountSummary
import dev.tonycode.composed.mbank.domain.entity.AccountSummary
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountSummaryUsecase
import kotlinx.coroutines.delay

class GetAccountSummaryUsecaseImpl : GetAccountSummaryUsecase {
    override suspend fun execute(accountId: String): AccountSummary {
        delay(750L)
        return stubAccountSummary
    }
}
