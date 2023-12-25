package dev.tonycode.composed.comida.model

import androidx.annotation.FloatRange
import java.math.BigDecimal


data class Restaurant(

    val id: Int,

    val title: String,

    val coverUrl: String,

    @FloatRange(1.0, 5.0)
    val rating: Double,

    val deliveryInfo: DeliveryInfo

)


data class DeliveryInfo(

    val deliveryPrice: BigDecimal?,

    val approxTimeMins: Int

) {

    val isFree: Boolean = (deliveryPrice == null || deliveryPrice.signum() == 0)

    companion object {
        fun free(approxTimeMins: Int) = DeliveryInfo(deliveryPrice = null, approxTimeMins)
    }

}
