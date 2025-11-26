package com.pets.weatherapp.presentation.screens.weather.presentation

import androidx.annotation.DrawableRes
import com.pets.weatherapp.R

@DrawableRes
fun getWeatherIconRes(iconName: String?): Int {
    return when (iconName) {
        "sunshine_none_day" -> R.drawable.sunshine_none_day
        "sunshine_none_night" -> R.drawable.sunshine_none_night
        "sunshine_light_snow_day" -> R.drawable.partly_cloudy_light_snow_day
        "sunshine_light_snow_night" -> R.drawable.partly_cloudy_light_snow_night
        "sunshine_light_rain_day" -> R.drawable.partly_cloudy_light_rain_day
        "sunshine_light_rain_night" -> R.drawable.partly_cloudy_light_rain_night
        "sunshine_light_snow_with_rain_day" -> R.drawable.partly_cloudy_light_snow_with_rain_day
        "sunshine_light_snow_with_rain_night" -> R.drawable.partly_cloudy_light_snow_with_rain_night
        "sunshine_rain_day" -> R.drawable.partly_cloudy_rain_day
        "sunshine_rain_night" -> R.drawable.partly_cloudy_rain_night
        "sunshine_snow_day" -> R.drawable.partly_cloudy_snow_day
        "sunshine_snow_night" -> R.drawable.partly_cloudy_snow_night
        "sunshine_snow_with_rain_day" -> R.drawable.partly_cloudy_snow_with_rain_day
        "sunshine_snow_with_rain_night" -> R.drawable.partly_cloudy_snow_with_rain_night
        "cloudy_none_day", "mostly_cloudy_none_day", "cloudy_rainless_day" -> R.drawable.cloudy_none_day
        "cloudy_none_night", "mostly_cloudy_none_night", "cloudy_rainless_night"  -> R.drawable.cloudy_none_night
        "cloudy_rain_day", "mostly_cloudy_rain_day" -> R.drawable.cloudy_rain_day
        "cloudy_rain_night", "mostly_cloudy_rain_night" -> R.drawable.cloudy_rain_night
        "cloudy_snow_day", "mostly_cloudy_snow_day" -> R.drawable.cloudy_snow_day
        "cloudy_snow_night", "mostly_cloudy_snow_night" -> R.drawable.cloudy_snow_night
        "cloudy_snow_with_rain_day", "mostly_cloudy_snow_with_rain_day" -> R.drawable.cloudy_snow_with_rain_day
        "cloudy_snow_with_rain_night", "mostly_cloudy_snow_with_rain_night" -> R.drawable.cloudy_snow_with_rain_night
        "cloudy_light_rain_day", "mostly_cloudy_light_rain_day" -> R.drawable.cloudy_light_rain_day
        "cloudy_light_rain_night", "mostly_cloudy_light_rain_night" -> R.drawable.cloudy_light_rain_night
        "cloudy_light_snow_day", "mostly_cloudy_light_snow_day" -> R.drawable.cloudy_light_snow_day
        "cloudy_light_snow_night", "mostly_cloudy_light_snow_night" -> R.drawable.cloudy_light_snow_night
        "cloudy_light_snow_with_rain_day", "mostly_cloudy_light_snow_with_rain_day" -> R.drawable.cloudy_light_snow_with_rain_day
        "cloudy_light_snow_with_rain_night", "mostly_cloudy_light_snow_with_rain_night" -> R.drawable.cloudy_light_snow_with_rain_night
        "partly_cloudy_none_day" -> R.drawable.partly_cloudy_none_day
        "partly_cloudy_none_night" -> R.drawable.partly_cloudy_none_night
        "partly_cloudy_rain_day" -> R.drawable.partly_cloudy_rain_day
        "partly_cloudy_rain_night" -> R.drawable.partly_cloudy_rain_night
        "partly_cloudy_snow_day" -> R.drawable.partly_cloudy_snow_day
        "partly_cloudy_snow_night" -> R.drawable.partly_cloudy_snow_night
        "partly_cloudy_snow_with_rain_day" -> R.drawable.partly_cloudy_snow_with_rain_day
        "partly_cloudy_snow_with_rain_night" -> R.drawable.partly_cloudy_snow_with_rain_night
        "partly_cloudy_light_rain_day" -> R.drawable.partly_cloudy_light_rain_day
        "partly_cloudy_light_rain_night" -> R.drawable.partly_cloudy_light_rain_night
        "partly_cloudy_light_snow_day" -> R.drawable.partly_cloudy_light_snow_day
        "partly_cloudy_light_snow_night" -> R.drawable.partly_cloudy_light_snow_night
        "partly_cloudy_light_snow_with_rain_day" -> R.drawable.partly_cloudy_light_snow_with_rain_day
        "partly_cloudy_light_snow_with_rain_night" -> R.drawable.partly_cloudy_light_snow_with_rain_night
        else -> R.drawable.ic_wind_direction
    }
}