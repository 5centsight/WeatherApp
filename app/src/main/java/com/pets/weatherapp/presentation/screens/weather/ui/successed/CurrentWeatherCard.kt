package com.pets.weatherapp.presentation.screens.weather.ui.successed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.domain.entity.CurrentForecast

@Composable
fun CurrentWeatherCard(
    forecast: CurrentForecast,
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(forecast.iconId),
                contentDescription = "Weather image",
                modifier = Modifier.weight(0.4f),
                tint = MaterialTheme.colorScheme.surfaceTint
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f, fill = true),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${forecast.temperature}°C",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.surfaceTint
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Ощущается как ${forecast.feelLikeTemp}°C",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${forecast.cloud}, ${forecast.precipitation}",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}