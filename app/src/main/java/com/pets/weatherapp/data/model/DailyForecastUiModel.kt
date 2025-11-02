package com.pets.weatherapp.data.model

data class DailyForecastUiModel(
    val city: String = "",
    val date: String = "",
    val minTemperature: Int = 0,
    val maxTemperature: Int = 0,
    val avgTemperature: Int = 0,
    val humidity: Int = 0,
    val cloud: String = "",
    val precipitation: String = "",
    val iconId: Int = 0
)
