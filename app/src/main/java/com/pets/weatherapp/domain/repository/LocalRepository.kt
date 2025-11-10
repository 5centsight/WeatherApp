package com.pets.weatherapp.domain.repository

import com.pets.weatherapp.data.dao.AppDb
import com.pets.weatherapp.data.model.CityUiModel
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import com.pets.weatherapp.domain.mapper.toEntity
import com.pets.weatherapp.domain.mapper.toUiModel

class LocalRepository(
    dao: AppDb
) {
    private val currentForecastDao = dao.currentForecastDao()
    private val dailyForecastsDao = dao.dailyForecastDao()
    private val cityDao = dao.cityDao()

    suspend fun getCurrentForecastFromDb(cityName: String): CurrentForecastUiModel? {
        return currentForecastDao.getCurrentForecast(cityName)?.toUiModel()
    }

    suspend fun saveCurrentForecastToDb(forecast: CurrentForecastUiModel) {
        currentForecastDao.upsertCurrentForecast(forecast.toEntity())
    }

    suspend fun getDailyForecastsFromDb(cityName: String): List<DailyForecastUiModel> {
        return dailyForecastsDao.getDailyForecasts(cityName).map { it.toUiModel() }
    }

    suspend fun saveDailyForecastsToDb(forecasts: List<DailyForecastUiModel>, cityName: String) {
        val entities = forecasts.map { it.toEntity(cityName) }
        dailyForecastsDao.upsertDailyForecasts(entities)
    }

    suspend fun getAllCitiesFromDb(): List<CityUiModel> {
        return cityDao.getAllCities().map { it.toUiModel() }
    }

    suspend fun saveCitiesToDb(cities: List<CityUiModel>) {
        val entities = cities.map { it.toEntity() }
        cityDao.insertCities(entities)
    }

    suspend fun getCityNameFromDb(cityTitle: String): String? {
        return cityDao.getCityByTitle(cityTitle)?.name
    }

    suspend fun getCityTitleFromDb(cityName: String): String? {
        return cityDao.getCityByName(cityName)?.title
    }

    suspend fun cleanupDailyForecastsData() {
        val cities = getAllCitiesFromDb()
        cities.forEach {
            dailyForecastsDao.deleteDailyForecasts(it.name)
        }
    }
}