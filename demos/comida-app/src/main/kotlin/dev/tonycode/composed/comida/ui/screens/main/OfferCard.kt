package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.dummy.dummyOffers
import dev.tonycode.composed.comida.model.Offer
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import dev.tonycode.composed.common.designsystem.ui.modifier.thenIf
import dev.tonycode.composed.common.designsystem.ui.preview.LoadingPreviewState
import dev.tonycode.composed.common.designsystem.ui.skeleton.LocalSkeletonTheme
import dev.tonycode.composed.common.designsystem.ui.skeleton.Skeleton
import dev.tonycode.composed.common.designsystem.ui.skeleton.defaultSkeletonTheme

private val cardWidth = 256.dp
private val cardHeight = 110.dp

@Composable
fun OfferCard(
    offer: Offer = dummyOffers.first(),
    isShimming: Boolean = false,
    shimmer: Shimmer? = null,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (!isShimming) offer.backgroundColor else ComidaPalette.KinglyCloud.copy(alpha = 0.25f),
        modifier = Modifier.size(width = cardWidth, height = cardHeight),
    ) {
        CompositionLocalProvider(
            LocalSkeletonTheme provides defaultSkeletonTheme.copy(color = ComidaPalette.White),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .thenIf(isShimming) { shimmer(customShimmer = shimmer) }
                        .thenIf(!isShimming) { clickable { } },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // offer-pic
                if (!isShimming) {
                    AsyncImage(
                        model =
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(offer.imageUrl)
                                .crossfade(true)
                                .build(),
                        placeholder = ColorPainter(Color.Transparent),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(cardHeight),
                    )
                } else {
                    Skeleton(
                        width = cardHeight,
                        height = cardHeight,
                        shape = CircleShape,
                        modifier = Modifier.padding(8.dp),
                    )
                }

                Spacer(Modifier.width(6.dp))

                // offer-info
                Column(modifier = Modifier.weight(1f)) {
                    // rating
                    if (!isShimming) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painterResource(R.drawable.comida_ic_star), "Rating is")

                            Spacer(Modifier.width(4.dp))

                            Text(
                                offer.rating.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = ComidaPalette.WhiteA75,
                            )
                        }
                    } else {
                        Skeleton(width = 32.dp, height = 14.dp)
                    }

                    Spacer(Modifier.height(4.dp))

                    // title
                    if (!isShimming) {
                        Text(
                            offer.title,
                            style = MaterialTheme.typography.headlineMedium,
                            color = ComidaPalette.White,
                        )
                    } else {
                        Skeleton(width = 132.dp, height = 22.dp)
                    }

                    Spacer(Modifier.height(4.dp))

                    // delivery-info
                    if (offer.deliveryInfo.isFree) {
                        if (!isShimming) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(R.drawable.comida_ic_delivery),
                                    contentDescription = "Delivery is",
                                    colorFilter = ColorFilter.tint(ComidaPalette.WhiteA75),
                                )

                                Spacer(Modifier.width(6.dp))

                                Text(
                                    "Free delivery",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = ComidaPalette.WhiteA75,
                                )
                            }
                        } else {
                            Skeleton(width = 88.dp, height = 14.dp)
                        }

                        Spacer(Modifier.height(6.dp))
                    }

                    // price & buy-button
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (!isShimming) {
                            OutlinedButton(
                                onClick = { },
                                modifier = Modifier.height(28.dp),
                                border = null,
                                colors =
                                    ButtonDefaults.elevatedButtonColors(
                                        containerColor = ComidaPalette.BlackA33,
                                    ),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                            ) {
                                Text(
                                    "Buy Now",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = ComidaPalette.White,
                                )
                            }
                        } else {
                            Skeleton(width = 88.dp, height = 28.dp, shape = RoundedCornerShape(50))
                        }

                        Spacer(Modifier.width(8.dp))

                        if (!isShimming) {
                            Text(
                                "$",
                                style = MaterialTheme.typography.bodyMedium,
                                color = ComidaPalette.WhiteA75,
                            )
                            Spacer(Modifier.width(1.dp))
                            Text(
                                offer.price.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = ComidaPalette.White,
                            )
                        } else {
                            Skeleton(width = 24.dp, height = 18.dp)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun OfferCardPreview(
    @PreviewParameter(OfferCardPreviewStateProvider::class) previewState: LoadingPreviewState<Offer>,
) = ElementPreview {
    when (previewState) {
        is LoadingPreviewState.Data ->
            OfferCard(offer = previewState.data)

        is LoadingPreviewState.Loading ->
            OfferCard(isShimming = true)
    }
}

private class OfferCardPreviewStateProvider : PreviewParameterProvider<LoadingPreviewState<Offer>> {
    override val values =
        sequenceOf(
            LoadingPreviewState.Data(dummyOffers.first()),
            LoadingPreviewState.Data(dummyOffers.last()),
            LoadingPreviewState.Loading(),
        )
}
