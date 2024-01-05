package dev.tonycode.composed.comida.data

import dev.tonycode.composed.comida.data.dummy.dummyOffers
import dev.tonycode.composed.comida.model.Offer
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class OffersRepository @Inject constructor() {

    suspend fun getOffers(): List<Offer> {
        delay(7500L)
        return dummyOffers
    }

}
