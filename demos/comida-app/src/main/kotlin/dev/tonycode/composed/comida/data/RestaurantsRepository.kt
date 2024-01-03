package dev.tonycode.composed.comida.data

import dev.tonycode.composed.comida.data.dummy.dummyRestaurants
import dev.tonycode.composed.comida.model.Restaurant
import kotlinx.coroutines.delay


class RestaurantsRepository {

    suspend fun getRestaurants(): List<Restaurant> {
        delay(7500L)
        return dummyRestaurants
    }

}
