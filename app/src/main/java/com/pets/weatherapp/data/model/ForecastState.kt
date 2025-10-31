package com.pets.weatherapp.data.model

sealed class ForecastState {
    object Loading: ForecastState()
    data class Success(val result: ForecastUiModel): ForecastState()
    data class Error(val reason: Throwable): ForecastState()
}