package com.pets.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pets.weatherapp.data.model.CurrentForecastEntity

@Dao
interface CurrentForecastDao {

    @Query("SELECT * FROM current_forecast WHERE city == :cityName")
    suspend fun getCurrentForecast(cityName: String): CurrentForecastEntity?

    @Upsert
    suspend fun upsertCurrentForecast(forecastEntity: CurrentForecastEntity)

    @Query("DELETE FROM current_forecast WHERE city == :cityName" )
    suspend fun deleteCurrentForecast(cityName: String)
}