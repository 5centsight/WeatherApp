package com.pets.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pets.weatherapp.data.model.DailyForecastsEntity

@Dao
interface DailyForecastsDao {
    @Query("SELECT * FROM daily_forecasts WHERE city_name = :cityName")
    suspend fun getDailyForecasts(cityName: String): List<DailyForecastsEntity>

    @Upsert
    suspend fun upsertDailyForecasts(forecast: List<DailyForecastsEntity>)

    @Query("DELETE FROM daily_forecasts WHERE city_name = :cityName")
    suspend fun deleteDailyForecasts(cityName: String)

    @Query("DELETE FROM daily_forecasts WHERE city_name = :cityName AND last_updated < :timestamp")
    suspend fun deleteOldDailyForecasts(cityName: String, timestamp: Long)
}