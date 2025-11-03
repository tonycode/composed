package dev.tonycode.composed.mbank.presentation.home.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import dev.tonycode.composed.mbank.domain.entity.AccountStats
import dev.tonycode.composed.mbank.domain.entity.AccountSummary
import dev.tonycode.composed.mbank.domain.entity.Transaction
import dev.tonycode.composed.mbank.domain.entity.UserProfile

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, Nothing> {
    sealed interface Intent : JvmSerializable {
        data object Refresh : Intent
    }

    data class State(
        val userProfile: UserProfile? = null,
        val accountSummary: AccountSummary? = null,
        val accountStats: AccountStats? = null,
        val recentTransactions: List<Transaction>? = null,
    ) : JvmSerializable
}
