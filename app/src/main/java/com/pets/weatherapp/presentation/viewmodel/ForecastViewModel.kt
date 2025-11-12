package com.pets.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.weatherapp.data.model.ForecastState
import com.pets.weatherapp.data.model.ForecastUiState
import com.pets.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.io.IOException

@KoinViewModel
class ForecastViewModel(
    private val repository: ForecastRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<ForecastUiState> = MutableStateFlow(ForecastUiState())
    val uiState: StateFlow<ForecastUiState> = _uiState.asStateFlow()

    private val _snackBarMessages = MutableSharedFlow<String>()
    val snackBarMessages = _snackBarMessages.asSharedFlow()

    init {
        viewModelScope.launch {
            val cityName = repository.getLastCityName() ?: _uiState.value.selectedCity
            _uiState.update {
                it.copy(
                    selectedCity = cityName
                )
            }
            loadWeatherData(cityName)
        }
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
                val cityTitle = repository.getCityTitle(cityName)
                _uiState.update {
                    it.copy(
                        forecastState = ForecastState.Success(currentWeather, dailyForecast),
                        cityTitle = cityTitle,
                        cityName = cityName,
                        isRefreshing = false
                    )
                }
                val wasOffline = repository.wasOfflineDataUsed(cityName)
                if (wasOffline) {
                    showMessage("Нет подключения к интернету")
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        forecastState = ForecastState.Error(e),
                        cityTitle = repository.getCityTitle(cityName),
                        cityName = cityName,
                        isRefreshing = false
                    )
                }
                val errorMessage = when (e) {
                    is IOException -> "Нет подключения к интернету"
                    else -> "Ошибка загрузки данных"
                }
                showMessage(errorMessage)
            }
        }
    }

    fun refreshWeatherData() {
        if (_uiState.value.isRefreshing) return
        loadWeatherData(_uiState.value.cityName)
    }

    fun onCitySelected(cityTitle: String) {
        viewModelScope.launch {
            val cityName = repository.getCityName(cityTitle)
            loadWeatherData(cityName)
            _uiState.update {
                it.copy(
                    selectedCity = cityName
                )
            }
        }
    }

    private fun showMessage(message: String) {
        viewModelScope.launch {
            _snackBarMessages.emit(message)
        }
    }
}