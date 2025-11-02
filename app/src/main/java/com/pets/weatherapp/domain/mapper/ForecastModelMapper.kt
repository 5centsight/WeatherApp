package com.pets.weatherapp.domain.mapper

import com.pets.weatherapp.data.model.CurrentForecastResponse
import com.pets.weatherapp.data.model.DailyForecastsResponse
import com.pets.weatherapp.data.model.CurrentForecastUiModel
import com.pets.weatherapp.data.model.DailyForecastUiModel
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
                icon = forecast.iconName
            )
    }

    fun toForecastUiModel(data: DailyForecastsResponse): List<DailyForecastUiModel> = with(data) {
        val hourlyForecastsList = data.forecasts
        hourlyForecastsList.map { it ->
            DailyForecastUiModel(
                date = FORMATTER.format(it.date.atZone(ZoneId.systemDefault())),
                minTemperature = it.hours[0].temperature.min,
                maxTemperature = it.hours[2].temperature.max
            )
        }
    }
}