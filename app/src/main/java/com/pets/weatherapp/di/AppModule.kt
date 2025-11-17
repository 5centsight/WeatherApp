package com.pets.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.pets.weatherapp.data.api.ApiClient
import com.pets.weatherapp.data.api.ForecastApi
import com.pets.weatherapp.data.dao.AppDb
import com.pets.weatherapp.data.datasource.LocalForecastDataSource
import com.pets.weatherapp.data.datasource.RemoteForecastDataSource
import com.pets.weatherapp.data.repository.CacheRepositoryImpl
import com.pets.weatherapp.data.repository.ForecastRepositoryImpl
import com.pets.weatherapp.domain.repository.CacheRepository
import com.pets.weatherapp.domain.repository.ForecastRepository
import com.pets.weatherapp.presentation.screens.citysearch.presentation.SearchViewModel
import com.pets.weatherapp.presentation.screens.weather.presentation.ForecastViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = { get<Context>().preferencesDataStoreFile("cached_screen") }
        )
    }

    single<ForecastApi> { ApiClient.weatherService }

    single { AppDb.getInstance(get()) }
    single { LocalForecastDataSource(get()) }
    single { RemoteForecastDataSource(get()) }

    single<ForecastRepository> { ForecastRepositoryImpl(get(), get()) }
    single<CacheRepository> { CacheRepositoryImpl(get()) }

    viewModel { ForecastViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
}