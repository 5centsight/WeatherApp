package com.pets.weatherapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CachedForecast(
    val cityName: String = "novosibirsk",
    val cityTitle: String = "",
    val temperature: Int = 0,
    val feelLikeTemp: Int = 0,
    val cloud: String = "",
    val precipitation: String = "",
    val iconId: Int = 0,
    val dailyMinTemp: List<Int> = emptyList(),
    val dailyMaxTemp: List<Int> = emptyList(),
    val dailyDate: List<String> = emptyList(),
    val dailyIconId: List<Int> = emptyList()
)