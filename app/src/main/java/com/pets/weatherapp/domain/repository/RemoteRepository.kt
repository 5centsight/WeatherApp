package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.api.ForecastApi
import com.pets.weatherapp.data.model.CityUiModel
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import com.pets.weatherapp.domain.mapper.toCityUiModel
import com.pets.weatherapp.domain.mapper.toForecastUiModel

class RemoteRepository(private val api: ForecastApi) {

    suspend fun getCurrentForecast(cityName: String): CurrentForecastUiModel {
        return api.getCurrentForecast(cityName).toForecastUiModel()
    }

    suspend fun getDailyForecast(cityName: String): List<DailyForecastUiModel> {
        return api.getDailyForecast(cityName).toForecastUiModel()
    }

    suspend fun getCities(): List<CityUiModel> {
        return api.getCities().toCityUiModel()
    }

}