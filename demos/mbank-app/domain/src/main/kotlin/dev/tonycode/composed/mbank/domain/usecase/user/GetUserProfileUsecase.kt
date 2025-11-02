package dev.tonycode.composed.mbank.domain.usecase.user

import dev.tonycode.composed.mbank.domain.entity.UserProfile

interface GetUserProfileUsecase {
    suspend fun execute(userId: String): UserProfile
}
