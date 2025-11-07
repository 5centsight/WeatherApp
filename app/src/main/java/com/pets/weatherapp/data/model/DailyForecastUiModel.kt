package com.pets.weatherapp.data.model

data class DailyForecastUiModel(
    val city: String = "",
    val date: String = "",
    val minTemperature: Int = 0,
    val maxTemperature: Int = 0,
    val humidity: Int = 0,
    val maxWindSpeed: Int = 0,
    val windDirection: String = "",
    val pressure: Int = 0,
    val sunrise: String = "",
    val sunset: String = "",
    val lengthDay: String = "",
    val cloud: String = "",
    val precipitation: String = "",
    val iconId: Int = 0
)
