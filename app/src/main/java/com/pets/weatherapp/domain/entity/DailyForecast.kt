package com.pets.weatherapp.domain.entity

data class DailyForecast(
    val cityName: String = "",
    val date: String = "",
    val hours: List<String> = emptyList(),
    val tempPerHour: List<Int> = emptyList(),
    val windPerHour: List<Int> = emptyList(),
    val directPerHour: List<String> = emptyList(),
    val minTemperature: Int = 0,
    val maxTemperature: Int = 0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val sunrise: String = "",
    val sunset: String = "",
    val lengthDay: String = "",
    val iconIds: List<Int> = emptyList()
)
