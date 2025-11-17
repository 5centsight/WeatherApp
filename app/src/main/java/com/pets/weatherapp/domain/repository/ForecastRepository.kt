package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast

interface ForecastRepository {
    suspend fun getCurrentForecast(cityName: String): CurrentForecast = error("Not Implemented")

    suspend fun getDailyForecast(cityName: String): List<DailyForecast> = error("Not implemented")

    suspend fun getCities(): List<City> = error("Not implemented")

    suspend fun getCityName(cityTitle: String): String = error("Not implemented")

    suspend fun getCityTitle(cityName: String): String = error("Not implemented")

    suspend fun getCitiesNamesList(): List<String> = error("Not implemented")

    suspend fun getCitiesTitlesList(): List<String> = error("Not implemented")

    suspend fun wasOfflineDataUsed(cityName: String): Boolean = error("Nor implemented")
}