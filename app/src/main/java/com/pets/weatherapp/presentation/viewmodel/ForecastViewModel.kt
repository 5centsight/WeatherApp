package com.pets.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.weatherapp.data.model.ForecastState
import com.pets.weatherapp.data.model.ForecastUiState
import com.pets.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val repository = ForecastRepository()
    private val _uiState: MutableStateFlow<ForecastUiState> = MutableStateFlow(ForecastUiState())
    val uiState: StateFlow<ForecastUiState> = _uiState.asStateFlow()

    init {
        loadWeatherData(_uiState.value.selectedCity)
    }

    fun loadWeatherData(cityName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isRefreshing = true
                )
            }
            try {
                val currentWeather = repository.getCurrentForecast(cityName)
                val dailyForecast = repository.getDailyForecast(cityName)
                _uiState.update {
                    it.copy(
                        forecastState = ForecastState.Success(currentWeather, dailyForecast),
                        isRefreshing = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        forecastState = ForecastState.Error(e),
                        isRefreshing = false
                    )
                }
            }
        }
    }

    fun refreshWeatherData() {
        if (_uiState.value.isRefreshing) return
        loadWeatherData(_uiState.value.selectedCity)
    }
}