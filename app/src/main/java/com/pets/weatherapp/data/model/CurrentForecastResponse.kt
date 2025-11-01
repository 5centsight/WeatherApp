package com.pets.weatherapp.data.model

import com.pets.weatherapp.domain.mapper.InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CurrentForecastResponse(
    @SerialName("metadata")
    val metadata: Metadata,
    @SerialName("forecasts")
    val forecasts: List<Forecast>
)

@Serializable
data class Forecast(
    @Serializable(with = InstantSerializer::class)
    @SerialName("date")
    val date: Instant,
    @SerialName("update_date")
    @Serializable(with = InstantSerializer::class)
    val updateDate: Instant,
    @SerialName("temperature")
    val temperature: Int = 0,
    @SerialName("feel_like_temperature")
    val feelLikeTemp: Int = 0,
    @SerialName("pressure")
    val pressure: Int = 0,
    @SerialName("humidity")
    val humidity: Int = 0,
    @SerialName("cloud")
    val cloud: CoupledDetails,
    @SerialName("wind")
    val wind: CurrentWind,
    @SerialName("precipitation")
    val precipitation: CoupledDetails,
    @SerialName("astronomy")
    val astronomy: AstronomyDetail,
    @SerialName("icon")
    val iconName: String,
    @SerialName("links")
    val links: LinkCityName,
)

@Serializable
data class CurrentWind(
    @SerialName("speed")
    val speed: Int = 0,
    @SerialName("direction")
    val direction: WindDirection
)

