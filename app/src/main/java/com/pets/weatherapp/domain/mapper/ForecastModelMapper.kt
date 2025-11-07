package com.pets.weatherapp.domain.mapper

import com.pets.weatherapp.data.model.CitiesResponse
import com.pets.weatherapp.data.model.CityUiModel
import com.pets.weatherapp.data.model.CurrentForecastResponse
import com.pets.weatherapp.data.model.DailyForecastsResponse
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
import com.pets.weatherapp.presentation.screens.util.getWeatherIconRes
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class ForecastModelMapper {

    private companion object {
        val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(
            "dd MMMM", Locale("ru")
        )
    }

    fun toForecastUiModel(data: CurrentForecastResponse): CurrentForecastUiModel = with(data) {
        val forecast = data.forecasts.first()
            CurrentForecastUiModel(
                city = forecast.links.city,
                date = FORMATTER.format(forecast.date.atZone(ZoneId.systemDefault())),
                updateDate = FORMATTER.format(forecast.updateDate.atZone(ZoneId.systemDefault())),
                temperature = forecast.temperature,
                feelLikeTemp = forecast.feelLikeTemp,
                humidity = forecast.humidity,
                cloud = forecast.cloud.title,
                precipitation = forecast.precipitation.title,
                iconId = getWeatherIconRes(forecast.iconName)
            )
    }

    fun toForecastUiModel(data: DailyForecastsResponse): List<DailyForecastUiModel> = with(data) {
        val hourlyForecastsList = data.forecasts
        hourlyForecastsList.map { it ->
            DailyForecastUiModel(
                date = FORMATTER.format(it.date.atZone(ZoneId.systemDefault())),
                minTemperature = it.hours[0].temperature.min,
                maxTemperature = it.hours[2].temperature.max,
                iconId = getWeatherIconRes(it.hours[2].icon),
                humidity = it.hours[2].humidity.avg,
                maxWindSpeed = it.hours[2].wind.speed.max,
                windDirection = it.hours[2].wind.direction.title,
                pressure = it.hours[2].pressure.avg,
                sunrise = it.astronomy.sunrise,
                sunset = it.astronomy.sunset,
                cloud = it.hours[2].cloud.title,
                precipitation = it.hours[2].precipitation.title,
                lengthDay = it.astronomy.lengthDay
            )
        }
    }


    fun toCityUiModel(data: CitiesResponse): List<CityUiModel> = with(data) {
        val citiesList = data.cities
        citiesList.map { it ->
            CityUiModel(
                name = it.name,
                title = it.title
            )
        }
    }
}