package com.pets.weatherapp.domain.mapper

import com.pets.weatherapp.data.model.CityEntity
import com.pets.weatherapp.data.model.CityUiModel
import com.pets.weatherapp.data.model.CurrentForecastEntity
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastsEntity

fun CityEntity.toUiModel() = CityUiModel(
    name = name,
    title = title
)

fun CityUiModel.toEntity() = CityEntity(
    name = name,
    title = title
)

fun CurrentForecastEntity.toUiModel() = CurrentForecastUiModel(
    city = cityName,
    date = date,
    temperature = temperature,
    feelLikeTemp = feelLikeTemp,
    humidity = humidity,
    cloud = cloud,
    precipitation = precipitation,
    iconId = iconId
)

fun CurrentForecastUiModel.toEntity() = CurrentForecastEntity(
    cityName = city,
    date = date,
    temperature = temperature,
    feelLikeTemp = feelLikeTemp,
    humidity = humidity,
    cloud = cloud,
    precipitation = precipitation,
    iconId = iconId
)

fun DailyForecastsEntity.toUiModel() = DailyForecastUiModel(
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

fun DailyForecastUiModel.toEntity(cityName: String) = DailyForecastsEntity(
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