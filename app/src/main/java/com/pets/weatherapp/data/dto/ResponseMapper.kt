package com.pets.weatherapp.data.dto

import com.pets.weatherapp.data.model.CitiesResponse
import com.pets.weatherapp.data.model.CurrentForecastResponse
import com.pets.weatherapp.data.model.DailyForecastResponse
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast
import com.pets.weatherapp.presentation.screens.weather.presentation.getWeatherIconRes
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
    "EE, dd MMMM",
    Locale("ru")
)

fun CurrentForecastResponse.toCurrentForecast(): CurrentForecast {
    val forecast = this.forecasts.first()
    return CurrentForecast(
        city = forecast.links.city,
        date = formatter.format(forecast.date.atZone(ZoneId.systemDefault())),
        updateDate = formatter.format(forecast.updateDate.atZone(ZoneId.systemDefault())),
        temperature = forecast.temperature,
        feelLikeTemp = forecast.feelLikeTemp,
        humidity = forecast.humidity,
        cloud = forecast.cloud.title,
        precipitation = forecast.precipitation.title,
        iconId = getWeatherIconRes(forecast.iconName)
    )
}

fun DailyForecastResponse.toDailyForecastList(): List<DailyForecast> {
    val hourlyForecastsList = this.forecasts
    return hourlyForecastsList.map { it ->
        DailyForecast(
            cityName = it.links.city,
            date = formatter.format(it.date.atZone(ZoneId.systemDefault()))
                .replaceFirstChar { it.titlecase() },
            minTemperature = it.hours[0].temperature.min,
            maxTemperature = it.hours[2].temperature.max,
            hours = (0..3).map { id ->
                "${it.hours[id].hour}:00"
            },
            tempPerHour = (0..3).map { id ->
                it.hours[id].temperature.avg
            },
            windPerHour = (0..3).map { id ->
                it.hours[id].wind.speed.max
            },
            directPerHour = (0..3).map { id ->
                it.hours[id].wind.direction.titleLetter
            },
            humidity = it.hours[2].humidity.avg,
            pressure = it.hours[2].pressure.avg,
            sunrise = it.astronomy.sunrise,
            sunset = it.astronomy.sunset,
            lengthDay = it.astronomy.lengthDay,
            iconIds = (0..3).map { id ->
                getWeatherIconRes(it.hours[id].icon)
            },
        )
    }
}

fun CitiesResponse.toCityList(): List<City> {
    val citiesList = this.cities
    return citiesList.map { it ->
        City(
            name = it.name,
            title = it.title
        )
    }
}