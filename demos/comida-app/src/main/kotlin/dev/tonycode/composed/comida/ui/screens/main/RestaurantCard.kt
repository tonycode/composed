package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.comidaRestaurants
import dev.tonycode.composed.comida.model.Restaurant
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun RestaurantCard(
    restaurant: Restaurant,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = ComidaPalette.White,
        modifier = Modifier
            .size(width = 264.dp, height = 176.dp)
            .shadow(
                elevation = 1.dp,
                shape = MaterialTheme.shapes.medium,
                spotColor = Color(0xFFABABAB),
            ),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(restaurant.coverUrl)
                    .crossfade(true)
                    .build(),
                placeholder = ColorPainter(ComidaPalette.ParisPaving),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(112.dp)
                    .fillMaxWidth(),
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    restaurant.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                    ),
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(R.drawable.ic_star), "Rating is")

                    Spacer(Modifier.width(4.dp))

                    Text(
                        restaurant.rating.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                if (restaurant.deliveryInfo.isFree) {
                    Image(painterResource(R.drawable.ic_delivery), "Delivery is")

                    Spacer(Modifier.width(6.dp))

                    Text(
                        "Free delivery",
                        style = MaterialTheme.typography.bodySmall,
                        color = ComidaPalette.ParisPaving,
                    )

                    Spacer(Modifier.width(16.dp))
                }

                Image(painterResource(R.drawable.ic_clock), "Approximate delivery time is")

                Spacer(Modifier.width(6.dp))

                Text(
                    "${restaurant.deliveryInfo.approxTimeMins} mins",
                    style = MaterialTheme.typography.bodySmall,
                    color = ComidaPalette.ParisPaving,
                )
            }
        }
    }

}

@Preview
@Composable
private fun PreviewRestaurantCard() {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            RestaurantCard(comidaRestaurants.first())
        }
    }
}
