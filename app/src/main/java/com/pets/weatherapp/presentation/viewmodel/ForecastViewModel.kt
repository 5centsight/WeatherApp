package com.pets.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.weatherapp.data.model.ForecastState
import com.pets.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val repository = ForecastRepository()
    private val _forecastState: MutableStateFlow<ForecastState> =
        MutableStateFlow(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> = _forecastState.asStateFlow()
    private val _selectedCity = MutableStateFlow("novosibirsk")
    val selectedCity: StateFlow<String> = _selectedCity.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        loadWeatherData(_selectedCity.value)
    }

    fun loadWeatherData(cityName: String) {
        viewModelScope.launch {
            _forecastState.value = ForecastState.Loading
            try {
                val currentWeather = repository.getCurrentForecast(cityName)
                val dailyForecast = repository.getDailyForecast(cityName)
                _forecastState.value = ForecastState.Success(
                    currentWeather = currentWeather,
                    dailyForecast = dailyForecast
                )
            } catch (e: Exception) {
                _forecastState.value = ForecastState.Error(e)
            }
        }
    }

    fun refreshWeatherData() {
        if (_isRefreshing.value) return
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                loadWeatherData(_selectedCity.value)
            } catch (e: Exception) {
                _forecastState.value = ForecastState.Error(e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}