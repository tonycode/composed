package dev.tonycode.composed.comida.data.dummy

import androidx.compose.ui.graphics.Color
import dev.tonycode.composed.comida.model.DeliveryInfo
import dev.tonycode.composed.comida.model.Offer
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import java.math.BigDecimal

val dummyOffers =
    listOf(
        Offer(
            title = "Burger Prince",
            imageUrl = "https://tonycode.dev/demos/assets/pic-hamburger-01.png",
            backgroundColor = ComidaPalette.Primary,
            rating = 4.5,
            deliveryInfo = DeliveryInfo.free(25),
            price = BigDecimal(16),
        ),
        Offer(
            title = "McPizza's",
            imageUrl = "https://tonycode.dev/demos/assets/pic-pizza-01.png",
            backgroundColor = Color(0xFF6CD39C),
            rating = 4.4,
            deliveryInfo = DeliveryInfo.free(25),
            price = BigDecimal(25),
        ),
    )
