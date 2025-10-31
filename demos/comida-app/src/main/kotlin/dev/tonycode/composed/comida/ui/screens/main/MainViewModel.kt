package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tonycode.composed.comida.data.OffersRepository
import dev.tonycode.composed.comida.data.RestaurantsRepository
import dev.tonycode.composed.comida.model.Offer
import dev.tonycode.composed.comida.model.Restaurant
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    private val offersRepository: OffersRepository,
) : ViewModel() {
    // restaurants

    private val _restaurants = mutableStateOf<List<Restaurant>>(emptyList())

    val restaurants: State<List<Restaurant>>
        get() = _restaurants

    private val _loadingRestaurants = mutableStateOf(false)

    val loadingRestaurants: State<Boolean>
        get() = _loadingRestaurants

    // offers

    private val _offers = mutableStateOf<List<Offer>>(emptyList())

    val offers: State<List<Offer>>
        get() = _offers

    private val _loadingOffers = mutableStateOf(false)

    val loadingOffers: State<Boolean>
        get() = _loadingOffers

    init {
        fetchRestaurants()
        fetchOffers()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            _loadingRestaurants.value = true

            val result = restaurantsRepository.getRestaurants()
            _restaurants.value = result
            _loadingRestaurants.value = false
        }
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            _loadingOffers.value = true

            val result = offersRepository.getOffers()
            _offers.value = result
            _loadingOffers.value = false
        }
    }
}
