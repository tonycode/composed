package dev.tonycode.composed.mbank.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.tonycode.composed.mbank.R


sealed class Screen(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
) {

    data object Home : Screen(R.string.home, R.drawable.nav_home_24)

    data object Payments : Screen(R.string.payments, R.drawable.nav_payments_24)

    data object Finance : Screen(R.string.finance, R.drawable.nav_finances_24)

    data object Statistics : Screen(R.string.statistics, R.drawable.nav_statistics_24)

    data object Services : Screen(R.string.services, R.drawable.nav_services_24)

}

val screens = listOf(
    Screen.Home,
    Screen.Payments,
    Screen.Finance,
    Screen.Statistics,
    Screen.Services
)
