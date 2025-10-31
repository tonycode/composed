package dev.tonycode.composed.mbank.usecase

import kotlinx.coroutines.delay

interface UseCase<T> {
    suspend fun execute(): T

    suspend fun imitateDelay(millis: Long) {
        // turn off if you don't need network delay imitation
        delay(millis)
    }
}
