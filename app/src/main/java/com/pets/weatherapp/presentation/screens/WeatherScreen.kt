package com.pets.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import com.pets.weatherapp.data.model.ForecastState
import com.pets.weatherapp.presentation.screens.util.ErrorScreen
import com.pets.weatherapp.presentation.screens.util.LoadingIndicator
import com.pets.weatherapp.presentation.theme.WeatherAppTheme
import com.pets.weatherapp.presentation.viewmodel.ForecastViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherScreenPreview() {
    WeatherAppTheme {
        val previewViewModel = ForecastViewModel()
        WeatherScreen(previewViewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(viewModel: ForecastViewModel) {
    val state by viewModel.forecastState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.refreshWeatherData()
        },
        refreshThreshold = 30.dp
    )

    Scaffold(
        topBar = {
            ToolBar(
                cityName = selectedCity
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(pullRefreshState)
        ) {
            when (val currentState = state) {
                is ForecastState.Loading -> LoadingIndicator()
                is ForecastState.Success -> WeatherContent(
                    currentState.currentWeather,
                    currentState.dailyForecast
                )
                is ForecastState.Error -> ErrorScreen(currentState.reason)
            }
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter),
                contentColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.surface
            )
        }

    }
}

@Composable
fun WeatherContent(
    currentWeather: CurrentForecastUiModel,
    dailyForecast: List<DailyForecastUiModel>
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
                text = "Прогноз на 10 дней",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(dailyForecast) { forecast ->
            DailyForecastCard(
                forecast = forecast,
                onClick = {}
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