package com.pets.weatherapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.pets.weatherapp.data.dto.SerializationConverters
import java.time.Instant

@Entity(tableName = "daily_forecasts", primaryKeys = ["city_name", "date"])
@TypeConverters(SerializationConverters::class)
data class DailyForecastRoomEntity(
    @ColumnInfo(name = "date")
    val date: Instant,
    @ColumnInfo(name = "city_name")
    val cityName: String = "",
    @ColumnInfo(name = "hours")
    val hours: List<String> = emptyList(),
    @ColumnInfo(name = "temp_per_hour")
    val tempPerHour: List<Int> = emptyList(),
    @ColumnInfo(name = "wind_per_hour")
    val windPerHour: List<Int> = emptyList(),
    @ColumnInfo(name = "direct_per_hour")
    val directPerHour: List<String> = emptyList(),
    @ColumnInfo(name = "min_temperature")
    val minTemperature: Int = 0,
    @ColumnInfo(name = "max_temperature")
    val maxTemperature: Int = 0,
    @ColumnInfo(name = "humidity")
    val humidity: Int = 0,
    @ColumnInfo(name = "pressure")
    val pressure: Int = 0,
    @ColumnInfo(name = "sunrise")
    val sunrise: String = "",
    @ColumnInfo(name = "sunset")
    val sunset: String = "",
    @ColumnInfo(name = "length_day")
    val lengthDay: String = "",
    @ColumnInfo(name = "icon_ids")
    val iconIds: List<Int> = emptyList(),
    @ColumnInfo(name = "last_updated")
    val lastUpdated: Long = System.currentTimeMillis()
)
