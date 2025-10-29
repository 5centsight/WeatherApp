package com.pets.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.data.model.ForecastResponse
import com.pets.weatherapp.data.model.ForecastState
import com.pets.weatherapp.presentation.viewmodel.ForecastViewModel
import com.pets.weatherapp.presentation.theme.WeatherAppTheme

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MainScreenPreview() {
    WeatherAppTheme {
        val previewViewModel = ForecastViewModel()
        MainScreen(previewViewModel)
    }
}

@Composable
fun MainScreen(viewModel: ForecastViewModel) {
    val state by viewModel.forecastState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadWeather("novosibirsk")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (val currentState = state) {
            is ForecastState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            is ForecastState.Success -> WeatherContent(currentState.response)
            is ForecastState.Error -> ErrorContent(currentState.reason)
        }
    }
}

@Composable
fun WeatherContent(response: ForecastResponse) {
    val forecast = response.forecasts.firstOrNull()
    if (forecast != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = forecast.links.city,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "${forecast.temperature}°C",
                style = MaterialTheme.typography.displayLarge
            )
            Text(
                text = "${forecast.cloud.title}, ${forecast.precipitation.title}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Влажность: ${forecast.humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        Text(
            text = "Ошибка: нет данных",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ErrorContent(message: Throwable) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ошибка: $message",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}