package dev.tonycode.composed.mbank.data.usecase.user

import dev.tonycode.composed.mbank.data.stubUserProfile
import dev.tonycode.composed.mbank.domain.entity.UserProfile
import dev.tonycode.composed.mbank.domain.usecase.user.GetUserProfileUsecase
import kotlinx.coroutines.delay

class GetUserProfileUsecaseImpl : GetUserProfileUsecase {
    override suspend fun execute(userId: String): UserProfile {
        delay(1500)
        return stubUserProfile
    }
}
