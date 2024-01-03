package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.tonycode.composed.comida.data.RestaurantsRepository
import dev.tonycode.composed.comida.model.Restaurant
import kotlinx.coroutines.launch


class MainViewModel(private val restaurantsRepository: RestaurantsRepository) : ViewModel() {

    private val _restaurants = mutableStateOf<List<Restaurant>>(emptyList())

    val restaurants: State<List<Restaurant>>
        get() = _restaurants

    private val _loadingRestaurants = mutableStateOf(false)

    val loadingRestaurants: State<Boolean>
        get() = _loadingRestaurants


    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch {
            _loadingRestaurants.value = true

            val result = restaurantsRepository.getRestaurants()
            _restaurants.value = result
            _loadingRestaurants.value = false
        }
    }

}
