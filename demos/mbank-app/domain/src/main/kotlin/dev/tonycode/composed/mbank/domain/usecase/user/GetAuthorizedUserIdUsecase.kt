package dev.tonycode.composed.mbank.domain.usecase.user

interface GetAuthorizedUserIdUsecase {
    suspend fun execute(): String
}
