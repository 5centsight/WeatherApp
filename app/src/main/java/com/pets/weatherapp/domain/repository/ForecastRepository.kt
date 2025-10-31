package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.api.ApiClient
import com.pets.weatherapp.data.model.ForecastUiModel
import com.pets.weatherapp.domain.mapper.ForecastModelMapper

class ForecastRepository {
    private val forecastService = ApiClient.weatherService
    private val mapper = ForecastModelMapper()

    suspend fun getCurrentForecast(cityName: String): ForecastUiModel {
        val response = forecastService.getCurrentForecast(cityName)
        return mapper.toForecastUiModel(response).first()
    }
}