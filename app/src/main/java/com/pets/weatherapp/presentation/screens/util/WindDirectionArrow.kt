package com.pets.weatherapp.presentation.screens.util

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
        "С" -> 0f
        "СВ" -> 45f
        "В" -> 90f
        "ЮВ" -> 135f
        "Ю" -> 180f
        "ЮЗ" -> 225f
        "З" -> 270f
        "СЗ" -> 315f
        else -> 0f
    }
}