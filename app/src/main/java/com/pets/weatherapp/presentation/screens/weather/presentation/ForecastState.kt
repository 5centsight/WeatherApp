package com.pets.weatherapp.presentation.screens.weather.presentation

import com.pets.weatherapp.data.model.CachedForecast
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast

sealed class ForecastState {
    object Loading : ForecastState()

    data class Cached(
        val cachedForecast: CachedForecast
    ) : ForecastState()

    data class Success(
        val currentWeather: CurrentForecast,
        val dailyForecast: List<DailyForecast>
    ) : ForecastState()

    data class Error(val reason: Throwable) : ForecastState()
}