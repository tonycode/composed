package dev.tonycode.composed.comida.model

import java.math.BigDecimal

data class DeliveryInfo(
    val deliveryPrice: BigDecimal?,
    val approxTimeMins: Int,
) {
    val isFree: Boolean = (deliveryPrice == null || deliveryPrice.signum() == 0)

    companion object {
        /**
         * Factory method for "free delivery" instance
         */
        fun free(approxTimeMins: Int) = DeliveryInfo(deliveryPrice = null, approxTimeMins)
    }
}
