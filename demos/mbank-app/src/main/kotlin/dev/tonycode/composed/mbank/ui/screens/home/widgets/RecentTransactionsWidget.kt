package dev.tonycode.composed.mbank.ui.screens.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.mbank.R
import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


//todo:
// - [ ] join cards within one day
@Composable
fun RecentTransactionsWidget(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier,
    onTransactionClicked: ((Transaction) -> Unit)? = null,
    onShowAllClicked: (() -> Unit)? = null,
) = MbankCard(modifier) {

    Column {
        // header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                stringResource(R.string.recent_operations),
                style = MbankTheme.typography.bodyEmphasis,
                color = MbankTheme.colorScheme.onSurface,
            )

            if (transactions.isNotEmpty()) {
                Text(
                    stringResource(R.string.full_history),
                    style = MbankTheme.typography.bodyEmphasis,
                    color = MbankTheme.colorScheme.onSurfaceAccent,
                    modifier = Modifier.clickable { onShowAllClicked?.invoke() }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // operations
        if (transactions.isNotEmpty()) {
            transactions.forEach {
                TransactionCard(
                    transaction = it,
                    modifier = Modifier.padding(vertical = 4.dp),
                    onClicked = { onTransactionClicked?.invoke(it) },
                )
            }
        } else {
            Text(
                stringResource(R.string.no_operations),
                style = MbankTheme.typography.body,
                color = MbankTheme.colorScheme.onSurfaceSecondary,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    }

}


@Preview
@Composable
private fun PreviewRecentTransactionsWidget(
    @PreviewParameter(TransactionsPreviewProvider::class) transactions: List<Transaction>
) = ElementPreview {
    RecentTransactionsWidget(transactions)
}

private class TransactionsPreviewProvider : PreviewParameterProvider<List<Transaction>> {
    override val values = sequenceOf(
        stubTransactions,
        emptyList()
    )
}
