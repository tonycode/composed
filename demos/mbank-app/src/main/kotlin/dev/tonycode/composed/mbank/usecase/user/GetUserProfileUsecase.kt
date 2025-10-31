package dev.tonycode.composed.mbank.usecase.user

import dev.tonycode.composed.mbank.data.stubUserProfile
import dev.tonycode.composed.mbank.model.UserProfile
import dev.tonycode.composed.mbank.usecase.UseCase

class GetUserProfileUsecase(
    userId: String,
) : UseCase<UserProfile> {
    override suspend fun execute(): UserProfile {
        imitateDelay(1500)
        return stubUserProfile
    }
}
