package com.pets.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pets.weatherapp.data.model.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityEntity>

    @Upsert
    suspend fun insertCities(cities: List<CityEntity>)

    @Query("DELETE FROM cities")
    suspend fun deleteCities()

    @Query("SELECT * FROM cities WHERE name = :name")
    suspend fun getCityByName(name: String): CityEntity?

    @Query("SELECT * FROM cities WHERE title = :title")
    suspend fun getCityByTitle(title: String): CityEntity?
}