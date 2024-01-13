package dev.tonycode.composed.mbank.ui.screens.home.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.ui.fmtAsAmount
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme
import java.math.BigDecimal


//todo:
// - [ ] match design
@Composable
fun AvailableFundsWidget(
    availableFunds: BigDecimal,
    modifier: Modifier = Modifier,
) = MbankCard(modifier) {

    Column {
        Text(
            "Available funds",
            style = MbankTheme.typography.body,
            color = MbankTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.width(8.dp))

        Text(
            availableFunds.fmtAsAmount(withSignForPositive = false),
            style = MbankTheme.typography.bodyEmphasis,
            color = MbankTheme.colorScheme.onSurface,
        )
    }

}
