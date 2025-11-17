package com.pets.weatherapp.presentation.screens.weather.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import com.pets.weatherapp.domain.entity.DailyForecast
import com.pets.weatherapp.presentation.screens.weather.ui.component.WindDirectionArrow

@Composable
fun DailyForecastCard(
    forecast: DailyForecast
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures { expanded = !expanded }
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(forecast.iconIds[2]),
                contentDescription = "Weather image",
                modifier = Modifier
                    .size(36.dp)
                    .offset(x = 5.dp),
                tint = MaterialTheme.colorScheme.surfaceTint
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
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.surfaceTint
                    )
                    Text(
                        text = "/",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.surfaceTint
                    )
                    Text(
                        text = "${forecast.maxTemperature}°",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.surfaceTint
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
fun DailyForecastCardExpansion(forecast: DailyForecast) {
    LazyRow(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(forecast.iconIds.size) { id ->
            Card {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "${forecast.tempPerHour[id]}°",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
                Icon(
                    imageVector = ImageVector.vectorResource(forecast.iconIds[id]),
                    contentDescription = "Weather image",
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
                Card(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Row {
                        WindDirectionArrow(forecast.directPerHour[id])
                        Text(
                            text = "${forecast.windPerHour[id]} м/с",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = forecast.hours[id],
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
    Spacer(
        modifier = Modifier.size(10.dp)
    )
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "Давление: ${forecast.pressure} мм.рт.ст.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Влажность: ${forecast.humidity}%",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "Восход: ${forecast.sunrise}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Закат: ${forecast.sunset}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "Длина светового дня: ${forecast.lengthDay}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
