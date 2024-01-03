package dev.tonycode.composed.comida.ui

import dev.tonycode.composed.comida.data.OffersRepository
import dev.tonycode.composed.comida.data.RestaurantsRepository
import dev.tonycode.composed.comida.ui.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val comidaAppModule = module {

    single<RestaurantsRepository> { RestaurantsRepository() }

    single<OffersRepository> { OffersRepository() }


    viewModel { MainViewModel(get(), get()) }

}