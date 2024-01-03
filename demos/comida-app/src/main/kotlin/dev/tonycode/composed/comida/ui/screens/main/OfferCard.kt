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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.dummy.comidaOffers
import dev.tonycode.composed.comida.model.Offer
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun OfferCard(
    offer: Offer,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = offer.backgroundColor,
        modifier = Modifier.size(width = 256.dp, height = 110.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // offer-pic
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(offer.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(Color.Transparent),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(110.dp),
            )

            Spacer(Modifier.width(6.dp))

            // offer-info
            Column(modifier = Modifier.weight(1f)) {
                // rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.ic_star), "Rating is")

                    Spacer(Modifier.width(4.dp))

                    Text(
                        offer.rating.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = ComidaPalette.WhiteA75,
                    )
                }

                // title
                Text(
                    offer.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = ComidaPalette.White,
                )

                // delivery-info
                if (offer.deliveryInfo.isFree) {
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

                    Spacer(Modifier.height(6.dp))
                }

                // price & buy-button
                Row(verticalAlignment = Alignment.CenterVertically) {
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

                    Spacer(Modifier.width(8.dp))

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
                }
            }
        }
    }

}


@Preview
@Composable
private fun PreviewOfferCard(
    @PreviewParameter(PreviewOfferProvider::class) offer: Offer,
) {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OfferCard(offer)
        }
    }
}

private class PreviewOfferProvider : PreviewParameterProvider<Offer> {
    override val values = sequenceOf(
        comidaOffers.first(),
        comidaOffers.last(),
    )
}
