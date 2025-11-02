package dev.tonycode.composed.mbank.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.tonycode.composed.mbank.data.usecase.account.GetAccountStatsUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.account.GetAccountSummaryUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.account.GetAccountTransactionsUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.user.GetAuthorizedUserIdUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.user.GetUserProfileUsecaseImpl
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountStatsUsecase
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountSummaryUsecase
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountTransactionsUsecase
import dev.tonycode.composed.mbank.domain.usecase.user.GetAuthorizedUserIdUsecase
import dev.tonycode.composed.mbank.domain.usecase.user.GetUserProfileUsecase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideGetAccountStatsUsecase(): GetAccountStatsUsecase = GetAccountStatsUsecaseImpl()

    @Provides
    @Singleton
    fun provideGetAccountSummaryUsecase(): GetAccountSummaryUsecase = GetAccountSummaryUsecaseImpl()

    @Provides
    @Singleton
    fun provideGetAccountTransactionsUsecase(): GetAccountTransactionsUsecase = GetAccountTransactionsUsecaseImpl()

    @Provides
    @Singleton
    fun provideGetAuthorizedUserIdUsecase(): GetAuthorizedUserIdUsecase = GetAuthorizedUserIdUsecaseImpl()

    @Provides
    @Singleton
    fun provideGetUserProfileUsecase(): GetUserProfileUsecase = GetUserProfileUsecaseImpl()
}
