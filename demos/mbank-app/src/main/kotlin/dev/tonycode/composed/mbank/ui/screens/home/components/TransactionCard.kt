package dev.tonycode.composed.mbank.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.theme.MbankTheme
import java.math.RoundingMode


@Composable
fun TransactionCard(
    transaction: Transaction,
) {
    MbankCard {
        Row {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MbankTheme.colorScheme.primary)
            )

            Column {
                Text(
                    transaction.merchant,
                    style = MbankTheme.typography.bodyEmphasis,
                )

                Text(
                    "yesterday, 21:37",
                    style = MbankTheme.typography.body,
                    color = MbankTheme.colorScheme.onCardSecondary,
                )
            }

            Text(
                transaction.amount.setScale(2, RoundingMode.CEILING).toString(),
                style = MbankTheme.typography.bodyEmphasis,
            )
        }
    }
}


@Preview
@Composable
private fun PreviewTransactionCard(
    @PreviewParameter(TransactionPreviewProvider::class) transaction: Transaction,
) = ElementPreview {
    TransactionCard(transaction)
}

private class TransactionPreviewProvider : PreviewParameterProvider<Transaction> {
    override val values = sequenceOf(
        *stubTransactions
    )
}
