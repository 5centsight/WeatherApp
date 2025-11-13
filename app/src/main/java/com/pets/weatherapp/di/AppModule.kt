package com.pets.weatherapp.di

import com.pets.weatherapp.data.api.ApiClient
import com.pets.weatherapp.data.api.ForecastApi
import com.pets.weatherapp.data.dao.AppDb
import com.pets.weatherapp.data.datasource.LocalForecastDataSource
import com.pets.weatherapp.data.datasource.RemoteForecastDataSource
import com.pets.weatherapp.data.repository.ForecastRepositoryImpl
import com.pets.weatherapp.domain.repository.ForecastRepository
import com.pets.weatherapp.presentation.screens.citysearch.presentation.SearchViewModel
import com.pets.weatherapp.presentation.screens.weather.presentation.ForecastViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ForecastApi> { ApiClient.weatherService }

    single { AppDb.getInstance(get()) }
    single { LocalForecastDataSource(get()) }
    single { RemoteForecastDataSource(get()) }

    single<ForecastRepository> { ForecastRepositoryImpl(get(), get()) }

    viewModel { ForecastViewModel(repository = get()) }
    viewModel { SearchViewModel(repository = get()) }
}