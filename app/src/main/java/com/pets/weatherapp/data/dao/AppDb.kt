package com.pets.weatherapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pets.weatherapp.data.model.CityRoomEntity
import com.pets.weatherapp.data.model.CurrentForecastRoomEntity
import com.pets.weatherapp.data.model.DailyForecastRoomEntity

@Database(
    entities = [
        CurrentForecastRoomEntity::class,
        DailyForecastRoomEntity::class,
        CityRoomEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDb : RoomDatabase() {
    abstract fun currentForecastDao(): CurrentForecastDao
    abstract fun dailyForecastDao(): DailyForecastsDao
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "forecast_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}