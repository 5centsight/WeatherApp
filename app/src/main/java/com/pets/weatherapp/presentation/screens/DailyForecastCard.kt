package com.pets.weatherapp.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.data.model.DailyForecastUiModel

@Composable
fun DailyForecastCard(
    forecast: DailyForecastUiModel
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable() { expanded = !expanded }
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures { expanded = !expanded }
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(forecast.iconId),
                contentDescription = "Weather image",
                modifier = Modifier
                    .size(36.dp)
                    .offset(x = 5.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = forecast.date,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )

                Row {
                    Text(
                        text = "${forecast.minTemperature}°",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "/",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${forecast.maxTemperature}°",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }
        }
        if (expanded) {
            DailyForecastCardExpansion(forecast)
        }
    }
}

@Composable
fun DailyForecastCardExpansion(forecast: DailyForecastUiModel) {
    Column(modifier = Modifier.padding(16.dp, top = 0.dp, bottom = 16.dp)) {
        Text(
            text = "Температура ночью: ${forecast.minTemperature}°",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Температура днём: ${forecast.maxTemperature}°",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "${forecast.cloud.replaceFirstChar { it.uppercase() }}, ${forecast.precipitation}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Ветер: ${forecast.windDirection}, ${forecast.maxWindSpeed} м/с",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Давление: ${forecast.pressure} мм.рт.ст.",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Влажность: ${forecast.humidity}%",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Восход: ${forecast.sunrise}",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Закат: ${forecast.sunset}",
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "Длина светового дня: ${forecast.lengthDay}",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}