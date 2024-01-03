package dev.tonycode.composed.comida.data.dummy

import dev.tonycode.composed.comida.model.DeliveryInfo
import dev.tonycode.composed.comida.model.Restaurant


val comidaRestaurants = listOf(
    Restaurant(
        id = 0,
        title = "Seafood maki sushi",
        coverUrl = stubCover(488),
        rating = 4.5,
        deliveryInfo = DeliveryInfo.free(45),
    ),
    Restaurant(
        id = 1,
        title = "Tori Midori",
        coverUrl = stubCover(493),
        rating = 4.4,
        deliveryInfo = DeliveryInfo.free(21),
    ),
)

private fun stubCover(imageId: Int) = "https://picsum.photos/id/$imageId/1024"
