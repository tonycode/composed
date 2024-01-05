package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.dummy.comidaCategories
import dev.tonycode.composed.comida.data.dummy.dummyOffers
import dev.tonycode.composed.comida.data.dummy.dummyRestaurants
import dev.tonycode.composed.comida.model.Offer
import dev.tonycode.composed.comida.model.Restaurant
import dev.tonycode.composed.comida.ui.components.Section
import dev.tonycode.composed.comida.ui.components.preview.Skeleton
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.screenHorizontalPadding
import dev.tonycode.composed.comida.ui.util.AnimationBox


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel(),
) {

    val restaurantsLoading by remember { mainViewModel.loadingRestaurants }
    val restaurants by remember { mainViewModel.restaurants }

    val offersLoading by remember { mainViewModel.loadingOffers }
    val offers by remember { mainViewModel.offers }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, bottom = 24.dp)
        ,
    ) {
        Text(
            stringResource(R.string.comida_good_afternoon),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = screenHorizontalPadding),
        )

        Spacer(Modifier.height(8.dp))

        SearchField(
            modifier = Modifier.padding(horizontal = screenHorizontalPadding),
            onChanged = { },
        )

        Spacer(Modifier.height(12.dp))

        CategoriesBlock()

        Spacer(Modifier.height(12.dp))

        OffersBlock(offersLoading, offers)

        Spacer(Modifier.height(12.dp))

        RestaurantsBlock(restaurantsLoading, restaurants)
    }

}

@Composable
private fun CategoriesBlock() {
    var selectedCategoryTitle: String by rememberSaveable { mutableStateOf(comidaCategories.first().title) }

    LazyRow(
        contentPadding = PaddingValues(horizontal = screenHorizontalPadding, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(comidaCategories) {
            CategoryChip(
                category = it,
                isSelected = (it.title == selectedCategoryTitle),
                onClicked = {
                    selectedCategoryTitle = it.title
                }
            )
        }
    }
}

@Composable
private fun ColumnScope.OffersBlock(
    offersLoading: Boolean,
    offers: List<Offer>,
) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    val shimmerLightContent = rememberShimmer(
        shimmerBounds = ShimmerBounds.Window,
        theme = defaultShimmerTheme.copy(
            shaderColors = listOf(
                Color.Unspecified.copy(alpha = 1.0f),
                Color.Unspecified.copy(alpha = 0.0f),
                Color.Unspecified.copy(alpha = 1.0f),
            ),
        ),
    )

    // section title
    AnimatedContent(
        targetState = offersLoading,
        label = "offers block",
        transitionSpec = { fadeIn().togetherWith(fadeOut()) },
    ) { targetLoading ->
        if (!targetLoading) {
            Section(
                title = "Special Offers",
                modifier = Modifier.padding(horizontal = screenHorizontalPadding),
                onViewAllClicked = { },
            )
        } else {
            Skeleton(
                width = 160.dp,
                height = 29.dp,
                modifier = Modifier
                    .padding(start = screenHorizontalPadding)
                    .shimmer(customShimmer = shimmer),
            )
        }
    }

    // section content
    LazyRow(
        contentPadding = PaddingValues(horizontal = screenHorizontalPadding, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = !offersLoading,
    ) {
        if (offersLoading) {
            items(dummyOffers.take(2)) {
                AnimationBox {
                    OfferCard(isShimming = true, shimmer = shimmerLightContent)
                }
            }
        } else {
            items(offers) {
                AnimationBox {
                    OfferCard(offer = it)
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.RestaurantsBlock(
    restaurantsLoading: Boolean,
    restaurants: List<Restaurant>,
) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)

    // section title
    AnimatedContent(
        targetState = restaurantsLoading,
        label = "restaurants block",
        transitionSpec = { fadeIn().togetherWith(fadeOut()) },
    ) { targetLoading ->
        if (!targetLoading) {
            Section(
                title = "Restaurants",
                modifier = Modifier.padding(horizontal = screenHorizontalPadding),
                onViewAllClicked = { },
            )
        } else {
            Skeleton(
                width = 160.dp,
                height = 29.dp,
                modifier = Modifier
                    .padding(start = screenHorizontalPadding)
                    .shimmer(customShimmer = shimmer),
            )
        }
    }

    // section content
    LazyRow(
        contentPadding = PaddingValues(horizontal = screenHorizontalPadding, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = !restaurantsLoading,
    ) {
        if (restaurantsLoading) {
            items(dummyRestaurants.take(2)) {
                AnimationBox {
                    RestaurantCard(isShimming = true, shimmer = shimmer)
                }
            }
        } else {
            items(restaurants) {
                AnimationBox {
                    RestaurantCard(restaurant = it)
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewMainScreen() = ElementPreview(usePadding = false) {
    MainScreen()
}
