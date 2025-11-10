package com.pets.weatherapp.di

import com.pets.weatherapp.data.api.ApiClient
import com.pets.weatherapp.data.api.ForecastApi
import com.pets.weatherapp.data.dao.AppDb
import com.pets.weatherapp.domain.repository.ForecastRepository
import com.pets.weatherapp.domain.repository.LocalRepository
import com.pets.weatherapp.domain.repository.RemoteRepository
import com.pets.weatherapp.presentation.viewmodel.ForecastViewModel
import com.pets.weatherapp.presentation.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ForecastApi> { ApiClient.weatherService }

    single { AppDb.getInstance(get()) }
    single { LocalRepository(get()) }
    single { RemoteRepository(get()) }

    single { ForecastRepository(get(),get()) }

    viewModel { ForecastViewModel(repository = get()) }
    viewModel { SearchViewModel(repository = get()) }
}