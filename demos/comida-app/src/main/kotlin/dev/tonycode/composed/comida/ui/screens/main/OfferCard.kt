package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.dummy.dummyOffers
import dev.tonycode.composed.comida.model.Offer
import dev.tonycode.composed.comida.ui.components.preview.Skeleton
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import dev.tonycode.composed.common.ui.preview.LoadingPreviewData
import dev.tonycode.composed.common.ui.thenIf


private val cardWidth = 256.dp
private val cardHeight = 110.dp

@Composable
fun OfferCard(
    offer: Offer = dummyOffers.first(),
    isShimming: Boolean = false,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = if (!isShimming) offer.backgroundColor else Color(0xFFEFEFEF),
        modifier = Modifier.size(width = cardWidth, height = cardHeight),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .thenIf(isShimming) { shimmer() }
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // offer-pic
            if (!isShimming) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
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
                        Image(painterResource(R.drawable.ic_star), "Rating is")

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
                                painterResource(R.drawable.ic_delivery),
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
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = ComidaPalette.BlackA33,
                            ),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                        ) {
                            Text(
                                "Buy Now",
                                style = MaterialTheme.typography.labelMedium,
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


@Preview
@Composable
private fun PreviewOfferCard(
    @PreviewParameter(PreviewOfferProvider::class) previewData: LoadingPreviewData<Offer>,
) {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            when (previewData) {
                is LoadingPreviewData.Data ->
                    OfferCard(previewData.data)

                is LoadingPreviewData.Loading ->
                    OfferCard(isShimming = true)
            }
        }
    }
}

private class PreviewOfferProvider : PreviewParameterProvider<LoadingPreviewData<Offer>> {
    override val values = sequenceOf(
        LoadingPreviewData.Data(dummyOffers.first()),
        LoadingPreviewData.Data(dummyOffers.last()),
        LoadingPreviewData.Loading(),
    )
}
