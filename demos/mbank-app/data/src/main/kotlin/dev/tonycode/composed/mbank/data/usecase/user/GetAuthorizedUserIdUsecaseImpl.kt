package dev.tonycode.composed.mbank.data.usecase.user

import dev.tonycode.composed.mbank.data.STUB_USER_ID
import dev.tonycode.composed.mbank.domain.usecase.user.GetAuthorizedUserIdUsecase
import kotlinx.coroutines.delay

class GetAuthorizedUserIdUsecaseImpl : GetAuthorizedUserIdUsecase {
    override suspend fun execute(): String {
        delay(250)
        return STUB_USER_ID
    }
}
