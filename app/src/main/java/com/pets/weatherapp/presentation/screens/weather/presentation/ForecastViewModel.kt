package com.pets.weatherapp.presentation.screens.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.weatherapp.data.model.CachedForecast
import com.pets.weatherapp.domain.repository.CacheRepository
import com.pets.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.io.IOException

@KoinViewModel
class ForecastViewModel(
    private val repository: ForecastRepository,
    private val cacheRepository: CacheRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<ForecastUiState> = MutableStateFlow(ForecastUiState())
    val uiState: StateFlow<ForecastUiState> = _uiState.asStateFlow()

    private val _snackBarMessages = MutableSharedFlow<SnackMessage>()
    val snackBarMessages = _snackBarMessages.asSharedFlow()

    init {
        viewModelScope.launch {
            val cachedData = cacheRepository.cachedForecast.first()
            if (cachedData != null) {
                _uiState.update {
                    it.copy(
                        cityName = cachedData.cityName,
                        cityTitle = cachedData.cityTitle,
                        forecastState = ForecastState.Cached(cachedData)
                    )
                }
            }
            loadWeatherData(_uiState.value.cityName)
        }
    }

    fun loadWeatherData(cityName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isRefreshing = true)
            }
            try {
                val currentWeather = repository.getCurrentForecast(cityName)
                val dailyForecast = repository.getDailyForecast(cityName)
                val cityTitle = repository.getCityTitle(cityName)
                _uiState.update {
                    it.copy(
                        forecastState = ForecastState.Success(currentWeather, dailyForecast),
                        cityTitle = cityTitle ?: _uiState.value.cityTitle,
                        cityName = cityName,
                        isRefreshing = false
                    )
                }
                val wasOffline = repository.wasOfflineDataUsed(cityName)
                if (wasOffline) {
                    showMessage(SnackMessage.NoInternet)
                } else {
                    val cachedData = CachedForecast(
                        cityName = currentWeather.city,
                        cityTitle = cityTitle ?: _uiState.value.cityTitle,
                        temperature = currentWeather.temperature,
                        feelLikeTemp = currentWeather.feelLikeTemp,
                        cloud = currentWeather.cloud,
                        precipitation = currentWeather.precipitation,
                        iconId = currentWeather.iconId,
                        dailyMinTemp = dailyForecast.map { it.minTemperature },
                        dailyMaxTemp = dailyForecast.map { it.maxTemperature },
                        dailyDate = dailyForecast.map { it.date },
                        dailyIconId = dailyForecast.map { it.iconIds[2] }
                    )
                    cacheRepository.cacheForecastData(cachedData)
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        forecastState = ForecastState.Error(e),
                        cityTitle = repository.getCityTitle(cityName) ?: _uiState.value.cityTitle,
                        cityName = cityName,
                        isRefreshing = false
                    )
                }
                val errorSnackMessage = when (e) {
                    is IOException -> SnackMessage.NoInternet
                    else -> SnackMessage.LoadError
                }
                showMessage(errorSnackMessage)
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
                it.copy(cityName = cityName)
            }
        }
    }

    private fun showMessage(snackMessage: SnackMessage) {
        viewModelScope.launch {
            _snackBarMessages.emit(snackMessage)
        }
    }
}