package com.pets.weatherapp.data.dto

import com.pets.weatherapp.data.model.CityRoomEntity
import com.pets.weatherapp.data.model.CurrentForecastRoomEntity
import com.pets.weatherapp.data.model.DailyForecastRoomEntity
import com.pets.weatherapp.domain.entity.City
import com.pets.weatherapp.domain.entity.CurrentForecast
import com.pets.weatherapp.domain.entity.DailyForecast

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

fun DailyForecastRoomEntity.toUiModel() = DailyForecast(
    cityName = cityName,
    date = date,
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

fun DailyForecast.toEntity() = DailyForecastRoomEntity(
    cityName = cityName,
    date = date,
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