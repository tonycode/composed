package dev.tonycode.composed.mbank.ui.screens.home.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.common.ui.fmtAsAmount
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.common.ui.skeleton.Skeleton
import dev.tonycode.composed.mbank.R
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme
import java.math.BigDecimal


/**
 * @param availableFunds null means loading
 * @param spentThisMonth null means loading
 */
@Composable
fun BalanceOverview(
    availableFunds: BigDecimal?,
    spentThisMonth: BigDecimal?,
    modifier: Modifier = Modifier,
) = MbankCard(modifier) {

    Column {
        //region Available funds
        Text(
            stringResource(R.string.mbank_available_funds),
            style = MbankTheme.typography.title,
            color = MbankTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.height(4.dp))

        if (availableFunds != null) {
            Text(
                availableFunds.fmtAsAmount(),
                style = MbankTheme.typography.valueLarge,
                color = MbankTheme.colorScheme.onSurface,
            )
        } else {  // loading
            Skeleton(
                width = 64.dp,
                height = MbankTheme.typography.valueLarge.lineHeight.value.dp,
                modifier = Modifier.shimmer(),
            )
        }
        //endregion

        // Spent this month
        if (availableFunds != null) {  // display "spend this month" only if "available funds" was loaded
            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.mbank_spent_this_month),
                    style = MbankTheme.typography.body,
                    color = MbankTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.width(6.dp))

                if (spentThisMonth != null) {
                    Text(
                        spentThisMonth.fmtAsAmount(),
                        style = MbankTheme.typography.body,
                        color = MbankTheme.colorScheme.onSurfaceAccent,
                    )
                } else {  // loading
                    Skeleton(
                        width = 42.dp,
                        height = MbankTheme.typography.body.lineHeight.value.dp,
                        modifier = Modifier.shimmer(),
                    )
                }
            }
        }
    }

}


@LightDarkPreviews
@Composable
private fun BalanceOverviewPreview(
    @PreviewParameter(BalanceOverviewPreviewStateProvider::class) previewState: BalanceOverviewPreviewState
) = ElementPreview {
    BalanceOverview(
        availableFunds = previewState.availableFunds,
        spentThisMonth = previewState.spentThisMonth
    )
}

private data class BalanceOverviewPreviewState(
    val availableFunds: BigDecimal?,
    val spentThisMonth: BigDecimal?
)

private class BalanceOverviewPreviewStateProvider : PreviewParameterProvider<BalanceOverviewPreviewState> {
    override val values = sequenceOf(
        BalanceOverviewPreviewState(
            availableFunds = null,
            spentThisMonth = null
        ),
        BalanceOverviewPreviewState(
            availableFunds = BigDecimal.ZERO,
            spentThisMonth = null
        ),
        BalanceOverviewPreviewState(
            availableFunds = BigDecimal(123.45),
            spentThisMonth = null
        ),
        BalanceOverviewPreviewState(
            availableFunds = BigDecimal(1234.56),
            spentThisMonth = BigDecimal.ZERO
        ),
        BalanceOverviewPreviewState(
            availableFunds = BigDecimal(123456.78),
            spentThisMonth = BigDecimal(1234.56)
        ),
    )
}
