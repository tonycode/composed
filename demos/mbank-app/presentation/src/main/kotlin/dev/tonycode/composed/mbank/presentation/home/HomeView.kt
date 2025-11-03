package dev.tonycode.composed.mbank.presentation.home

import com.arkivanov.mvikotlin.core.view.MviView
import dev.tonycode.composed.mbank.presentation.home.store.HomeStore

interface HomeView : MviView<HomeStore.State, HomeStore.Intent>
