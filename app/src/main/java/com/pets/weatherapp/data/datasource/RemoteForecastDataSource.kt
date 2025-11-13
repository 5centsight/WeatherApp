package com.pets.weatherapp.data.datasource

import com.pets.weatherapp.data.api.ForecastApi
import com.pets.weatherapp.data.dto.toCityUiModel
import com.pets.weatherapp.data.dto.toForecastUiModel
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast

class RemoteForecastDataSource(private val api: ForecastApi) {

    suspend fun getCurrentForecast(cityName: String): CurrentForecast {
        return api.getCurrentForecast(cityName).toForecastUiModel()
    }

    suspend fun getDailyForecast(cityName: String): List<DailyForecast> {
        return api.getDailyForecast(cityName).toForecastUiModel()
    }

    suspend fun getCities(): List<City> {
        return api.getCities().toCityUiModel()
    }
}