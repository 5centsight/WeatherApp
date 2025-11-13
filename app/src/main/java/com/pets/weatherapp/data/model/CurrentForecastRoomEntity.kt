package com.pets.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pets.weatherapp.data.dto.SerializationConverters

@Entity(tableName = "current_forecast")
@TypeConverters(SerializationConverters::class)
data class CurrentForecastRoomEntity(
    @PrimaryKey
    @ColumnInfo(name = "city")
    val cityName: String = "",
    @ColumnInfo(name = "date")
    val date: String = "",
    @ColumnInfo(name = "temperature")
    val temperature: Int = 0,
    @ColumnInfo(name = "feel_like_temp")
    val feelLikeTemp: Int = 0,
    @ColumnInfo(name = "humidity")
    val humidity: Int = 0,
    @ColumnInfo(name = "cloud")
    val cloud: String = "",
    @ColumnInfo(name = "precipitation")
    val precipitation: String = "",
    @ColumnInfo(name = "icon_id")
    val iconId: Int = 0,
    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long = System.currentTimeMillis()
)