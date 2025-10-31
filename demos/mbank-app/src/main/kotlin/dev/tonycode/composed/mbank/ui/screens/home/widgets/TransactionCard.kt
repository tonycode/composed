package dev.tonycode.composed.mbank.ui.screens.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.common.currency.presentation.fmtAsAmount
import dev.tonycode.composed.common.datetime.presentation.fmtAsEpochMillis
import dev.tonycode.composed.common.designsystem.presentation.card.CardJoint
import dev.tonycode.composed.common.designsystem.ui.modifier.thenIf
import dev.tonycode.composed.common.designsystem.ui.preview.LightDarkPreviews
import dev.tonycode.composed.common.designsystem.ui.skeleton.Skeleton
import dev.tonycode.composed.common.designsystem.ui.skeleton.defaultSkeletonTheme
import dev.tonycode.composed.mbank.data.stubTransactions
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * @param transaction null means loading
 */
@Composable
fun TransactionCard(
    transaction: Transaction?,
    modifier: Modifier = Modifier,
    cardJoint: CardJoint = CardJoint.Single,
    onClicked: (() -> Unit)? = null,
) = MbankCard(
    modifier,
    cardJoint = cardJoint,
    backgroundColor = MbankTheme.colorScheme.card,
    padding = 12.dp,
    onClicked = onClicked,
) {
    val viewBoundsShimmer = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // transaction-icon
        Box(
            modifier =
                Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .thenIf(transaction == null) { shimmer(customShimmer = viewBoundsShimmer) }
                    .background(
                        when {
                            (transaction == null) -> defaultSkeletonTheme.color
                            transaction.isCleared -> MbankTheme.colorScheme.primary
                            else -> MbankTheme.colorScheme.accent
                        },
                    ),
        )

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            if (transaction != null) {
                // merchant
                Text(
                    transaction.merchant,
                    style = MbankTheme.typography.bodyEmphasis,
                    color = MbankTheme.colorScheme.onCard,
                )

                // performed-at
                val fmtPerformedAt =
                    transaction.performedAt.fmtAsEpochMillis(
                        dateFormatter = DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH),
                        timeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH),
                    )
                Text(
                    fmtPerformedAt,
                    style = MbankTheme.typography.body,
                    color = MbankTheme.colorScheme.onCardSecondary,
                )
            } else { // loading
                Skeleton(
                    width = 72.dp,
                    height = MbankTheme.typography.bodyEmphasis.lineHeight.value.dp,
                    modifier = Modifier.shimmer(customShimmer = viewBoundsShimmer),
                )

                Spacer(Modifier.height(8.dp))

                Skeleton(
                    width = 108.dp,
                    height = MbankTheme.typography.body.lineHeight.value.dp,
                    modifier = Modifier.shimmer(customShimmer = viewBoundsShimmer),
                )
            }
        }

        Spacer(Modifier.width(12.dp))

        // amount with sign & currency
        if (transaction != null) {
            Text(
                transaction.amount.fmtAsAmount(withSignForPositive = true),
                style = MbankTheme.typography.bodyEmphasis,
                color = MbankTheme.colorScheme.onCard,
            )
        } else { // loading
            Skeleton(
                width = 64.dp,
                height = MbankTheme.typography.bodyEmphasis.lineHeight.value.dp,
                modifier = Modifier.shimmer(customShimmer = viewBoundsShimmer),
            )
        }
    }
}

@LightDarkPreviews
@Composable
private fun TransactionCardPreview(
    @PreviewParameter(TransactionCardPreviewStateProvider::class) previewState: TransactionCardPreviewState,
) = ElementPreview(backgroundColor = MbankTheme.colorScheme.surface) {
    TransactionCard(
        transaction = previewState.transaction,
        cardJoint = previewState.cardJoint,
    )
}

private class TransactionCardPreviewState(
    val transaction: Transaction?,
    val cardJoint: CardJoint,
)

private class TransactionCardPreviewStateProvider : PreviewParameterProvider<TransactionCardPreviewState> {
    override val values =
        sequenceOf(
            TransactionCardPreviewState(transaction = null, CardJoint.Single),
            TransactionCardPreviewState(transaction = stubTransactions.first(), CardJoint.Single),
            TransactionCardPreviewState(transaction = stubTransactions[1], CardJoint.Top),
            TransactionCardPreviewState(transaction = stubTransactions[2], CardJoint.Middle),
            TransactionCardPreviewState(transaction = stubTransactions.last(), CardJoint.Bottom),
        )
}
