package com.pets.weatherapp.data.model

import com.pets.weatherapp.domain.mapper.InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class DailyForecastsResponse(
    @SerialName("metadata")
    val metadata: Metadata,
    @SerialName("forecasts")
    val forecasts: List<HourlyForecast>
)

@Serializable
data class HourlyForecast(
    @SerialName("date")
    @Serializable(with = InstantSerializer::class)
    val date: Instant,
    @SerialName("hours")
    val hours: List<Hour>,
    @SerialName("astronomy")
    val astronomy: AstronomyDetail,
    @SerialName("links")
    val links: LinkCityName
)

@Serializable
data class Hour(
    @SerialName("hour")
    val hour: Int = 0,
    @SerialName("temperature")
    val temperature: MinAvgMax,
    @SerialName("pressure")
    val pressure: MinAvgMax,
    @SerialName("humidity")
    val humidity: MinAvgMax,
    @SerialName("wind")
    val wind: WindDetails,
    @SerialName("cloud")
    val cloud: CoupledDetails,
    @SerialName("precipitation")
    val precipitation: CoupledDetails,
    @SerialName("icon")
    val icon: String = ""
)

@Serializable
data class MinAvgMax(
    @SerialName("min")
    val min: Int = 0,
    @SerialName("avg")
    val avg: Int = 0,
    @SerialName("max")
    val max: Int = 0
)

@Serializable
data class WindDetails(
    @SerialName("speed")
    val speed: MinAvgMax,
    @SerialName("direction")
    val direction: WindDirection
)

