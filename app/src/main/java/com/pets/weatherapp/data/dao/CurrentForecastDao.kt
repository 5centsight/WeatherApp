package com.pets.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pets.weatherapp.data.model.CurrentForecastEntity

@Dao
interface CurrentForecastDao {

    @Query("SELECT * FROM current_forecast WHERE city = :cityName")
    suspend fun getCurrentForecast(cityName: String): CurrentForecastEntity?

    @Upsert
    suspend fun upsertCurrentForecast(forecastEntity: CurrentForecastEntity)

    @Query("DELETE FROM current_forecast WHERE city = :cityName")
    suspend fun deleteCurrentForecast(cityName: String)

    @Query("DELETE FROM current_forecast WHERE city = :cityName AND last_updated < :timestamp")
    suspend fun deleteOldCurrentForecast(cityName: String, timestamp: Long)

    @Query("SELECT city FROM current_forecast ORDER BY last_updated DESC LIMIT 1")
    suspend fun getLastUpdatedCity(): String?
}