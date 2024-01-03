package dev.tonycode.composed.comida.data

import dev.tonycode.composed.comida.data.dummy.comidaRestaurants
import dev.tonycode.composed.comida.model.Restaurant
import kotlinx.coroutines.delay


class RestaurantsRepository {

    suspend fun getRestaurants(): List<Restaurant> {
        delay(2500L)
        return comidaRestaurants
    }

}
