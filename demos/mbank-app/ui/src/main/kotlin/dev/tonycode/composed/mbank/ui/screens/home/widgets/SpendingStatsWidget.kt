package dev.tonycode.composed.mbank.ui.screens.home.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme

// TODO:
// - [ ] parameterize data
// - [ ] match design
@Composable
fun SpendingStatsWidget(modifier: Modifier = Modifier) =
    MbankCard(modifier) {
        Text(
            "Mon Tue Wed Thu Fri",
            style = MbankTheme.typography.label,
            color = MbankTheme.colorScheme.onSurface,
        )
    }
