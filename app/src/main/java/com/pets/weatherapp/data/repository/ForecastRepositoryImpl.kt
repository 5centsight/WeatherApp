package com.pets.weatherapp.data.repository

import com.pets.weatherapp.data.datasource.LocalForecastDataSource
import com.pets.weatherapp.data.datasource.RemoteForecastDataSource
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast
import com.pets.weatherapp.domain.repository.ForecastRepository
import okio.IOException

class ForecastRepositoryImpl(
    private val remoteForecastDataSource: RemoteForecastDataSource,
    private val localForecastDataSource: LocalForecastDataSource
) : ForecastRepository {

    override suspend fun getCurrentForecast(cityName: String): CurrentForecast {
        return try {
            val forecast = remoteForecastDataSource.getCurrentForecast(cityName)
            localForecastDataSource.cleanupOldCurrentForecastData(cityName)
            localForecastDataSource.saveCurrentForecastToDb(forecast)
            forecast
        } catch (e: Exception) {
            localForecastDataSource.getCurrentForecastFromDb(cityName) ?: throw e
        }
    }

    override suspend fun getDailyForecast(cityName: String): List<DailyForecast> {
        return try {
            val forecasts = remoteForecastDataSource.getDailyForecast(cityName)
            localForecastDataSource.cleanupOldDailyForecastsData(cityName)
            localForecastDataSource.saveDailyForecastsToDb(forecasts)
            forecasts
        } catch (_: Exception) {
            localForecastDataSource.getDailyForecastsFromDb(cityName).sortedBy { it.date }
        }
    }

    override suspend fun getCities(): List<City> {
        return try {
            val cities = remoteForecastDataSource.getCities()
            localForecastDataSource.saveCitiesToDb(cities)
            cities
        } catch (e: IOException) {
            localForecastDataSource.getAllCitiesFromDb().ifEmpty { throw e }
        }
    }

    override suspend fun getCityName(cityTitle: String): String {
        return try {
            val cities = getCities()
            cities.find { it.title == cityTitle }!!.name
        } catch (e: IOException) {
            localForecastDataSource.getCityNameFromDb(cityTitle) ?: throw e
        }
    }

    override suspend fun getCityTitle(cityName: String): String {
        return try {
            val cities = getCities()
            cities.find { it.name == cityName }!!.title
        } catch (e: IOException) {
            localForecastDataSource.getCityTitleFromDb(cityName) ?: throw e
        }
    }

    override suspend fun getCitiesTitlesList(): List<String> {
        return try {
            val cities = getCities()
            cities.map { it.title }.sorted()
        } catch (e: IOException) {
            localForecastDataSource.getAllCitiesFromDb().map { it.title }.sorted()
                .ifEmpty { throw e }
        }
    }

    override suspend fun getCitiesNamesList(): List<String> {
        return try {
            val cities = getCities()
            cities.map { it.name }
        } catch (e: IOException) {
            localForecastDataSource.getAllCitiesFromDb().map { it.name }.ifEmpty { throw e }
        }
    }

    override suspend fun getLastCityName(): String? {
        return localForecastDataSource.getLastCity()
    }

    override suspend fun wasOfflineDataUsed(cityName: String): Boolean {
        return try {
            remoteForecastDataSource.getCurrentForecast(cityName)
            false
        } catch (_: Exception) {
            true
        }
    }
}