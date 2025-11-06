package com.pets.weatherapp.data.model

data class ForecastUiState(
    val forecastState: ForecastState = ForecastState.Loading,
    val isRefreshing: Boolean = false,
    val selectedCity: String = "novosibirsk",
    val cityTitle: String = "",
    val cityName: String = ""
)