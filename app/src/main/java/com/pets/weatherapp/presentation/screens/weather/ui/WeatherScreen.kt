package com.pets.weatherapp.presentation.screens.weather.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast
import com.pets.weatherapp.presentation.screens.weather.presentation.ForecastState
import com.pets.weatherapp.presentation.screens.weather.presentation.ForecastViewModel
import com.pets.weatherapp.presentation.screens.weather.ui.cached.CachedWeatherContent
import com.pets.weatherapp.presentation.screens.weather.ui.component.SnackMessage
import com.pets.weatherapp.presentation.screens.weather.ui.component.ToolBar
import com.pets.weatherapp.presentation.screens.weather.ui.component.WeatherSnackBar
import com.pets.weatherapp.presentation.screens.weather.ui.successed.CurrentWeatherCard
import com.pets.weatherapp.presentation.screens.weather.ui.successed.DailyForecastCard

@Composable
fun WeatherScreen(
    viewModel: ForecastViewModel,
    onSearchClick: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val pullRefreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        viewModel.snackBarMessages.collect { message ->
            val text = when (message) {
                is SnackMessage.NoInternet -> "Нет интернет-соединения"
                is SnackMessage.LoadError -> "Ошибка загрузки данных"
                is SnackMessage.CustomSnackMessage -> message.text
            }
            snackBarHostState.showSnackbar(text)
        }
    }

    Scaffold(
        topBar = {
            ToolBar(
                cityName = state.cityTitle,
                onSearchClick = onSearchClick
            )
        },
        snackbarHost = { WeatherSnackBar(snackBarHostState) }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            state = pullRefreshState,
            onRefresh = {
                viewModel.refreshWeatherData()
            },
            indicator = {
                Indicator(
                    pullRefreshState,
                    state.isRefreshing,
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    color = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.surface
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val currentState = state.forecastState) {
                is ForecastState.Loading -> LoadingScreen()
                is ForecastState.Cached -> CachedWeatherContent(currentState.cachedForecast)
                is ForecastState.Success -> WeatherContent(
                    currentState.currentWeather,
                    currentState.dailyForecast
                )

                is ForecastState.Error -> {
                    LoadingScreen()
                }
            }
        }
    }
}

@Composable
fun WeatherContent(
    currentWeather: CurrentForecast,
    dailyForecast: List<DailyForecast>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        item {
            CurrentWeatherCard(forecast = currentWeather)
        }

        item {
            Text(
                text = "Прогноз на ${dailyForecast.size} дней",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(dailyForecast) { forecast ->
            DailyForecastCard(
                forecast = forecast
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Данные pogoda.ngs.ru",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }
        }
    }
}