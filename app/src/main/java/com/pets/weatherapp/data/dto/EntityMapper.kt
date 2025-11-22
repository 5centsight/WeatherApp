package com.pets.weatherapp.data.dto

import com.pets.weatherapp.data.dto.InstantSerializer.Companion.WEEK_FORMATTER
import com.pets.weatherapp.data.model.CityRoomEntity
import com.pets.weatherapp.data.model.CurrentForecastRoomEntity
import com.pets.weatherapp.data.model.DailyForecastResponse
import com.pets.weatherapp.data.model.DailyForecastRoomEntity
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast
import com.pets.weatherapp.presentation.screens.weather.presentation.getWeatherIconRes
import java.time.ZoneId

fun CityRoomEntity.toUiModel() = City(
    name = name,
    title = title
)

fun City.toEntity() = CityRoomEntity(
    name = name,
    title = title
)

fun CurrentForecastRoomEntity.toUiModel() = CurrentForecast(
    city = cityName,
    date = date,
    temperature = temperature,
    feelLikeTemp = feelLikeTemp,
    humidity = humidity,
    cloud = cloud,
    precipitation = precipitation,
    iconId = iconId
)

fun CurrentForecast.toEntity() = CurrentForecastRoomEntity(
    cityName = city,
    date = date,
    temperature = temperature,
    feelLikeTemp = feelLikeTemp,
    humidity = humidity,
    cloud = cloud,
    precipitation = precipitation,
    iconId = iconId
)

fun DailyForecastRoomEntity.toDailyForecast() = DailyForecast(
    cityName = cityName,
    date = WEEK_FORMATTER.format(date.atZone(ZoneId.systemDefault()))
        .replaceFirstChar { it.titlecase() },
    hours = hours,
    tempPerHour = tempPerHour,
    windPerHour = windPerHour,
    directPerHour = directPerHour,
    minTemperature = minTemperature,
    maxTemperature = maxTemperature,
    humidity = humidity,
    pressure = pressure,
    sunrise = sunrise,
    sunset = sunset,
    lengthDay = lengthDay,
    iconIds = iconIds
)

fun DailyForecastResponse.toEntities(): List<DailyForecastRoomEntity> {
    val hourlyForecastsList = this.forecasts
    return hourlyForecastsList.map { it ->
        DailyForecastRoomEntity(
            date = it.date,
            cityName = it.links.city,
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