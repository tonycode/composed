package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.data.dummy.comidaCategories
import dev.tonycode.composed.comida.data.dummy.comidaOffers
import dev.tonycode.composed.comida.data.dummy.dummyRestaurants
import dev.tonycode.composed.comida.model.Restaurant
import dev.tonycode.composed.comida.ui.comidaAppModule
import dev.tonycode.composed.comida.ui.components.Section
import dev.tonycode.composed.comida.ui.screenHorizontalPadding
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication


@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
) {

    val restaurantsLoading by remember { mainViewModel.loadingRestaurants }
    val restaurants by remember { mainViewModel.restaurants }

    Column(modifier = Modifier.padding(top = 12.dp, bottom = 80.dp)) {
        ComidaTopAppbar(
            modifier = Modifier.padding(horizontal = screenHorizontalPadding),
            onNavMenuClicked = { },
        )

        Spacer(Modifier.height(20.dp))

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

        Section(
            title = "Special Offers",
            modifier = Modifier.padding(horizontal = screenHorizontalPadding),
            onViewAllClicked = { },
        )

        OffersBlock()

        Spacer(Modifier.height(12.dp))

        Section(
            title = "Restaurants",
            modifier = Modifier.padding(horizontal = screenHorizontalPadding),
            onViewAllClicked = { },
        )

        RestaurantsBlock(restaurantsLoading, restaurants)
    }

}

@Composable
private fun CategoriesBlock() {
    var selectedCategoryTitle: String by remember { mutableStateOf(comidaCategories.first().title) }

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
private fun OffersBlock() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = screenHorizontalPadding, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(comidaOffers) {
            OfferCard(it)
        }
    }
}

@Composable
private fun RestaurantsBlock(
    restaurantsLoading: Boolean,
    restaurants: List<Restaurant>,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = screenHorizontalPadding, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (restaurantsLoading) {
            items(listOf(dummyRestaurants.first())) {
                RestaurantCard(isShimming = true)
            }
        } else {
            items(restaurants) {
                RestaurantCard(restaurant = it)
            }
        }
    }
}


@Preview
@Composable
private fun PreviewMainScreen() {
    KoinApplication(application = {
        modules(comidaAppModule)
    }) {
        ComidaAppTheme {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxWidth(),
            ) {
                MainScreen()
            }
        }
    }
}
