package dev.tonycode.composed.mbank.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DefaultDispatchers : IDispatchers {
    override val main: CoroutineDispatcher get() = Dispatchers.Main.immediate
    override val io: CoroutineDispatcher get() = Dispatchers.IO
    override val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
}
