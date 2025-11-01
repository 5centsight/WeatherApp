package com.pets.weatherapp.data.model

sealed class ForecastState {
    object Loading : ForecastState()
    data class Success(
        val currentWeather: CurrentForecastUiModel,
        val dailyForecast: List<DailyForecastUiModel>
    ) : ForecastState()

    data class Error(val reason: Throwable) : ForecastState()
}