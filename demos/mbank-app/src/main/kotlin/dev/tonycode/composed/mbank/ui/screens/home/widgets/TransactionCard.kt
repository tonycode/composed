package dev.tonycode.composed.mbank.ui.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.ui.fmtAsAmount
import dev.tonycode.composed.common.ui.fmtAsEpochMillis
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun TransactionCard(
    transaction: Transaction,
    modifier: Modifier = Modifier,
    onClicked: (() -> Unit)? = null,
) = MbankCard(
    modifier,
    backgroundColor = MbankTheme.colorScheme.card,
    padding = 12.dp,
    onClicked = onClicked,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // transaction-icon
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (transaction.isCleared) MbankTheme.colorScheme.primary
                    else MbankTheme.colorScheme.accent
                )
        )

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            // merchant
            Text(
                transaction.merchant,
                style = MbankTheme.typography.bodyEmphasis,
                color = MbankTheme.colorScheme.onCard,
            )

            // performed-at
            val fmtPerformedAt = transaction.performedAt.fmtAsEpochMillis(
                datetimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, HH:mm", Locale.ENGLISH)
            )
            Text(
                fmtPerformedAt,
                style = MbankTheme.typography.body,
                color = MbankTheme.colorScheme.onCardSecondary,
            )
        }

        Spacer(Modifier.width(12.dp))

        // amount with sign & currency
        Text(
            transaction.amount.fmtAsAmount(),
            style = MbankTheme.typography.bodyEmphasis,
            color = MbankTheme.colorScheme.onCard,
        )
    }

}


@LightDarkPreviews
@Composable
private fun PreviewTransactionCard(
    @PreviewParameter(TransactionPreviewProvider::class) transaction: Transaction,
) = ElementPreview(backgroundColor = MbankTheme.colorScheme.surface) {
    TransactionCard(transaction = transaction)
}

private class TransactionPreviewProvider : PreviewParameterProvider<Transaction> {
    override val values = stubTransactions.asSequence()
}
