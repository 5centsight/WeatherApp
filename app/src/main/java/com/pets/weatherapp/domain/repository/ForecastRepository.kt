package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.model.CityUiModel
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import okio.IOException

class ForecastRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {

    suspend fun getCurrentForecast(cityName: String): CurrentForecastUiModel {
        return try {
            val forecast = remoteRepository.getCurrentForecast(cityName)
            localRepository.cleanupOldCurrentForecastData(cityName)
            localRepository.saveCurrentForecastToDb(forecast)
            forecast
        } catch (e: Exception) {
            localRepository.getCurrentForecastFromDb(cityName) ?: throw e
        }
    }

    suspend fun getDailyForecast(cityName: String): List<DailyForecastUiModel> {
        return try {
            val forecasts = remoteRepository.getDailyForecast(cityName)
            localRepository.cleanupOldDailyForecastsData(cityName)
            localRepository.saveDailyForecastsToDb(forecasts)
            forecasts
        } catch (e: Exception) {
            localRepository.getDailyForecastsFromDb(cityName).sortedBy { it.date }
        }
    }

    suspend fun getCities(): List<CityUiModel> {
        return try {
            val cities = remoteRepository.getCities()
            localRepository.saveCitiesToDb(cities)
            cities
        } catch (e: IOException) {
            localRepository.getAllCitiesFromDb().ifEmpty { throw e }
        }
    }

    suspend fun getCityName(cityTitle: String): String {
        return try {
            val cities = getCities()
            cities.find { it.title == cityTitle }!!.name
        } catch (e: IOException) {
            localRepository.getCityNameFromDb(cityTitle) ?: throw e
        }
    }

    suspend fun getCityTitle(name: String): String {
        return try {
            val cities = getCities()
            cities.find { it.name == name }!!.title
        } catch (e: IOException) {
            localRepository.getCityTitleFromDb(name) ?: throw e
        }
    }

    suspend fun getCitiesTitlesList(): List<String> {
        return try {
            val cities = getCities()
            cities.map { it.title }.sorted()
        } catch (e: IOException) {
            localRepository.getAllCitiesFromDb().map { it.title }.sorted().ifEmpty { throw e }
        }
    }

    suspend fun getCitiesNamesList(): List<String> {
        return try {
            val cities = getCities()
            cities.map { it.name }
        } catch (e: IOException) {
            localRepository.getAllCitiesFromDb().map { it.name }.ifEmpty { throw e }
        }
    }

    suspend fun getLastCityName(): String? {
        return localRepository.getLastCity()
    }

    suspend fun wasOfflineDataUsed(cityName: String): Boolean {
        return try {
            remoteRepository.getCurrentForecast(cityName)
            false
        } catch (_: Exception) {
            true
        }
    }
}