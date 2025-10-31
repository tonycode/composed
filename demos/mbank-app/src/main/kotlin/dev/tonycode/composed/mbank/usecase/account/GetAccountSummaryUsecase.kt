package dev.tonycode.composed.mbank.usecase.account

import dev.tonycode.composed.mbank.data.stubAccountSummary
import dev.tonycode.composed.mbank.model.AccountSummary
import dev.tonycode.composed.mbank.usecase.UseCase

class GetAccountSummaryUsecase(
    @Suppress("unused") val accountId: String,
) : UseCase<AccountSummary> {
    override suspend fun execute(): AccountSummary {
        imitateDelay(750L)
        return stubAccountSummary
    }
}
