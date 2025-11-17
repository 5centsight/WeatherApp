package com.pets.weatherapp.presentation.screens.weather.ui.cached

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.data.model.CachedForecast

@Composable
fun CachedWeatherContent(
    cachedData: CachedForecast
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

            item {
                CachedCurrentWeather(cachedData)
            }

            item {
                Text(
                    text = "Прогноз на 10 дней",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            items(10) { index ->
                Card(
                    elevation = CardDefaults.cardElevation(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    MockDailyForecastCard(
                        iconId = cachedData.dailyIconId.getOrNull(index) ?: 0,
                        date = cachedData.dailyDate[index],
                        minTemperature = cachedData.dailyMinTemp.getOrNull(index) ?: 0,
                        maxTemperature = cachedData.dailyMaxTemp.getOrNull(index) ?: 0

                    )
                }
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
}