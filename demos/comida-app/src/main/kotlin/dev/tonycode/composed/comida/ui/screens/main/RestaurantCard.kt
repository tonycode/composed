package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.dummy.dummyRestaurants
import dev.tonycode.composed.comida.model.Restaurant
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import dev.tonycode.composed.common.designsystem.ui.modifier.thenIf
import dev.tonycode.composed.common.designsystem.ui.preview.LoadingPreviewState
import dev.tonycode.composed.common.designsystem.ui.skeleton.Skeleton


private val cardWidth = 264.dp
private val cardHeight = 176.dp
private val imageHeight = 112.dp

@Composable
fun RestaurantCard(
    restaurant: Restaurant = dummyRestaurants.first(),
    isShimming: Boolean = false,
    shimmer: Shimmer? = null,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = ComidaPalette.White,
        modifier = Modifier
            .size(width = cardWidth, height = cardHeight)
            .shadow(
                elevation = 2.dp,
                shape = MaterialTheme.shapes.medium,
                spotColor = Color(0xFFABABAB),
            ),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .thenIf(isShimming) { shimmer(customShimmer = shimmer) }
                .thenIf(!isShimming) { clickable { } }
                .padding(bottom = 12.dp),
        ) {
            // image
            if (!isShimming) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(restaurant.coverUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = ColorPainter(ComidaPalette.ParisPaving),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight),
                )
            } else {
                Skeleton(
                    height = imageHeight,
                    shape = RectangleShape,
                )
            }

            // title + rating
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                // title
                if (!isShimming) {
                    Text(
                        restaurant.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                } else {
                    Skeleton(width = 120.dp, height = 18.dp)
                }

                // rating
                if (!isShimming) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.comida_ic_star), "Rating is")

                        Spacer(Modifier.width(4.dp))

                        Text(
                            restaurant.rating.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                } else {
                    Skeleton(width = 32.dp, height = 14.dp)
                }
            }

            // delivery info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                // delivery-price
                if (restaurant.deliveryInfo.isFree) {
                    if (!isShimming) {
                        Image(painterResource(R.drawable.comida_ic_delivery), "Delivery is")

                        Spacer(Modifier.width(6.dp))

                        Text(
                            "Free delivery",
                            style = MaterialTheme.typography.bodySmall,
                            color = ComidaPalette.ParisPaving,
                        )
                    } else {
                        Skeleton(width = 64.dp, height = 14.dp)
                    }

                    Spacer(Modifier.width(16.dp))
                }

                // delivery-time
                if (!isShimming) {
                    Image(painterResource(R.drawable.comida_ic_clock), "Approximate delivery time is")

                    Spacer(Modifier.width(6.dp))

                    Text(
                        "${restaurant.deliveryInfo.approxTimeMins} mins",
                        style = MaterialTheme.typography.bodySmall,
                        color = ComidaPalette.ParisPaving,
                    )
                } else {
                    Skeleton(width = 64.dp, height = 14.dp)
                }
            }
        }
    }

}


@Preview
@Composable
private fun RestaurantCardPreview(
    @PreviewParameter(RestaurantCardPreviewStateProvider::class) previewState: LoadingPreviewState<Restaurant>,
) = ElementPreview {
    when (previewState) {
        is LoadingPreviewState.Data ->
            RestaurantCard(restaurant = previewState.data)

        is LoadingPreviewState.Loading ->
            RestaurantCard(isShimming = true)
    }
}

private class RestaurantCardPreviewStateProvider : PreviewParameterProvider<LoadingPreviewState<Restaurant>> {
    override val values = sequenceOf(
        LoadingPreviewState.Data(dummyRestaurants.first()),
        LoadingPreviewState.Loading()
    )
}
