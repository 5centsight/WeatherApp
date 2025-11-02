package com.pets.weatherapp.data.model

data class CurrentForecastUiModel(
    val city: String = "",
    val date: String = "",
    val updateDate: String = "",
    val temperature: Int = 0,
    val feelLikeTemp: Int = 0,
    val humidity: Int = 0,
    val cloud: String = "",
    val precipitation: String = "",
    val icon: String = ""
)