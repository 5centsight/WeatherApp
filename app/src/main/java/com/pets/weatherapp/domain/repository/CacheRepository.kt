package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.model.CachedForecast
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    val cachedForecast: Flow<CachedForecast?>

    suspend fun cacheForecastData(data: CachedForecast)
}