package com.pets.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pets.weatherapp.data.model.CityRoomEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityRoomEntity>

    @Upsert
    suspend fun insertCities(cities: List<CityRoomEntity>)

    @Query("DELETE FROM cities")
    suspend fun deleteCities()

    @Query("SELECT * FROM cities WHERE name = :name")
    suspend fun getCityByName(name: String): CityRoomEntity?

    @Query("SELECT * FROM cities WHERE title = :title")
    suspend fun getCityByTitle(title: String): CityRoomEntity?
}