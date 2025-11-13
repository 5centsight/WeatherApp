package com.pets.weatherapp.data.datasource

import com.pets.weatherapp.data.dao.AppDb
import com.pets.weatherapp.data.dto.toEntity
import com.pets.weatherapp.data.dto.toUiModel
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast

class LocalForecastDataSource(
    dao: AppDb
) {
    private val currentForecastDao = dao.currentForecastDao()
    private val dailyForecastsDao = dao.dailyForecastDao()
    private val cityDao = dao.cityDao()

    suspend fun getCurrentForecastFromDb(cityName: String): CurrentForecast? {
        return currentForecastDao.getCurrentForecast(cityName)?.toUiModel()
    }

    suspend fun saveCurrentForecastToDb(forecast: CurrentForecast) {
        currentForecastDao.upsertCurrentForecast(forecast.toEntity())
    }

    suspend fun getDailyForecastsFromDb(cityName: String): List<DailyForecast> {
        return dailyForecastsDao.getDailyForecasts(cityName).map { it.toUiModel() }
    }

    suspend fun saveDailyForecastsToDb(forecasts: List<DailyForecast>) {
        val entities = forecasts.map { it.toEntity() }
        dailyForecastsDao.upsertDailyForecasts(entities)
    }

    suspend fun getAllCitiesFromDb(): List<City> {
        return cityDao.getAllCities().map { it.toUiModel() }
    }

    suspend fun saveCitiesToDb(cities: List<City>) {
        val entities = cities.map { it.toEntity() }
        cityDao.insertCities(entities)
    }

    suspend fun getCityNameFromDb(cityTitle: String): String? {
        return cityDao.getCityByTitle(cityTitle)?.name
    }

    suspend fun getCityTitleFromDb(cityName: String): String? {
        return cityDao.getCityByName(cityName)?.title
    }

    suspend fun getLastCity(): String? {
        return currentForecastDao.getLastUpdatedCity()
    }

    suspend fun cleanupOldCurrentForecastData(cityName: String) {
        val timestamp = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        currentForecastDao.deleteOldCurrentForecast(cityName, timestamp)
    }

    suspend fun cleanupOldDailyForecastsData(cityName: String) {
        val timestamp = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        dailyForecastsDao.deleteOldDailyForecasts(cityName, timestamp)

    }
}