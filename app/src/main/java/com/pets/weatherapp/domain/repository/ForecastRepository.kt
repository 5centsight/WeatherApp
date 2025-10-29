package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.api.ApiClient
import com.pets.weatherapp.data.model.ForecastResponse

class ForecastRepository {
    private val forecastService = ApiClient.weatherService

    suspend fun getForecast(cityName: String): ForecastResponse {
        return forecastService.getCurrentForecast(cityName)
    }
}