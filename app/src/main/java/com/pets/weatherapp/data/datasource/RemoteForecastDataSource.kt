package com.pets.weatherapp.data.datasource

import com.pets.weatherapp.data.api.ForecastApi
import com.pets.weatherapp.data.dto.toCityList
import com.pets.weatherapp.data.dto.toCurrentForecast
import com.pets.weatherapp.data.model.DailyForecastResponse
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast

class RemoteForecastDataSource(private val api: ForecastApi) {

    suspend fun getCurrentForecast(cityName: String): CurrentForecast {
        return api.getCurrentForecast(cityName).toCurrentForecast()
    }

    suspend fun getDailyForecast(cityName: String): DailyForecastResponse {
        return api.getDailyForecast(cityName)
    }

    suspend fun getCities(): List<City> {
        return api.getCities().toCityList()
    }
}