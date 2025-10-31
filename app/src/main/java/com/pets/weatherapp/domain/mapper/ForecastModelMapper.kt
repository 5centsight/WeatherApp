package com.pets.weatherapp.domain.mapper

import com.pets.weatherapp.data.model.ForecastResponse
import com.pets.weatherapp.data.model.ForecastUiModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class ForecastModelMapper {

    private companion object {
        val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(
            "dd MMMM", Locale("ru")
        )
    }

    fun toForecastUiModel(data: ForecastResponse): List<ForecastUiModel> = with(data) {
        val forecastsList = data.forecasts
        forecastsList.map { it ->
            ForecastUiModel(
                city = it.links.city,
                date = FORMATTER.format(it.date.atZone(ZoneId.systemDefault())),
                updateDate = FORMATTER.format(it.updateDate.atZone(ZoneId.systemDefault())),
                temperature = it.temperature,
                feelLikeTemp = it.feelLikeTemp,
                humidity = it.humidity,
                cloud = it.cloud.title,
                precipitation = it.precipitation.title
            )
        }
    }
}