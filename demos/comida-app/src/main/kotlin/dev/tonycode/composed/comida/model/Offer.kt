package dev.tonycode.composed.comida.model

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import java.math.BigDecimal


data class Offer(

    val title: String,

    val imageUrl: String,

    val backgroundColor: Color,

    @FloatRange(1.0, 5.0)
    val rating: Double,

    val deliveryInfo: DeliveryInfo,

    val price: BigDecimal

)
