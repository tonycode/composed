package dev.tonycode.composed.mbank.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.tonycode.composed.mbank.R

sealed class Screen(
    @param:StringRes val titleRes: Int,
    @param:DrawableRes val iconRes: Int,
) {
    data object Home : Screen(R.string.mbank_home, R.drawable.mbank_nav_home_24)

    data object Payments : Screen(R.string.mbank_payments, R.drawable.mbank_nav_payments_24)

    data object Finance : Screen(R.string.mbank_finance, R.drawable.mbank_nav_finances_24)

    data object Statistics : Screen(R.string.mbank_statistics, R.drawable.mbank_nav_statistics_24)

    data object Services : Screen(R.string.mbank_services, R.drawable.mbank_nav_services_24)
}

val screens =
    listOf(
        Screen.Home,
        Screen.Payments,
        Screen.Finance,
        Screen.Statistics,
        Screen.Services,
    )
