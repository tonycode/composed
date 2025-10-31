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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.designsystem.presentation.card.CardJointHelper
import dev.tonycode.composed.common.designsystem.ui.card.paddingForCardJoint
import dev.tonycode.composed.common.designsystem.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.R
import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


/**
 * @param transactions null means loading
 */
@Composable
fun RecentTransactionsWidget(
    transactions: List<Transaction>?,
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
                stringResource(R.string.mbank_recent_operations),
                style = MbankTheme.typography.bodyEmphasis,
                color = MbankTheme.colorScheme.onSurface,
            )

            if (!transactions.isNullOrEmpty()) {
                Text(
                    stringResource(R.string.mbank_full_history),
                    style = MbankTheme.typography.bodyEmphasis,
                    color = MbankTheme.colorScheme.onSurfaceAccent,
                    modifier = Modifier.clickable { onShowAllClicked?.invoke() }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // recent transactions
        when {
            (transactions == null) -> {  // loading
                TransactionCard(
                    transaction = null,
                    modifier = Modifier.padding(vertical = 4.dp),
                )
            }

            transactions.isEmpty() -> {
                Text(
                    stringResource(R.string.mbank_no_operations),
                    style = MbankTheme.typography.body,
                    color = MbankTheme.colorScheme.onSurfaceSecondary,
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            else -> {
                val cardJointMetadata = CardJointHelper.createJointData(
                    itemsCount = transactions.size,
                    getItemGroup = { idx -> transactions[idx].performedDay }
                )

                transactions.forEachIndexed { idx, transaction ->
                    val cardJoint = cardJointMetadata[idx]

                    TransactionCard(
                        transaction = transaction,
                        modifier = Modifier.paddingForCardJoint(cardJoint, verticalPadding = 4.dp),
                        cardJoint = cardJoint,
                        onClicked = { onTransactionClicked?.invoke(transaction) },
                    )
                }
            }
        }
    }

}


@LightDarkPreviews
@Composable
private fun RecentTransactionWidgetPreview(
    @PreviewParameter(TransactionsPreviewParameterProvider::class) transactions: List<Transaction>
) = ElementPreview {
    RecentTransactionsWidget(transactions)
}

private class TransactionsPreviewParameterProvider : PreviewParameterProvider<List<Transaction>> {
    override val values = sequenceOf(
        stubTransactions,
        emptyList()
    )
}
