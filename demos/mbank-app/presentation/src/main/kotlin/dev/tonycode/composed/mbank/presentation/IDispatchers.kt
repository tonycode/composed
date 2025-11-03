package dev.tonycode.composed.mbank.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
