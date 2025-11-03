package com.pets.weatherapp.data.model

data class ForecastUiState(
    val forecastState: ForecastState = ForecastState.Loading,
    val isRefreshing: Boolean = false,
    val isSearching: Boolean = false,
    val selectedCity: String = "adler",
    val searchQuery: String = ""
)