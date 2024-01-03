package dev.tonycode.composed.comida.data

import dev.tonycode.composed.comida.data.dummy.dummyOffers
import dev.tonycode.composed.comida.model.Offer
import kotlinx.coroutines.delay


class OffersRepository {

    suspend fun getOffers(): List<Offer> {
        delay(7500L)
        return dummyOffers
    }

}
