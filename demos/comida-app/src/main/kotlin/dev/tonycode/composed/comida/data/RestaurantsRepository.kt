package dev.tonycode.composed.comida.data

import dev.tonycode.composed.comida.data.dummy.dummyRestaurants
import dev.tonycode.composed.comida.model.Restaurant
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantsRepository
    @Inject
    constructor() {
        suspend fun getRestaurants(): List<Restaurant> {
            delay(5000L)
            return dummyRestaurants
        }
    }
