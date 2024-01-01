package dev.tonycode.composed.comida.model

import androidx.annotation.FloatRange


data class Restaurant(

    val id: Int,

    val title: String,

    val coverUrl: String,

    @FloatRange(1.0, 5.0)
    val rating: Double,

    val deliveryInfo: DeliveryInfo

)
