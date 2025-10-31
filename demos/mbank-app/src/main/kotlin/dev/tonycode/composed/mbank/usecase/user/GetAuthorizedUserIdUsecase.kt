package dev.tonycode.composed.mbank.usecase.user

import dev.tonycode.composed.mbank.data.stubUserId
import dev.tonycode.composed.mbank.usecase.UseCase

class GetAuthorizedUserIdUsecase : UseCase<String> {
    override suspend fun execute(): String {
        imitateDelay(250)
        return stubUserId
    }
}
