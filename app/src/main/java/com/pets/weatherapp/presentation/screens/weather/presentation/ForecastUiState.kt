package com.pets.weatherapp.presentation.screens.weather.presentation

data class ForecastUiState(
    val forecastState: ForecastState = ForecastState.Loading,
    val isRefreshing: Boolean = false,
    val cityTitle: String = "",
    val cityName: String = "novosibirsk"
)