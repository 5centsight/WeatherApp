package com.pets.weatherapp.presentation.screens.weather.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.R

@Composable
fun WindDirectionArrow(directionDescription: String) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_wind_direction),
        contentDescription = "Направление ветра",
        modifier = Modifier
            .rotate(getArrowRotation(directionDescription))
            .size(15.dp)
    )
}

fun getArrowRotation(iconName: String?): Float {
    return when (iconName) {
        "С" -> 180f
        "СВ" -> 215f
        "В" -> 270f
        "ЮВ" -> 225f
        "Ю" -> 0f
        "ЮЗ" -> 45f
        "З" -> 90f
        "СЗ" -> 135f
        else -> 0f
    }
}