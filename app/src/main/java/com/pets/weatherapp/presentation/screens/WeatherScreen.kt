package com.pets.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.data.model.ForecastState
import com.pets.weatherapp.data.model.ForecastUiModel
import com.pets.weatherapp.presentation.screens.util.ErrorScreen
import com.pets.weatherapp.presentation.screens.util.LoadingIndicator
import com.pets.weatherapp.presentation.theme.WeatherAppTheme
import com.pets.weatherapp.presentation.viewmodel.ForecastViewModel

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun WeatherScreenPreview() {
    WeatherAppTheme {
        val previewViewModel = ForecastViewModel()
        WeatherScreen(previewViewModel)
    }
}

@Composable
fun WeatherScreen(viewModel: ForecastViewModel) {
    val state by viewModel.forecastState.collectAsState()
    val mockSelectedCity = "novosibirsk"

    LaunchedEffect(Unit) {
        viewModel.loadWeather(mockSelectedCity)
    }

    Scaffold(
        topBar = {
            ToolBar(
                cityName = mockSelectedCity
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val currentState = state) {
                is ForecastState.Loading -> LoadingIndicator()
                is ForecastState.Success -> WeatherContent(currentState.result)
                is ForecastState.Error -> ErrorScreen(currentState.reason)
            }
        }
    }
}

@Composable
fun WeatherContent(
    result: ForecastUiModel
) {
    val mockDailyForecasts = mutableListOf<ForecastUiModel>()

    (1..10).forEach { _ ->
        mockDailyForecasts.add(result)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            CurrentWeatherCard(forecast = result)
        }

        item {
            Text(
                text = "Прогноз на 10 дней",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(mockDailyForecasts) { forecast ->
            DailyForecastCard(
                forecast = forecast,
                onClick = {}
            )
        }
    }
}