package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.api.ApiClient
import com.pets.weatherapp.data.model.CityUiModel
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import com.pets.weatherapp.domain.mapper.ForecastModelMapper

class ForecastRepository {
    private val forecastService = ApiClient.weatherService
    private val mapper = ForecastModelMapper()

    suspend fun getCurrentForecast(cityName: String): CurrentForecastUiModel {
        val response = forecastService.getCurrentForecast(cityName)
        return mapper.toForecastUiModel(response)
    }

    suspend fun getDailyForecast(cityName: String): List<DailyForecastUiModel> {
        val response = forecastService.getDailyForecast(cityName)
        return mapper.toForecastUiModel(response)
    }

    suspend fun getCities(): List<CityUiModel> {
        val response = forecastService.getCities()
        return mapper.toCityUiModel(response)
    }

    suspend fun getCityName(cityTitle: String): String {
        val cities = getCities()
        return cities.find { it.title == cityTitle }!!.name
    }

    suspend fun getCityTitle(name: String): String {
        val cities = getCities()
        return cities.find { it.name == name }!!.title
    }

    suspend fun getCitiesTitlesList(): List<String> {
        val cities = getCities()
        return cities.map { it.title }.sorted()
    }

    suspend fun getCitiesNamesList(): List<String> {
        val cities = getCities()
        return cities.map { it.name }
    }
}